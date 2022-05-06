package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import com.dam.kanpeki.model.UserRole;
import com.dam.kanpeki.utils.KanpekiConstants;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateUserDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -8978904000125002212L;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_EMAIL_NOTES, example = KanpekiConstants.API_USER_EMAIL_EXAMPLE, required = true)
	@NotBlank(message = KanpekiConstants.USER_EMAIL_NOT_BLANK_MSG)
	@Email(message = KanpekiConstants.USER_EMAIL_NOT_CORRECT_MSG)
	private String email;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_PASSWORD_NOTES, example = KanpekiConstants.API_USER_PASSWORD_EXAMPLE)
	private String password;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_FULLNAME_NOTES, example = KanpekiConstants.API_USER_FULLNAME_EXAMPLE, required = true)
	@NotBlank(message = KanpekiConstants.USER_NAME_NOT_BLANK_MSG)
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE, message = KanpekiConstants.USER_NAME_SIZE_MSG)
	private String fullName;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_NICKNAME_NOTES, example = KanpekiConstants.API_USER_NICKNAME_EXAMPLE)
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE, message = KanpekiConstants.USER_NICKNAME_SIZE_MSG)
	private String nickname;

	@ApiModelProperty(notes = KanpekiConstants.API_FILE_NOTES)
	@Nullable
	private MultipartFile file;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_BIRTHDAY_NOTES, example = KanpekiConstants.API_USER_BIRTHDAY_EXAMPLE)
	@DateTimeFormat(pattern = KanpekiConstants.DATE_FORMAT)
	private Date birthday;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_CITY_NOTES, example = KanpekiConstants.API_USER_CITY_EXAMPLE, required = true)
	@NotBlank(message = KanpekiConstants.USER_CITY_NOT_BLANK_MSG)
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE, message = KanpekiConstants.USER_CITY_SIZE_MSG)
	private String city;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_ROLES_NOTES, example = KanpekiConstants.API_USER_ROLES_EXAMPLE, required = true)
	@NotNull(message = KanpekiConstants.USER_ROLES_NOT_NULL)
	@Size(min = KanpekiConstants.MIN_ROLES_VALUE, message = KanpekiConstants.USER_ROLES_SIZE_MSG)
	private Set<UserRole> roles;

}
