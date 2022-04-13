package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.dam.kanpeki.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6514883880087110520L;

	private String email;

	private String password;

	private String fullName;

	private String nickname;

	private MultipartFile file;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	private String city;

	private Set<UserRole> roles;

}
