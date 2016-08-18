package com.financial.analisys.expenses.api.validators;

import com.financial.analisys.expenses.api.domain.Companion;

public interface CompanionsValidator {

	public ValidationResult validate(Companion companion,
			ValidationType validationType);

}
