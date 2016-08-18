package com.financial.analisys.expenses.api.validators.defimpl;

import com.financial.analisys.expenses.api.domain.Companion;
import com.financial.analisys.expenses.api.validators.CompanionsValidator;
import com.financial.analisys.expenses.api.validators.ValidationResult;
import com.financial.analisys.expenses.api.validators.ValidationType;

public class CompanionsValidatorDefaultImpl implements CompanionsValidator {

	private ValidationResult validationResult = new ValidationResult();

	public ValidationResult validate(Companion companion,
			ValidationType validationType) {

		validationResult.setValid(true);

		switch (validationType) {
		case CREATE:
			if (isCompanionNull(companion)) {
				setInvalidCompanionReason("Companion should not be null");
			}
			break;
		case READ:
			if (isCompanionIdNullOrEmpty(companion)) {
				setInvalidCompanionReason("Companion Id should not be null or empty");
			}
			break;
		case UPDATE:
			if (isCompanionIdNullOrEmpty(companion)) {
				setInvalidCompanionReason("Companion Id should not be null or empty");
			}
			break;
		case DELETE:
			if (isCompanionIdNullOrEmpty(companion)) {
				setInvalidCompanionReason("Companion Id should not be null or empty");
			}
			break;
		default:
			setInvalidCompanionReason("The validation type is not valid or is not configured");
			break;
		}

		return validationResult;
	}

	private void setInvalidCompanionReason(String message) {
		validationResult.setValid(false);
		validationResult.setReason(message);
	}

	private boolean isCompanionIdNullOrEmpty(Companion companion) {
		return companion.getCompanionId() == null
				|| companion.getCompanionId().equals("");
	}

	private boolean isCompanionNull(Companion companion) {
		return companion == null;
	}

}
