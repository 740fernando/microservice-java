package com.devsuperior.hrpayroll.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devsuperior.hrpayroll.model.Payment;
import com.devsuperior.hrpayroll.model.dto.WorkerResponseDTO;
import com.devsuperior.hrpayroll.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	public static final String PATH_WORKERS_ID = "/workers/{id}";

	private static final String NO_SPACE = "";

	@Value("${hr-worker.host}")
	private String workerHost;

	@Autowired
	private RestTemplate restTemplate;


	@Override
	public Payment getPayment(long workerId, int days) {
		Map<String, String> uriVariables = new HashMap<>();// Utilizado como parametro do metodo getForObject, o map
															// contem <String, String> pq os parametros no http são
															// sempre string
		uriVariables.put("id", NO_SPACE.valueOf(workerId)); // id = id do worker

		WorkerResponseDTO worker = restTemplate.getForObject(workerHost.concat(PATH_WORKERS_ID),
				WorkerResponseDTO.class, uriVariables);
		return new Payment(worker.getName(), worker.getDailyIncome(), days);

	}
}
