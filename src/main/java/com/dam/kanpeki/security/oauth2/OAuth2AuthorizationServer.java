package com.dam.kanpeki.security.oauth2;

import javax.sql.DataSource;

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
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.dam.kanpeki.utils.KanpekiConstants;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final DataSource dataSource;

	// Constantes para los diferentes tipos de flujos que podrían existir
	private static final String CODE_GRANT_TYPE = "authorization_code";
	private static final String IMPLICIT_GRANT_TYPE = "implicit";
	private static final String PASS_GRANT_TYPE = "password";
	private static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";

	@Value("${oauth2.client-id}")
	private String clientId;

	@Value("${oauth2.client-secret}")
	private String clientSecret;

	@Value("${oauth2.redirect-uri}")
	private String redirectUri;

	@Value("${oauth2.access-token-validity-seconds}")
	private int accessTokenValiditySeconds;

	@Value("${oauth2.access-token-validity-seconds}")
	private int refreshTokenValiditySeconds;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// Especificar algunos elementos de seguridad
		security.tokenKeyAccess("permitAll()")// Cualquiera puede acceder
//				.checkTokenAccess("permitAll()").allowFormAuthenticationForClients();//Permitir los endpoints de gestión a cualquiera
				.checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();// Permitimos la
																							// autenticación del
																							// formulario de cliente
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource).withClient(clientId).secret(passwordEncoder.encode(clientSecret))
				.authorizedGrantTypes(PASS_GRANT_TYPE, REFRESH_TOKEN_GRANT_TYPE, IMPLICIT_GRANT_TYPE, CODE_GRANT_TYPE)
				.authorities("READ_ONLY_CLIENT").scopes("read").resourceIds(KanpekiConstants.SECURITY_RESOURCE_ID)
				.redirectUris(redirectUri).accessTokenValiditySeconds(accessTokenValiditySeconds)
				.refreshTokenValiditySeconds(refreshTokenValiditySeconds);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// Le indicamos el autentication manager y el userdetailsservice inyectados
		endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService)
				.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter());
	}

	@Bean
	public TokenStore tokenStore() {// Almacén de tokens
		return new JdbcTokenStores(dataSource);
	}

	@Bean
	public AccessTokenConverter accessTokenConverter() {
		// Podemos add TokenEnhancer para añadir más información al token de la que se
		// añade por defecto
		return new JwtAccessTokenConverter();
	}

}
