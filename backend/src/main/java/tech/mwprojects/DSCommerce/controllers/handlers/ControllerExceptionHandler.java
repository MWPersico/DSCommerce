package tech.mwprojects.DSCommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import tech.mwprojects.DSCommerce.dto.CustomError;
import tech.mwprojects.DSCommerce.dto.ValidationError;
import tech.mwprojects.DSCommerce.exceptions.DatabaseException;
import tech.mwprojects.DSCommerce.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(Instant.now(), status.value(), ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<CustomError> database(DatabaseException ex, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		CustomError err = new CustomError(Instant.now(), status.value(), ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> invalid(MethodArgumentNotValidException ex, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError(Instant.now(), status.value(), ex.getMessage(), request.getRequestURI());
		
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			err.addFieldError(error.getField(), error.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(err);
	}
}
