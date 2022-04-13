package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.dam.kanpeki.model.UserRole;

import io.swagger.annotations.ApiModelProperty;
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

	@ApiModelProperty(notes = "User id", example = "1", required = true)
	@NotNull
	private Long id;

	@ApiModelProperty(notes = "User email", example = "kanjilovers@kanpeki.com", required = true)
	@NotBlank
	@Email
	private String email;

	@ApiModelProperty(notes = "User password", example = "C4c4hu3t3!!", required = true)
	@NotBlank
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
	private String password;

	@ApiModelProperty(notes = "User full name", example = "John Doe", required = true)
	@NotBlank
	@Size(max = 40)
	private String fullName;

	@ApiModelProperty(notes = "User nickname", example = "Kanji-Lover", required = false)
	@Size(max = 40)
	private String nickname;

	@ApiModelProperty(notes = "User image URL in our server", required = false)
	private String urlImage;

	@ApiModelProperty(notes = "User birthday", example = "1989-12-31", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	@ApiModelProperty(notes = "User city or town", example = "Kyoto", required = true)
	@NotBlank
	@Size(max = 40)
	private String city;

	@ApiModelProperty(notes = "User roles", example = "[\"USER\"]", required = true)
	@NotNull
	@Size(min = 1)
	private Set<UserRole> roles;

	@ApiModelProperty(notes = "User creation date and time", example = "2022-04-13T18:39:39.505Z", required = false)
	private LocalDateTime createdAt;

	@ApiModelProperty(notes = "Password last date and time of modification", example = "2022-04-13T18:39:39.505Z", required = false)
	private LocalDateTime lastPasswordChangeAt;

}
