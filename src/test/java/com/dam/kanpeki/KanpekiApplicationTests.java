package com.dam.kanpeki;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dam.kanpeki.controller.CategoryController;
import com.dam.kanpeki.controller.FilesController;
import com.dam.kanpeki.controller.QuestionAnswerController;
import com.dam.kanpeki.controller.RegistrationController;
import com.dam.kanpeki.controller.ResultController;
import com.dam.kanpeki.controller.UserController;
import com.dam.kanpeki.controller.WordController;

@SpringBootTest
class KanpekiApplicationTests {

	@Autowired
	private CategoryController catController;

	@Autowired
	private FilesController filesController;

	@Autowired
	private QuestionAnswerController questionAnswerController;

	@Autowired
	private RegistrationController registrationController;

	@Autowired
	private ResultController resultController;

	@Autowired
	private UserController userController;

	@Autowired
	private WordController wordController;

	@Test
	void contextLoads() {
		assertThat(catController).isNotNull();
		assertThat(filesController).isNotNull();
		assertThat(questionAnswerController).isNotNull();
		assertThat(registrationController).isNotNull();
		assertThat(resultController).isNotNull();
		assertThat(userController).isNotNull();
		assertThat(wordController).isNotNull();
	}

}
