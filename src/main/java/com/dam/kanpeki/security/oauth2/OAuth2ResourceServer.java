package com.dam.kanpeki.security.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.dam.kanpeki.utils.KanpekiConstants;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(KanpekiConstants.SECURITY_RESOURCE_ID);

	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// Configuración de la seguridad
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				// Sirve para habilitar la consola de H2
				.antMatchers("/h2-console/**").permitAll().antMatchers(HttpMethod.GET, KanpekiConstants.ALL_MAPPINGS)
				.hasAnyRole(KanpekiConstants.SENSEI_ROLE, KanpekiConstants.GAKUSEI_ROLE)
				.antMatchers(HttpMethod.POST, KanpekiConstants.CATEGORY_MAPPINGS).hasRole(KanpekiConstants.SENSEI_ROLE)
				.antMatchers(HttpMethod.POST, KanpekiConstants.QUESTION_MAPPINGS).hasRole(KanpekiConstants.SENSEI_ROLE)
				.antMatchers(HttpMethod.POST, KanpekiConstants.RESULT_MAPPINGS).hasRole(KanpekiConstants.SENSEI_ROLE)
				.antMatchers(HttpMethod.POST, KanpekiConstants.WORD_MAPPINGS).hasRole(KanpekiConstants.SENSEI_ROLE)
				.antMatchers(HttpMethod.POST, KanpekiConstants.USER_MAPPINGS).hasRole(KanpekiConstants.SENSEI_ROLE)
				.antMatchers(HttpMethod.PUT, KanpekiConstants.CATEGORY_MAPPINGS, KanpekiConstants.QUESTION_MAPPINGS,
						KanpekiConstants.RESULT_MAPPINGS, KanpekiConstants.WORD_MAPPINGS)
				.hasRole(KanpekiConstants.SENSEI_ROLE).antMatchers(HttpMethod.DELETE, KanpekiConstants.ALL_MAPPINGS)
				.hasRole(KanpekiConstants.SENSEI_ROLE)
				.antMatchers(HttpMethod.PUT, KanpekiConstants.USER_UPDATE_MAPPINGS)
				.hasAnyRole(KanpekiConstants.GAKUSEI_ROLE, KanpekiConstants.SENSEI_ROLE)
				.antMatchers(HttpMethod.POST, KanpekiConstants.REGISTRATION_MAPPINGS).permitAll();

		// Sirve para habilitar la consola de H2
		http.headers().frameOptions().disable();
	}

}