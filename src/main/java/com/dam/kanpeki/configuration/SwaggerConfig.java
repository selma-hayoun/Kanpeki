package com.dam.kanpeki.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dam.kanpeki.utils.KanpekiConstants;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

@Configuration
public class SwaggerConfig {

	@Value("${oauth2.client-id}")
	private String clientId;

	@Value("${oauth2.client-secret}")
	private String clientSecret;

	@Value("${info.build.name}")
	private String infoBuildName;

	@Value("${oauth2.server.url}")
	private String accessTokenUri;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().securitySchemes(Collections.singletonList(securitySchema()))
				.securityContexts(Collections.singletonList(securityContext())).useDefaultResponseMessages(false);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(KanpekiConstants.API_INFO_TITLE, KanpekiConstants.API_INFO_DESCRIPTION,
				KanpekiConstants.API_INFO_VERSION, KanpekiConstants.API_INFO_WILD_CARD,
				new Contact(KanpekiConstants.API_INFO_CONTACT_NAME, KanpekiConstants.API_INFO_CONTACT_URL,
						KanpekiConstants.API_INFO_CONTACT_EMAIL),
				KanpekiConstants.API_INFO_LICENSE_NAME, KanpekiConstants.API_INFO_WILD_CARD, Collections.emptyList());
	}

	private OAuth securitySchema() {

		List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		authorizationScopeList.add(new AuthorizationScope("read", "read all"));
		authorizationScopeList.add(new AuthorizationScope("trust", "trust all"));
		authorizationScopeList.add(new AuthorizationScope("write", "access all"));

		List<GrantType> grantTypes = new ArrayList<>();
		GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(accessTokenUri);

		grantTypes.add(creGrant);

		return new OAuth("oauth2schema", authorizationScopeList, grantTypes);

	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {

		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
		authorizationScopes[0] = new AuthorizationScope("read", "read all");
		authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
		authorizationScopes[2] = new AuthorizationScope("write", "write all");

		return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
	}

	@Bean
	public SecurityConfiguration securityInfo() {
		return SecurityConfigurationBuilder.builder().clientId(clientId).clientSecret(clientSecret)
				.realm("test-app-realm").appName("test-app").scopeSeparator(",").additionalQueryStringParams(null)
				.useBasicAuthenticationWithAccessCodeGrant(false).build();
	}

}
