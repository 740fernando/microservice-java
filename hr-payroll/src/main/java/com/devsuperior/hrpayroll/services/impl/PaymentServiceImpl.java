package com.devsuperior.hrpayroll.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hrpayroll.feignclients.WorkerFeignClient;
import com.devsuperior.hrpayroll.model.Payment;
import com.devsuperior.hrpayroll.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;


	@Override
	public Payment getPayment(long workerId, int days) {
			var worker = workerFeignClient.findById(workerId).getBody(); // Balanceamento de carga com Ribbon - Quando for executada esta linha, o Logger irá imprimir o numero da porta
			return new Payment(worker.getName(),worker.getDailyIncome(),days);
	}
}
