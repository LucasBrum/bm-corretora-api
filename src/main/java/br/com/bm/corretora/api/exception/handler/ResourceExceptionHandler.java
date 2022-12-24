package br.com.bm.corretora.api.exception.handler;

import br.com.bm.corretora.api.exception.DataIntegrityViolationException;
import br.com.bm.corretora.api.exception.ObjectNotFoundException;
import br.com.bm.corretora.api.exception.error.StandardError;
import br.com.bm.corretora.api.exception.error.ValidationError;
import br.com.bm.corretora.api.util.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {

		StandardError standardError = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(),
				Messages.OBJECT_NOT_FOUND,
				ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {

		StandardError standardError = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				Messages.DATA_VALIDATION,
				ex.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {

		ValidationError errors = new ValidationError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				Messages.VALIDATION_ERROR,
				Messages.ERRO_VALIDACAO_CAMPOS,
				request.getRequestURI());

		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errors.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

	}
}
