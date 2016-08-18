package com.financial.analisys.expenses.api.exceptions;

public class CategoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoryException(Exception e) {
		super(e);
	}

	public CategoryException(String message) {
		super(message);
	}

}
