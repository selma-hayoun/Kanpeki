package com.dam.kanpeki.configuration;

import com.dam.kanpeki.utils.KanpekiConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing
public class KanpekiConfig {

	/**
	 * Configuración básica de CORS para los mapeos realizados
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/kanpeki/**").allowedOrigins(KanpekiConstants.MAP_CLIENT)
						.allowedMethods(KanpekiConstants.MAP_GET_METHOD, KanpekiConstants.MAP_POST_METHOD, KanpekiConstants.MAP_PUT_METHOD, KanpekiConstants.MAP_DELETE_METHOD).maxAge(KanpekiConstants.MAX_AGE_VALUE);
			}

		};
	}
}
