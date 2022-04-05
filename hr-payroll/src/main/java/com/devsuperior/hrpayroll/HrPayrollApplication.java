package com.devsuperior.hrpayroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@RibbonClient(name = "hr-worker")
@EnableFeignClients
@SpringBootApplication
public class HrPayrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrPayrollApplication.class, args);
	}

}
/**
 * Balanceamento de carga com Ribbon
 *  o balanceamento de carga (ou load balancing) é uma técnica que permite distribuir as requisições uniformemente entre as instâncias, assim,
 *   otimizando a utilização de recursos, diminuindo o tempo de resposta e minimizando sobrecargas.
 *   
 * O projeto payroll tem a função de cliente Ribbon, ou seja, ele que irá realizar a chamada para outro projeto, no caso Worker.
 * que irá ter mais de uma instancia do worker levantada, e essa chamada para worker utilizará balaceamento de carga. 
 * 
 *  Para configurar o projeto para ser cliente Ribbon, é necessario da annotation @RibbonClient
 */