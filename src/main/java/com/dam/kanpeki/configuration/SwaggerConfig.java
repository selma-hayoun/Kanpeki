package com.dam.kanpeki.configuration;

import java.util.Collections;

import com.dam.kanpeki.utils.KanpekiConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(KanpekiConstants.API_INFO_TITLE,
				KanpekiConstants.API_INFO_DESCRIPTION,
				KanpekiConstants.API_INFO_VERSION, KanpekiConstants.API_INFO_WILD_CARD, new Contact(KanpekiConstants.API_INFO_CONTACT_NAME,
						KanpekiConstants.API_INFO_CONTACT_URL, KanpekiConstants.API_INFO_CONTACT_EMAIL),
				KanpekiConstants.API_INFO_LICENSE_NAME, KanpekiConstants.API_INFO_WILD_CARD, Collections.emptyList());
	}

}
