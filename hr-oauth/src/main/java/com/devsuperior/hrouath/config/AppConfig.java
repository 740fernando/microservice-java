package com.devsuperior.hrouath.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Configuracoes gerais da app hr-oauth
 * 
 * @author fsouviei
 */
@Configuration
public class AppConfig {

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Auxiliar que traduz entre valores de token codificados JWT e informações de
	 * autenticação OAuth (em ambas as direções).
	 * 
	 * @return JwtAccessTokenConverter
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("MY-SECRET-KEY");
		return tokenConverter;
	}

	/**
	 * Responsavel por ler as informacoes do token
	 */
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}
