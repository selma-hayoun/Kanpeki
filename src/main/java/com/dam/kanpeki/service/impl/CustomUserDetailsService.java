package com.dam.kanpeki.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dam.kanpeki.service.UserServiceI;

import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private final UserServiceI uService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return uService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
