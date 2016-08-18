package com.financial.analisys.expenses.api.validators;

import com.financial.analisys.expenses.api.domain.Category;

public interface CategoriesValidator {

	public ValidationResult validate(Category category,
			ValidationType validationType);

}
