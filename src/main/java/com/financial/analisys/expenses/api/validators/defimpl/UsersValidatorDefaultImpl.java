package com.financial.analisys.expenses.api.validators.defimpl;

import com.financial.analisys.expenses.api.domain.User;
import com.financial.analisys.expenses.api.validators.UsersValidator;
import com.financial.analisys.expenses.api.validators.ValidationResult;
import com.financial.analisys.expenses.api.validators.ValidationType;

public class UsersValidatorDefaultImpl implements UsersValidator {

	private ValidationResult validationResult = new ValidationResult();

	public ValidationResult validate(User user, ValidationType validationType) {

		validationResult.setValid(true);

		switch (validationType) {
		case CREATE:
			if (isUserNull(user)) {
				setInvalidUserReason("User should not be null");
			}
			break;
		case READ:
			if (isUserIdNullOrEmpty(user)) {
				setInvalidUserReason("User Id should not be null or empty");
			}
			break;
		case UPDATE:
			if (isUserIdNullOrEmpty(user)) {
				setInvalidUserReason("User Id should not be null or empty");
			}
			break;
		case DELETE:
			if (isUserIdNullOrEmpty(user)) {
				setInvalidUserReason("User Id should not be null or empty");
			}
			break;
		default:
			setInvalidUserReason("The validation type is not valid or is not configured");
			break;
		}

		return validationResult;
	}

	private void setInvalidUserReason(String message) {
		validationResult.setValid(false);
		validationResult.setReason(message);
	}

	private boolean isUserIdNullOrEmpty(User user) {
		return user.getUserId() == null || user.getUserId().equals("");
	}

	private boolean isUserNull(User user) {
		return user == null;
	}

}
