package com.jsp.ums.util;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ums.exception.UserNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	private ResponseEntity<Object> structure(HttpStatus status, String message, String rootCause) {
		return new ResponseEntity<Object>(Map.of("status", status.value(), "message", message, "rootCause", rootCause),
				status);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
		return new ResponseEntity<Object>(
				structure(HttpStatus.NOT_FOUND, ex.getMessage(), "user not found with the given Id"),
				HttpStatus.NOT_FOUND);
	}

}
