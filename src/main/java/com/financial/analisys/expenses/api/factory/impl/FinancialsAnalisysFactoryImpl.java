package com.financial.analisys.expenses.api.factory.impl;

import com.financial.analisys.expenses.api.factory.FinancialsAnalisysFactory;
import com.financial.analisys.expenses.api.factory.GatewayType;
import com.financial.analisys.expenses.api.factory.ValidatorType;
import com.financial.analisys.expenses.api.gateways.CardsGateway;
import com.financial.analisys.expenses.api.gateways.CategoriesGateway;
import com.financial.analisys.expenses.api.gateways.CompanionsGateway;
import com.financial.analisys.expenses.api.gateways.ExpensesGateway;
import com.financial.analisys.expenses.api.gateways.ExpensesReportsGateway;
import com.financial.analisys.expenses.api.gateways.UsersGateway;
import com.financial.analisys.expenses.api.gateways.mapimpl.CardsGatewayImpl;
import com.financial.analisys.expenses.api.gateways.mapimpl.CategoriesGatewayImpl;
import com.financial.analisys.expenses.api.gateways.mapimpl.CompanionsGatewayImpl;
import com.financial.analisys.expenses.api.gateways.mapimpl.ExpensesGatewayImpl;
import com.financial.analisys.expenses.api.gateways.mapimpl.ExpensesReportsGatewayImpl;
import com.financial.analisys.expenses.api.gateways.mapimpl.UsersGatewayImpl;
import com.financial.analisys.expenses.api.validators.CardsValidator;
import com.financial.analisys.expenses.api.validators.CategoriesValidator;
import com.financial.analisys.expenses.api.validators.CompanionsValidator;
import com.financial.analisys.expenses.api.validators.ExpensesValidator;
import com.financial.analisys.expenses.api.validators.UsersValidator;
import com.financial.analisys.expenses.api.validators.defimpl.CardsValidatorDefaultImpl;
import com.financial.analisys.expenses.api.validators.defimpl.CategoriesValidatorDefaultImpl;
import com.financial.analisys.expenses.api.validators.defimpl.CompanionsValidatorDefaultImpl;
import com.financial.analisys.expenses.api.validators.defimpl.ExpensesValidatorDefaultImpl;
import com.financial.analisys.expenses.api.validators.defimpl.UsersValidatorDefaultImpl;

public class FinancialsAnalisysFactoryImpl implements FinancialsAnalisysFactory {

	public CardsGateway createCardsGateway(GatewayType gatewayType) {
		switch (gatewayType) {
		case MAP:
			return new CardsGatewayImpl();
		default:
			return null;
		}
	}

	public CategoriesGateway createCategoriesGateway(GatewayType gatewayType) {
		switch (gatewayType) {
		case MAP:
			return new CategoriesGatewayImpl();
		default:
			return null;
		}
	}

	public CompanionsGateway createCompanionsGateway(GatewayType gatewayType) {
		switch (gatewayType) {
		case MAP:
			return new CompanionsGatewayImpl();
		default:
			return null;
		}
	}

	public ExpensesGateway createExpensesGateway(GatewayType gatewayType) {
		switch (gatewayType) {
		case MAP:
			return new ExpensesGatewayImpl();
		default:
			return null;
		}
	}

	public ExpensesReportsGateway createExpensesReportsGateway(
			GatewayType gatewayType) {
		switch (gatewayType) {
		case MAP:
			return new ExpensesReportsGatewayImpl();
		default:
			return null;
		}
	}

	public UsersGateway createUsersGateway(GatewayType gatewayType) {
		switch (gatewayType) {
		case MAP:
			return new UsersGatewayImpl();
		default:
			return null;
		}
	}

	public UsersValidator createUsersValidator(ValidatorType validatorType) {
		switch (validatorType) {
		case DEFAULT:
			return new UsersValidatorDefaultImpl();
		default:
			return null;
		}
	}

	public CategoriesValidator createCategoriesValidator(
			ValidatorType validatorType) {
		switch (validatorType) {
		case DEFAULT:
			return new CategoriesValidatorDefaultImpl();
		default:
			return null;
		}
	}

	public CardsValidator createCardsValidator(ValidatorType validatorType) {
		switch (validatorType) {
		case DEFAULT:
			return new CardsValidatorDefaultImpl();
		default:
			return null;
		}
	}

	public CompanionsValidator createCompanionsValidator(
			ValidatorType validatorType) {
		switch (validatorType) {
		case DEFAULT:
			return new CompanionsValidatorDefaultImpl();
		default:
			return null;
		}
	}

	public ExpensesValidator createExpensesValidator(ValidatorType validatorType) {
		switch (validatorType) {
		case DEFAULT:
			return new ExpensesValidatorDefaultImpl();
		default:
			return null;
		}
	}

}