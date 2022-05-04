package com.dam.kanpeki.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dam.kanpeki.utils.KanpekiConstants;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@Order(1)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()// Establecemos cors y dehabilitamos csrf
				.requestMatchers().antMatchers("/login", "/oauth/authorize").and().authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/oauth/**").permitAll()// Para que se puedan hacer cualquier tipo de
																			// petición de tipo options para oauth
				.antMatchers(HttpMethod.POST, KanpekiConstants.REGISTRATION_MAPPINGS).permitAll().anyRequest().authenticated()
				.and().formLogin().permitAll();// Salvo formulario de login que se permite
												// sin excepciones
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
