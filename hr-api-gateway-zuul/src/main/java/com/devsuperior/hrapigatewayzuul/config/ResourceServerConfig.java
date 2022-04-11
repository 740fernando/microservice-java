package com.devsuperior.hrapigatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Classe de Configuracao responsavel por determinar o gateway zuul como servidor de recursos
 * 
 * EnableResourceServer - configurar por background que o microservice seja um resource service
 * 
 * @author fsouviei
 *
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String ADMIN = "ADMIN";

	private static final String OPERATOR = "OPERATOR";

	@Autowired
	private JwtTokenStore tokenStore; // Uma implementação de TokenStore que apenas lê dados dos próprios tokens. Não é realmente uma armazenamento, pois nunca persiste nada, e métodos como getAccessToken(OAuth2Authentication) sempre retornam null. Mas, no entanto, uma ferramenta útil, pois traduz tokens de acesso de e para autenticações. Use isso sempre que um TokenStore for necessário, mas lembre-se de usar a mesma instância JwtAccessTokenConverter (ou uma com o mesmo verificador) que foi usada quando os tokens foram cunhados.
	
	private static final String[] PATH_PUBLIC = {"/hr-oauth/oauth/token"};
	
	private static final String[] PATH_OPERATOR = {"/hr-worker/**"};
	
	private static final String[] PATH_ADMIN = {"/hr-payroll/**","/hr-user/**"};
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
		http
		.authorizeRequests()// autoriza requisicoes
		.antMatchers(PATH_PUBLIC).permitAll() // define as autorizacoes das rotas
		.antMatchers(HttpMethod.GET,PATH_OPERATOR).hasAnyRole(OPERATOR,ADMIN) // define o acesso as rotas de OPERATOR para funcoes admin e operator, mas SOMENTE para o metodo GET
		.antMatchers(PATH_ADMIN).hasRole(ADMIN)
		.anyRequest().authenticated(); // Qualquer outra rota que nao foi especificada acima, ira exigir autenticacao
	}
}
