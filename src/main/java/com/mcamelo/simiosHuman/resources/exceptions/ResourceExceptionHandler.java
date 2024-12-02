package com.mcamelo.simiosHuman.resources.exceptions;

import com.mcamelo.simiosHuman.services.exceptions.DatabaseException;
import com.mcamelo.simiosHuman.services.exceptions.InvalideMatrixException;
import com.mcamelo.simiosHuman.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

	protected Logger logger;

	public ResourceExceptionHandler() {
		logger = LoggerFactory.getLogger(getClass());
	}
	@ExceptionHandler(HttpMessageConversionException.class)
	public ResponseEntity<StandardError> internalErrorConvertMessage(HttpMessageConversionException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		logger.error(MessageFormat.format("Request: {0} | cause: {1} | raised: {2}", request.getRequestURI(), e.toString(), e.getStackTrace()[0]));
		err.setError("Error to convert message.");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		logger.error(MessageFormat.format("Request: {0} | cause: {1} | raised: {2}", request.getRequestURI(), e.toString(), e.getStackTrace()[0]));
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		logger.error(MessageFormat.format("Request: {0} | cause: {1} | raised: {2}", request.getRequestURI(), e.toString(), e.getStackTrace()[0]));
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Validation exception");
		logger.error(MessageFormat.format("Request: {0} | cause: {1} | raised: {2}", request.getRequestURI(), e.toString(), e.getStackTrace()[0]));
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Bad request");
		logger.error(MessageFormat.format("Request: {0} | cause: {1} | raised: {2}", request.getRequestURI(), e.toString(), e.getStackTrace()[0]));
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(InvalideMatrixException.class)
	public ResponseEntity<StandardError> InvalideMatrixException(InvalideMatrixException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Matrix invalide: No N x N");
		logger.error(MessageFormat.format("Request: {0} | cause: {1} | raised: {2}", request.getRequestURI(), e.toString(), e.getStackTrace()[0]));
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
