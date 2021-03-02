package br.com.gama.bankline.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> loginException(DataBaseException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<StandardError> loginException(LoginException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(TokenExpiradoException.class)
	public ResponseEntity<StandardError> tokenException(TokenExpiradoException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}
}
