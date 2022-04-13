package com.dam.kanpeki.configuration;

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
				registry.addMapping("/kanpeki/**").allowedOrigins("http://localhost:9001")
						.allowedMethods("GET", "POST", "PUT", "DELETE").maxAge(3600);
			}

		};
	}
}
