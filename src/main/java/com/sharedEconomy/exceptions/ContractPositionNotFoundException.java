package com.sharedEconomy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContractPositionNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ContractPositionNotFoundException(String message) {
		super(message);
	}
}
