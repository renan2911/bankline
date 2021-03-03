package br.com.gama.bankline.exception;



import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	 @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        List<ObjectError> erros = getErros(ex);
	        ErroResponseException erroResponse = getErroResponse(ex, status, erros);
	        return new ResponseEntity<>(erroResponse, status);
	    }

	    private ErroResponseException getErroResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ObjectError> erros) {
	        return new ErroResponseException("Requisição possui campos inválidos", status.value(), status.getReasonPhrase(), erros);
	    }

	    private List<ObjectError> getErros(MethodArgumentNotValidException ex) {
	        return ex.getBindingResult().getFieldErrors().stream()
	                .map(error -> new ObjectError(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
	                .collect(Collectors.toList());
	    }
	    
	    @ExceptionHandler(DataBaseException.class)
		public ResponseEntity<StandardError> loginException(DataBaseException ex, HttpServletRequest request) {

			HttpStatus status = HttpStatus.NOT_FOUND;
			StandardError err = new StandardError(Instant.now(), status.value(), ex.getMessage(), request.getRequestURI());

			return ResponseEntity.status(status).body(err);
		}
		
		@ExceptionHandler(TokenExpiradoException.class)
		public ResponseEntity<StandardError> tokenException(TokenExpiradoException ex, HttpServletRequest request) {

			HttpStatus status = HttpStatus.NOT_FOUND;
			StandardError err = new StandardError(Instant.now(), status.value(), ex.getMessage(), request.getRequestURI());

			return ResponseEntity.status(status).body(err);
		}
}
