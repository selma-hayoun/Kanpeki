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

import com.dam.kanpeki.utils.KanpekiConstants;
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

	@ApiModelProperty(notes = KanpekiConstants.API_USER_ID_NOTES, example = KanpekiConstants.API_ID_EXAMPLE, required = true)
	@NotNull
	private Long id;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_EMAIL_NOTES, example = KanpekiConstants.API_USER_EMAIL_EXAMPLE, required = true)
	@NotBlank
	@Email
	private String email;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_PASSWORD_NOTES, example = KanpekiConstants.API_USER_PASSWORD_EXAMPLE, required = true)
	@NotBlank
	@Pattern(regexp = KanpekiConstants.USER_PASSWORD_PATTERN, message = KanpekiConstants.USER_PASSWORD_PATTERN_MSG)
	private String password;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_FULLNAME_NOTES, example = KanpekiConstants.API_USER_FULLNAME_EXAMPLE, required = true)
	@NotBlank
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE)
	private String fullName;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_NICKNAME_NOTES, example = KanpekiConstants.API_USER_NICKNAME_EXAMPLE)
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE)
	private String nickname;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_IMG_NOTES)
	private String urlImage;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_BIRTHDAY_NOTES, example = KanpekiConstants.API_USER_BIRTHDAY_EXAMPLE)
	@DateTimeFormat(pattern = KanpekiConstants.DATE_FORMAT)
	private Date birthday;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_CITY_NOTES, example = KanpekiConstants.API_USER_CITY_EXAMPLE, required = true)
	@NotBlank
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE)
	private String city;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_ROLES_NOTES, example = KanpekiConstants.API_USER_ROLES_EXAMPLE, required = true)
	@NotNull
	@Size(min = KanpekiConstants.MIN_ROLES_VALUE)
	private Set<UserRole> roles;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_CREATED_NOTES, example = KanpekiConstants.API_USER_CREATED_EXAMPLE)
	private LocalDateTime createdAt;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_PASSWORD_CHANGE_NOTES, example = KanpekiConstants.API_USER_PASSWORD_CHANGE_EXAMPLE)
	private LocalDateTime lastPasswordChangeAt;

}
