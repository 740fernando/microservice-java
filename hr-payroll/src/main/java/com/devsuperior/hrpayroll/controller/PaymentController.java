package com.devsuperior.hrpayroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hrpayroll.model.Payment;
import com.devsuperior.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService service;
	
	/**
	 * Endpoit getPayment responsável por chamar o service hr-worker
	 * Caso ocorra algum problema @HystrixCommand entra em execucao
	 * @param workerId
	 * @param days
	 * @return
	 */
	@HystrixCommand(fallbackMethod="getPaymentAlternative")
	@GetMapping("/{workerId}/days/{days}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days){
		return ResponseEntity.ok(service.getPayment(workerId, days));
	}
	public ResponseEntity<Payment> getPaymentAlternative( Long workerId,  Integer days){
		return ResponseEntity.ok(new Payment("Brann", 400.00, days));
	}
}
