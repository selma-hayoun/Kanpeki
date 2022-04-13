package com.dam.kanpeki;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dam.kanpeki.service.FileSystemStorageServiceI;

@SpringBootApplication
public class KanpekiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanpekiApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(FileSystemStorageServiceI storageService) {
		return args -> {
			// Inicializamos el servicio de ficheros
			storageService.deleteAll();
			storageService.init();
		};

	}

}
