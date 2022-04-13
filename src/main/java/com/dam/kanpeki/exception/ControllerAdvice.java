package com.dam.kanpeki.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
		ApiError apiError = new ApiError(status, ex.toString());
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}

	/**
	 * Excepción cuando no se encuentra el archivo en el almacenamiento
	 *
	 */
	@ExceptionHandler(StorageFileNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiError> handleStorageFileNotFoundException(StorageFileNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	/**
	 * Excepción cuando no se encuentran datos
	 *
	 */
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiError> handleDataNotFoundException(DataNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	/**
	 * Excepción cuando se envían parámetros incorrectos que no se han podido
	 * parsear
	 *
	 */
	@ExceptionHandler(ParameterIncorrectFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handleParameterIncorrectFormatException(ParameterIncorrectFormatException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	/**
	 * Excepción cuando no se quebrantan constraints de validación en la request
	 *
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
				"Not valid due to validation error: \n" + ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		return super.handleMethodArgumentNotValid(ex, headers, status, request);
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, getDefaultMsg(ex.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
//		return super.handleBindException(ex, headers, status, request);
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, getDefaultMsg(ex.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	private String getDefaultMsg(String ex) {

		String delimeterStr = " default message ";
		String defaultMsg = "";

		if (ex.lastIndexOf(delimeterStr) != -1) {
			try {
				String temp = ex;
				defaultMsg = temp
						.substring(temp.indexOf(delimeterStr, temp.indexOf(delimeterStr) + 1) + 18, temp.length() - 3)
						.trim();
			} catch (Exception e) {
				defaultMsg = ex;
			}
			return defaultMsg;
		} else {
			return ex;
		}

	}

}
