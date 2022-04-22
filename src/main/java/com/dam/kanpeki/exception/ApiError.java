package com.dam.kanpeki.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.dam.kanpeki.utils.KanpekiConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * Clase creada para formatear los errores de nuestra Api controlados por
 * nosotros
 */
@Getter
@Setter
@NoArgsConstructor
public class ApiError {

	@NonNull
	private HttpStatus status;

	@JsonFormat(shape = Shape.STRING, pattern = KanpekiConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime errorDate = LocalDateTime.now();

	@NonNull
	private String msg;

	@NonNull
	private List<String> errors;

	public ApiError(@NonNull HttpStatus status, @NonNull String msg) {
		this.status = status;
		this.msg = msg;
		this.errors = new ArrayList<>();
	}

	public ApiError(@NonNull HttpStatus status, @NonNull String msg, @NonNull List<String> errors) {
		this.status = status;
		this.msg = msg;
		this.errors = errors;
	}

	public ApiError(@NonNull HttpStatus status, @NonNull String msg, String error) {
		this.status = status;
		this.msg = msg;
		errors = Arrays.asList(error);
	}
}
