package com.dam.kanpeki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KanpekiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanpekiApplication.class, args);
	}

}
