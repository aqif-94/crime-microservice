package com.app.crime.resources;

public class CrimeNotFoundException extends RuntimeException {

	public CrimeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CrimeNotFoundException(String message) {
		super(message);
	}

	public CrimeNotFoundException(Throwable cause) {
		super(cause);
	}

}
