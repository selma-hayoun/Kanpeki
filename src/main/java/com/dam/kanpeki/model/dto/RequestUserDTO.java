package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

	@NotBlank(message = "Email may not be null or empty")
	@Email(message = "Wrong email format")
	private String email;

	@NotBlank(message = "Password may not be null or empty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}[]:;<>,.?/~_+-=|\\]).{8,32}$")
	private String password;

	@NotBlank(message = "FullName may not be null or empty")
	@Size(max = 40, message = "FullName must be less than 40 characters long")
	private String fullName;

	@Size(max = 40, message = "NickName must be less than 40 characters long")
	private String nickname;

	private MultipartFile file;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	@NotBlank(message = "City may not be null or empty")
	@Size(max = 40, message = "City must be less than 40 characters long")
	private String city;

	@NotNull
	@Size(min = 1, message = "Must be at least one role assigned mandatorily")
	private Set<UserRole> roles;

}
