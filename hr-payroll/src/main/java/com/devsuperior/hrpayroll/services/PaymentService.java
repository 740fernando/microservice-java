package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.model.Payment;

public interface PaymentService {

	Payment getPayment(long workerId, int days);

}
