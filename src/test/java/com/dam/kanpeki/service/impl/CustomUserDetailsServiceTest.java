package com.dam.kanpeki.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dam.kanpeki.model.User;
import com.dam.kanpeki.service.UserServiceI;
import com.dam.kanpeki.util.KanpekiTestsConstants;

class CustomUserDetailsServiceTest {

	private CustomUserDetailsService customUserDetailsService;
	private UserServiceI uService;

	@BeforeEach
	public void setup() {
		uService = Mockito.mock(UserServiceI.class);
		customUserDetailsService = new CustomUserDetailsService(uService);
	}

	@Test
	@DisplayName("Test Should Pass When loadUserByUsername is Given Correct Username Return Correct Object")
	void whenCallingLoadUserByUsername_givenCorrectUsername_thenShouldReturnCorrectObject() throws Exception {

		User dummyUser = new User(1L, KanpekiTestsConstants.USER_EMAIL_EXAMPLE,
				KanpekiTestsConstants.USER_PASSWORD_EXAMPLE, KanpekiTestsConstants.USER_NAME_EXAMPLE,
				KanpekiTestsConstants.USER_NICKNAME_EXAMPLE, KanpekiTestsConstants.EMPTY_STRING, null,
				KanpekiTestsConstants.USER_CITY_EXAMPLE, null, null, null, null);

		when(uService.findByEmail(anyString())).thenReturn(Optional.of(dummyUser));

		assertEquals(customUserDetailsService.loadUserByUsername(KanpekiTestsConstants.USER_EMAIL_EXAMPLE), dummyUser);

	}

	@Test
	@DisplayName("Test Should Pass When loadUserByUsername is Given Incorrect Username Throws UsernameNotFoundException")
	void whenCallingLoadUserByUsername_givenIncorrectUsername_thenThrowUsernameNotFoundException() throws Exception {

		String username = KanpekiTestsConstants.USER_EMAIL_EXAMPLE;

		when(uService.findByEmail(username)).thenReturn(Optional.ofNullable(null));

		assertThrows(UsernameNotFoundException.class, () -> {
			customUserDetailsService.loadUserByUsername(username);
		});

	}

}
