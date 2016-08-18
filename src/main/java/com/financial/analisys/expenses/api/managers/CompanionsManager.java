package com.financial.analisys.expenses.api.managers;

import java.util.List;

import com.financial.analisys.expenses.api.domain.Companion;
import com.financial.analisys.expenses.api.exceptions.CompanionException;
import com.financial.analisys.expenses.api.gateways.CompanionsGateway;
import com.financial.analisys.expenses.api.validators.CompanionsValidator;
import com.financial.analisys.expenses.api.validators.ValidationResult;
import com.financial.analisys.expenses.api.validators.ValidationType;

public class CompanionsManager {

	private CompanionsGateway companionsGateway;
	private CompanionsValidator companionsValidator;

	private CompanionsManager() {
	}

	private CompanionsManager(CompanionsGateway companionsGateway,
			CompanionsValidator companionsValidator) {
		this.companionsGateway = companionsGateway;
		this.companionsValidator = companionsValidator;
	}

	public static CompanionsManager getNewCompanionsManager(
			CompanionsGateway companionsGateway,
			CompanionsValidator companionsValidator) {
		return new CompanionsManager(companionsGateway, companionsValidator);
	}

	public Companion createCompanion(Companion companion) {
		ValidationResult result = companionsValidator.validate(companion,
				ValidationType.CREATE);
		if (result.isValid())
			return companionsGateway.createCompanion(companion);
		throw new CompanionException(result.getReason());
	}

	public void updateCompanion(Companion companion) {
		ValidationResult result = companionsValidator.validate(companion,
				ValidationType.UPDATE);
		if (result.isValid())
			companionsGateway.updateCompanion(companion);
		else
			throw new CompanionException(result.getReason());
	}

	public void deleteCompanion(Companion companion) {
		ValidationResult result = companionsValidator.validate(companion,
				ValidationType.DELETE);
		if (result.isValid())
			companionsGateway.deleteCompanion(companion);
		else
			throw new CompanionException(result.getReason());
	}

	public Companion getCompanion(Companion companion) {
		ValidationResult result = companionsValidator.validate(companion,
				ValidationType.READ);
		if (result.isValid())
			return companionsGateway.getCompanion(companion);
		throw new CompanionException(result.getReason());
	}

	public List<Companion> getAllCompanions() {
		return companionsGateway.getAllCompanions();
	}
}