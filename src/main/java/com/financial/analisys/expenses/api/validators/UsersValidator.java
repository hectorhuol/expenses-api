package com.financial.analisys.expenses.api.validators;

import com.financial.analisys.expenses.api.domain.User;

public interface UsersValidator {

	public ValidationResult validate(User user, ValidationType validationType);

}
