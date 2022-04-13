package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import com.dam.kanpeki.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 5568049776356202763L;

	private Long id;

	private String email;

	private String password;

	private String fullName;

	private String nickname;

	private String urlImage;

	private Date birthday;

	private String city;

	private Set<UserRole> roles;

	private LocalDateTime createdAt;

	private LocalDateTime lastPasswordChangeAt;

}
