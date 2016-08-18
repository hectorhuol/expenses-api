package com.financial.analisys.expenses.api.exceptions;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserException(Exception e) {
		super(e);
	}

	public UserException(String message) {
		super(message);
	}

}
