package com.financial.analisys.expenses.api.exceptions;

public class CardException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CardException(Exception e) {
		super(e);
	}

	public CardException(String message) {
		super(message);
	}

}
