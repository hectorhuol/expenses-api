package com.financial.analisys.expenses.api.validators.defimpl;

import com.financial.analisys.expenses.api.domain.Category;
import com.financial.analisys.expenses.api.validators.CategoriesValidator;
import com.financial.analisys.expenses.api.validators.ValidationResult;
import com.financial.analisys.expenses.api.validators.ValidationType;

public class CategoriesValidatorDefaultImpl implements CategoriesValidator {

	private ValidationResult validationResult = new ValidationResult();

	public ValidationResult validate(Category category,
			ValidationType validationType) {

		validationResult.setValid(true);

		switch (validationType) {
		case CREATE:
			if (isCategoryNull(category)) {
				setInvalidCategoryReason("Category should not be null");
			}
			break;
		case READ:
			if (isCategoryIdNullOrEmpty(category)) {
				setInvalidCategoryReason("Category Id should not be null or empty");
			}
			break;
		case UPDATE:
		case DELETE:
			if (isCategoryIdNullOrEmpty(category)) {
				setInvalidCategoryReason("Category Id should not be null or empty");
			}
			break;
		default:
			setInvalidCategoryReason("The validation type is not valid or is not configured");
			break;
		}

		return validationResult;
	}

	private void setInvalidCategoryReason(String message) {
		validationResult.setValid(false);
		validationResult.setReason(message);
	}

	private boolean isCategoryIdNullOrEmpty(Category category) {
		return category.getCategoryId() == null
				|| "".equals(category.getCategoryId());
	}

	private boolean isCategoryNull(Category category) {
		return category == null;
	}

}
