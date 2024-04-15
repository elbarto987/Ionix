package Ionix.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;



@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<?> handlerNotFoundException(NoResourceFoundException ex){
		Error error = new Error();
		error.setDate(new Date());
		error.setError("Api no encontrado");
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		Error error = new Error();
		error.setDate(new Date());
		error.setError("Tipo de dato incorrecto");
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
		Error error = new Error();
		error.setDate(new Date());
		error.setError("El tipo de solicitud no es soportada por el metodo!");
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.	METHOD_NOT_ALLOWED.value());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(error);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> HttpMessageNotReadbleException(HttpMessageNotReadableException ex){
		Error error = new Error();
		error.setDate(new Date());
		error.setError("El JSON esta mal formado!");
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<?> HttpClientErrorException(HttpClientErrorException ex){
		Error error = new Error();
		error.setDate(new Date());
		error.setError("El JSON esta mal formado!");
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
	}
	
	public class Error {

		private String message;
		private String error;
		private int status;
		private Date date;
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		
	}
	
}
