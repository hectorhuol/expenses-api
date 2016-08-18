package com.financial.analisys.expenses.api.factory;

import com.financial.analisys.expenses.api.gateways.CardsGateway;
import com.financial.analisys.expenses.api.gateways.CategoriesGateway;
import com.financial.analisys.expenses.api.gateways.CompanionsGateway;
import com.financial.analisys.expenses.api.gateways.ExpensesGateway;
import com.financial.analisys.expenses.api.gateways.ExpensesReportsGateway;
import com.financial.analisys.expenses.api.gateways.UsersGateway;
import com.financial.analisys.expenses.api.validators.CardsValidator;
import com.financial.analisys.expenses.api.validators.CategoriesValidator;
import com.financial.analisys.expenses.api.validators.CompanionsValidator;
import com.financial.analisys.expenses.api.validators.ExpensesValidator;
import com.financial.analisys.expenses.api.validators.UsersValidator;

public interface FinancialsAnalisysFactory {

	public CardsGateway createCardsGateway(GatewayType cardsGatewayType);

	public CategoriesGateway createCategoriesGateway(
			GatewayType categoriesGatewayType);

	public CompanionsGateway createCompanionsGateway(
			GatewayType companionsGatewayType);

	public ExpensesGateway createExpensesGateway(GatewayType expensesGatewayType);

	public ExpensesReportsGateway createExpensesReportsGateway(
			GatewayType expensesReportsGatewayType);

	public UsersGateway createUsersGateway(GatewayType usersGatewayType);

	public UsersValidator createUsersValidator(ValidatorType validatorType);

	public CategoriesValidator createCategoriesValidator(
			ValidatorType validatorType);

	public CardsValidator createCardsValidator(ValidatorType validatorType);

	public CompanionsValidator createCompanionsValidator(
			ValidatorType validatorType);

	public ExpensesValidator createExpensesValidator(ValidatorType validatorType);

}
