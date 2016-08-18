package com.financial.analisys.expenses.api.exceptions;

public class CompanionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompanionException(Exception e) {
		super(e);
	}

	public CompanionException(String message) {
		super(message);
	}

}
