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
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import com.dam.kanpeki.model.UserRole;

import io.swagger.annotations.ApiModelProperty;
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

	@ApiModelProperty(notes = "User email", example = "kanjilovers@kanpeki.com", required = true)
	@NotBlank(message = "Email may not be null or empty")
	@Email(message = "Wrong email format")
	private String email;

	@ApiModelProperty(notes = "User password", example = "C4c4hu3t3!!", required = true)
	@NotBlank(message = "Password may not be null or empty")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
	private String password;

	@ApiModelProperty(notes = "User full name", example = "John Doe", required = true)
	@NotBlank(message = "FullName may not be null or empty")
	@Size(max = 40, message = "FullName must be less than 40 characters long")
	private String fullName;

	@ApiModelProperty(notes = "User nickname", example = "Kanji-Lover", required = false)
	@Size(max = 40, message = "NickName must be less than 40 characters long")
	private String nickname;

	@ApiModelProperty(notes = "Image file field for uploadling", required = false)
	@Nullable
	private MultipartFile file;

	@ApiModelProperty(notes = "User birthday", example = "1989-12-31", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	@ApiModelProperty(notes = "User city or town", example = "Kyoto", required = true)
	@NotBlank(message = "City may not be null or empty")
	@Size(max = 40, message = "City must be less than 40 characters long")
	private String city;

	@ApiModelProperty(notes = "User roles", example = "[\"USER\"]", required = true)
	@NotNull
	@Size(min = 1, message = "Must be at least one role assigned mandatorily")
	private Set<UserRole> roles;

}
