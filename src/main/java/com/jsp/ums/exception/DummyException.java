package com.jsp.ums.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DummyException extends RuntimeException implements ExceptionInterface {
	private String message;

}
