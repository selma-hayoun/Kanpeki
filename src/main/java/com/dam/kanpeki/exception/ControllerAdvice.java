package com.dam.kanpeki.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

	/**
	 * Sobreescribimos este método para que las excepciones sigan el formato creado
	 * en ApiError
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
//		return super.handleExceptionInternal(ex, body, headers, status, request);
		ApiError apiError = new ApiError(status, ex.getMessage());
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}

	/**
	 * Excepción cuando no se encuentra el archivo en el almacenamiento
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<ApiError> handleNotSupportedPlanet(StorageFileNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

}
