package com.financial.analisys.expenses.api.exceptions;

public class TechnicalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TechnicalException(Exception e) {
		super(e);
	}

	public TechnicalException(String message) {
		super(message);
	}
	
	public TechnicalException(String message, Exception e) {
		super(message, e);
	}

}
