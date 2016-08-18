package com.financial.analisys.expenses.api.validators;

import com.financial.analisys.expenses.api.domain.Expense;

public interface ExpensesValidator {

	public ValidationResult validate(Expense expense,
			ValidationType validationType);

}
