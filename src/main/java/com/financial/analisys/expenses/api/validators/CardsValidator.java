package com.financial.analisys.expenses.api.validators;

import com.financial.analisys.expenses.api.domain.Card;

public interface CardsValidator {

	public ValidationResult validate(Card card, ValidationType validationType);

}
