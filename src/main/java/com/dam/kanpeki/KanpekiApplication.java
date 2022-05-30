package com.dam.kanpeki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dam.kanpeki.service.FileSystemStorageServiceI;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@SecurityScheme(name = "kanpeki-api", scheme = "Bearer", type = SecuritySchemeType.OAUTH2, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(info = @Info(title = "User API", version = "1.0", description = "User Details"))
public class KanpekiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanpekiApplication.class, args);
	}

	@Autowired
	protected PasswordEncoder passEncoder;

	@Bean
	public CommandLineRunner init(FileSystemStorageServiceI storageService) {
		return args -> {
			// Inicializamos el servicio de ficheros
			storageService.deleteAll();
			storageService.init();
		};

	}

}
