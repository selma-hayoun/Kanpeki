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

import com.dam.kanpeki.utils.ExceptionUtils;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

	/**
	 * Sobreescribimos este método para que las excepciones sigan el formato creado
	 * en ApiError
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
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
	 * Excepción ya existe otro usuario registrado con ese email
	 *
	 */
	@ExceptionHandler(UserEmailAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handleUserEmailAlreadyExistsException(UserEmailAlreadyExistsException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	/**
	 * Excepción cuando se persiste una pregunta con más de una respuesta correcta
	 *
	 */
	@ExceptionHandler(QuestionOnlyOneCorrectAnswerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handleQuestionOnlyOneCorrectAnswerException(
			QuestionOnlyOneCorrectAnswerException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	/**
	 * Excepción la password es insegura (no cumple patrón de seguridad)
	 *
	 */
	@ExceptionHandler(UserPasswordInsecureException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handleUserPasswordInsecureException(UserPasswordInsecureException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	/**
	 * Excepción cuando no se ha podido enviar el email
	 *
	 */
	@ExceptionHandler(EmailServerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiError> handleEmailServerException(EmailServerException ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
	}

	/**
	 * Excepción cuando ya existe otro usuario registrado con ese nickname
	 *
	 */
	@ExceptionHandler(UserNicknameAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handleUserNicknameAlreadyExistsException(UserNicknameAlreadyExistsException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	/**
	 * Excepción para controlar la no eliminación de una categoría con datos
	 * vinculados
	 *
	 */
	@ExceptionHandler(InvalidOperationOnCategoryException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ApiError> handleInvalidOperationOnCategoryException(InvalidOperationOnCategoryException ex) {
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, ex.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
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
	 * Excepción el user id y/o category id del resultado no existen
	 *
	 */
	@ExceptionHandler(InvalidFKReferencesException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handleInvalidReferencesOnResultException(InvalidFKReferencesException ex) {
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
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
				"Not valid due to validation error: \n" + ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	/**
	 * Excepción cuando se inserta o actualiza una categoría con un Unit Name y
	 * Category Name que ya existen en la base de datos
	 *
	 */
	@ExceptionHandler(CategoryAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	/**
	 * Excepción cuando ya la palabra japonesa en la base de datos
	 *
	 */
	@ExceptionHandler(WordJapaneseAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handleWordJapaneseAlreadyExistsException(WordJapaneseAlreadyExistsException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	/**
	 * Excepción sobreescrita para el control de valores no permitidos en la base de
	 * datos
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionUtils.getCustomApiError(ex));
	}

	/**
	 * Excepción sobreescrita para el control de valores no permitidos en la base de
	 * datos
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionUtils.getCustomApiError(ex));
	}

}
