package com.devsuperior.hrpayroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	/**
	 * Cria uma instancia única de um RestTemplate
	 * Ficara disponivel para ser instanciada em outros componentes.
	 * @return
	 */
	@Bean
	public RestTemplate registerRestTemplate() {
		return new RestTemplate();
	}
}
