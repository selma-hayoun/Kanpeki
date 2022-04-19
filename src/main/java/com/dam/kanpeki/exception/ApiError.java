package com.dam.kanpeki.exception;

import java.time.LocalDateTime;

import com.dam.kanpeki.utils.KanpekiConstants;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Clase creada para formatear los errores de nuestra Api controlados por
 * nosotros
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ApiError {

	@NonNull
	private HttpStatus status;

	@JsonFormat(shape = Shape.STRING, pattern = KanpekiConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime errorDate = LocalDateTime.now();

	@NonNull
	private String msg;
}
