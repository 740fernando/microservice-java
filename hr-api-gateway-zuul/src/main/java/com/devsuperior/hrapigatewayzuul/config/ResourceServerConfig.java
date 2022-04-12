package com.devsuperior.hrapigatewayzuul.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Classe de Configuracao responsavel por determinar o gateway zuul como
 * servidor de recursos
 * 
 * EnableResourceServer - configurar por background que o microservice seja um
 * resource service
 * 
 * @author fsouviei
 *
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String PATH = "/**";

	private static final String CONTENT_TYPE = "Content-type";

	private static final String AUTHORIZATION = "Authorization";

	private static final String PATCH = "PATCH";

	private static final String DELETE = "DELETE";

	private static final String PUT = "PUT";

	private static final String GET = "GET";

	private static final String POST = "POST";

	private static final String ALL = "*";

	private static final String ADMIN = "ADMIN";

	private static final String OPERATOR = "OPERATOR";

	private static final String[] PATH_PUBLIC = { "/hr-oauth/oauth/token" };

	private static final String[] PATH_OPERATOR = { "/hr-worker/**" };

	private static final String[] PATH_ADMIN = { "/hr-payroll/**", "/hr-user/**", "/actuator/**",
			"/hr-worker/actuator/**", "/hr-oauth/actuator/**" };

	@Autowired
	private JwtTokenStore tokenStore;

	/**
	 * Metodo responsavel por realizar a leitura do token
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	/**
	 * Metodo responsavel por configurar as autorizacoes
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(PATH_PUBLIC).permitAll().antMatchers(HttpMethod.GET, PATH_OPERATOR)
				.hasAnyRole(OPERATOR, ADMIN).antMatchers(PATH_ADMIN).hasRole(ADMIN).anyRequest().authenticated();

		http.cors().configurationSource(corsConfigurationSource());
	}

	/**
	 * O CORS (Cross-origin Resource Sharing) é um mecanismo utilizado pelos
	 * navegadores para compartilhar recursos entre diferentes origens. O CORS é uma
	 * especificação do W3C e faz uso de headers do HTTP para informar aos
	 * navegadores se determinado recurso pode ser ou não acessado.
	 * 
	 * 
	 * @return
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		var corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList(ALL)); // permissoes de origem
		corsConfig.setAllowedMethods(Arrays.asList(POST, GET, PUT, DELETE, PATCH)); // metodos permitidos
		corsConfig.setAllowCredentials(true); // permissao 'credenciais'
		corsConfig.setAllowedHeaders(Arrays.asList(AUTHORIZATION, CONTENT_TYPE)); // permissao de headers

		var source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration(PATH, corsConfig);
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);// essa linha representa que o filter vai ser executado em alta
													// precedência
		return bean;
	}
}
