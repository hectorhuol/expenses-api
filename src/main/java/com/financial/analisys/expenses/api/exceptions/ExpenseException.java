package com.financial.analisys.expenses.api.exceptions;

public class ExpenseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExpenseException(Exception e) {
		super(e);
	}

	public ExpenseException(String message) {
		super(message);
	}

}
