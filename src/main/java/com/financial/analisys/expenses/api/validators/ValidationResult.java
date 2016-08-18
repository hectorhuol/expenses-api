package com.financial.analisys.expenses.api.validators;

public final class ValidationResult {
	
	private boolean valid;
	private String reason;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean result) {
		this.valid = result;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
