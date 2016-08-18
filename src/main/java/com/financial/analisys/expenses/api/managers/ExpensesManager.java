package com.financial.analisys.expenses.api.managers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.financial.analisys.expenses.api.domain.Category;
import com.financial.analisys.expenses.api.domain.Companion;
import com.financial.analisys.expenses.api.domain.Expense;
import com.financial.analisys.expenses.api.domain.User;
import com.financial.analisys.expenses.api.exceptions.ExpenseException;
import com.financial.analisys.expenses.api.gateways.ExpensesGateway;
import com.financial.analisys.expenses.api.gateways.ExpensesReportsGateway;
import com.financial.analisys.expenses.api.validators.ExpensesValidator;
import com.financial.analisys.expenses.api.validators.ValidationResult;
import com.financial.analisys.expenses.api.validators.ValidationType;

public class ExpensesManager {

	private ExpensesGateway expensesGateway;
	private ExpensesReportsGateway expensesReportsGateway;
	private ExpensesValidator expensesValidator;

	private ExpensesManager() {
	}

	private ExpensesManager(ExpensesGateway expensesGateway,
			ExpensesReportsGateway expensesReportsGateway,
			ExpensesValidator expensesValidator) {
		this.expensesGateway = expensesGateway;
		this.expensesValidator = expensesValidator;
		this.expensesReportsGateway = expensesReportsGateway;
	}

	public static ExpensesManager getNewExpensesManager(
			ExpensesGateway expensesGateway,
			ExpensesReportsGateway expensesReportsGateway,
			ExpensesValidator expensesValidator) {
		return new ExpensesManager(expensesGateway, expensesReportsGateway,
				expensesValidator);
	}

	public Expense createExpense(Expense expense) {
		ValidationResult result = expensesValidator.validate(expense,
				ValidationType.CREATE);
		if (result.isValid())
			return expensesGateway.createExpense(expense);
		throw new ExpenseException(result.getReason());
	}

	public void updateExpense(Expense expense) {
		ValidationResult result = expensesValidator.validate(expense,
				ValidationType.UPDATE);
		if (result.isValid())
			expensesGateway.updateExpense(expense);
		else
			throw new ExpenseException(result.getReason());
	}

	public void deleteExpense(Expense expense) {
		ValidationResult result = expensesValidator.validate(expense,
				ValidationType.DELETE);
		if (result.isValid())
			expensesGateway.deleteExpense(expense);
		else
			throw new ExpenseException(result.getReason());
	}

	public Expense getExpense(Expense expense) {
		ValidationResult result = expensesValidator.validate(expense,
				ValidationType.READ);
		if (result.isValid())
			return expensesGateway.getExpense(expense);
		throw new ExpenseException(result.getReason());
	}
	
	public List<Expense> getAllExpenses() {
		return expensesGateway.getAllExpenses();
	}

	public List<Expense> getAllUserExpenses(User user) {
		return expensesGateway.getAllUserExpenses(user);
	}

	public List<Expense> getExpensesByCategoryByUser(Category category,
			User user) {
		return expensesReportsGateway.getExpensesByCategoryByUser(category,
				user);
	}

	public List<Expense> getExpensesByCityByUser(String cityName, User user) {
		return expensesReportsGateway.getExpensesByCityByUser(cityName, user);
	}

	public List<Expense> getExpensesByCompanionsByUser(
			List<Companion> companions, User user) {
		return expensesReportsGateway.getExpensesByCompanionsByUser(companions,
				user);
	}

	public List<Expense> getExpensesByMonthByUser(LocalDate month, User user) {
		return expensesReportsGateway.getExpensesByMonthByUser(month, user);
	}

	public List<Expense> getExpensesByDayByUser(LocalDate day, User user) {
		return expensesReportsGateway.getExpensesByDayByUser(day, user);
	}

	public List<Expense> getExpensesBetweenDatesByUser(LocalDateTime startDate,
			LocalDateTime finishDate, User user) {
		return expensesReportsGateway.getExpensesBetweenDatesByUser(startDate,
				finishDate, user);
	}
}