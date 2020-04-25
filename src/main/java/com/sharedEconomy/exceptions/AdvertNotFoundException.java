package com.sharedEconomy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AdvertNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AdvertNotFoundException(String message) {
		super(message);
	}
}
