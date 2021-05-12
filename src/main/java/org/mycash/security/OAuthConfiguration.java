package org.mycash.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

	@Value("${mycash.clientId}")
	private String clientId;
	
	@Value("${mycash.clientSecret}")
	private String clientSecret;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	// Configura os clientes (apps) que poderão acessar nossa API.
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(clientId)
	        .secret(passwordEncoder.encode(clientSecret))
	        .accessTokenValiditySeconds(43200) // 12h
	        .refreshTokenValiditySeconds(2592000) // 30d
	        .authorizedGrantTypes("password", "refresh_token")
	        .scopes("read", "write")
	        .resourceIds("api");
	}
	
	// Configuração relacionada aos endpoints de autorização /oauth/token.
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
	        .accessTokenConverter(accessTokenConverter())
	        .userDetailsService(userDetailsService)
	        .authenticationManager(authenticationManager);
	}
	
	// Auxiliar que converte token JWT
	@Bean
	JwtAccessTokenConverter accessTokenConverter() {
		return new JwtAccessTokenConverter();
	}
	
}
