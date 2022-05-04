package com.dam.kanpeki;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dam.kanpeki.controller.CategoryController;

@SpringBootTest
class KanpekiApplicationTests {

	@Autowired
	private CategoryController catController;

	@Test
	void contextLoads() {
		assertThat(catController).isNotNull();
	}

}
