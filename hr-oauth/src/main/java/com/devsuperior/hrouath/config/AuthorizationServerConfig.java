package com.devsuperior.hrouath.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Classe responsavel por determinar que o microservice vai ser um authorization
 * server utilizando o OAUTH 2.0
 * 
 * @EnableAuthorizationServer - annotation responsavel por fazer um pre
 *                            processamento em background configurando nosso
 *                            microservice como oauth.
 * 
 *                            AuthorizationServerConfigurerAdapter -
 * 
 * @author fsouviei
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String IS_AUTHENTICATED = "isAuthenticated()";

	private static final String PERMIT_ALL = "permitAll()";

	private static final int SECONDS = 86400;

	private static final String PASSWORD = "password";

	private static final String WRITE = "write";

	private static final String READ = "read";

	@Value("${oauth.client.name}")
	private String clientName;

	@Value("${oauth.client.secret}")
	private String clientSecret;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private JwtTokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess(PERMIT_ALL).checkTokenAccess(IS_AUTHENTICATED);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientName).secret(passwordEncoder.encode(clientSecret)).scopes(READ, WRITE)
				.authorizedGrantTypes(PASSWORD).accessTokenValiditySeconds(SECONDS);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore)
				.accessTokenConverter(accessTokenConverter);
	}

}
