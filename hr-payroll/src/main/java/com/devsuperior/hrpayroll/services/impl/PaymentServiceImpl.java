package com.devsuperior.hrpayroll.services.impl;

import org.springframework.stereotype.Service;

import com.devsuperior.hrpayroll.model.Payment;
import com.devsuperior.hrpayroll.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public Payment getPayment(long workerId, int days) {
		return new Payment("Bob", 200.0, days);
	}
}
