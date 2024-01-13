package com.jsp.ums.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotFoundException extends RuntimeException implements ExceptionInterface {
	private String message;

}
