package com.financial.analisys.expenses;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.financial.analisys.expenses.api.domain.Expense;
import com.financial.analisys.expenses.api.domain.User;
import com.financial.analisys.expenses.api.factory.FinancialsAnalisysFactory;
import com.financial.analisys.expenses.api.factory.GatewayType;
import com.financial.analisys.expenses.api.factory.ValidatorType;
import com.financial.analisys.expenses.api.gateways.ExpensesGateway;
import com.financial.analisys.expenses.api.gateways.ExpensesReportsGateway;
import com.financial.analisys.expenses.api.managers.ExpensesManager;
import com.financial.analisys.expenses.api.utils.FinancialUtils;
import com.financial.analisys.expenses.api.validators.ExpensesValidator;
import com.financial.analisys.expenses.api.validators.ValidationResult;
import com.financial.analisys.expenses.api.validators.ValidationType;

public class ExpenseTest {

	FinancialsAnalisysFactory financialsAnalisysFactory;
	ExpensesManager expensesManager;
	ExpensesGateway expensesGateway;
	ExpensesReportsGateway expensesReportsGateway;
	ExpensesValidator expensesValidator;
	Expense expense;
	User user;

	@Before
	public void setup() {
		initMocks();
		expensesManager = ExpensesManager.getNewExpensesManager(
				financialsAnalisysFactory
						.createExpensesGateway(GatewayType.MAP),
				financialsAnalisysFactory
						.createExpensesReportsGateway(GatewayType.MAP),
				financialsAnalisysFactory
						.createExpensesValidator(ValidatorType.DEFAULT));

	}

	private void initMocks() {
		financialsAnalisysFactory = mock(FinancialsAnalisysFactory.class);
		expensesGateway = mock(ExpensesGateway.class);
		expensesValidator = mock(ExpensesValidator.class);

		expense = new Expense();
		expense.setExpenseId("1");
		expense.setValue(10.0);
		expense.setDateAndHour(FinancialUtils.getLocalDateTimeString(LocalDateTime.now()));
		expense.setLocation("Location");
		expense.setCity("City");

		user = new User();
		user.setUserId("1");
		user.setUserName("Name");
		user.setPassword("1234");

		List<Expense> expenses = new ArrayList<>();
		expenses.add(expense);

		ValidationResult validationResult = new ValidationResult();
		validationResult.setValid(true);

		when(expensesGateway.createExpense(expense)).thenReturn(expense);
		when(expensesGateway.getExpense(expense)).thenReturn(expense);
		when(expensesGateway.getAllUserExpenses(user)).thenReturn(expenses);
		when(expensesValidator.validate(expense, ValidationType.CREATE))
				.thenReturn(validationResult);
		when(expensesValidator.validate(expense, ValidationType.READ))
				.thenReturn(validationResult);
		when(expensesValidator.validate(expense, ValidationType.UPDATE))
				.thenReturn(validationResult);
		when(expensesValidator.validate(expense, ValidationType.DELETE))
				.thenReturn(validationResult);
		when(financialsAnalisysFactory.createExpensesGateway(GatewayType.MAP))
				.thenReturn(expensesGateway);
		when(
				financialsAnalisysFactory
						.createExpensesValidator(ValidatorType.DEFAULT))
				.thenReturn(expensesValidator);
	}

	@Test
	public void testCreateExpense() {
		Expense createdExpense = expensesManager.createExpense(expense);
		assertEquals(expense.getValue(), createdExpense.getValue());
		assertEquals(expense.getDateAndHour(), createdExpense.getDateAndHour());
		assertEquals(expense.getLocation(), createdExpense.getLocation());
		assertEquals(expense.getCity(), createdExpense.getCity());
		assertEquals(expense, createdExpense);
		verify(expensesGateway, times(1)).createExpense(expense);
		verify(expensesValidator, times(1)).validate(expense,
				ValidationType.CREATE);
	}

	@Test
	public void testUpdateExpense() {
		expensesManager.updateExpense(expense);
		verify(expensesGateway, times(1)).updateExpense(expense);
		verify(expensesValidator, times(1)).validate(expense,
				ValidationType.UPDATE);
	}

	@Test
	public void testDeleteExpense() {
		expensesManager.deleteExpense(expense);
		verify(expensesGateway, times(1)).deleteExpense(expense);
		verify(expensesValidator, times(1)).validate(expense,
				ValidationType.DELETE);
	}

	@Test
	public void testGetExpense() {
		Expense queriedExpense = expensesManager.getExpense(expense);
		assertEquals(expense.getExpenseId(), queriedExpense.getExpenseId());
		assertEquals(expense.getValue(), queriedExpense.getValue());
		assertEquals(expense.getDateAndHour(), queriedExpense.getDateAndHour());
		assertEquals(expense.getLocation(), queriedExpense.getLocation());
		assertEquals(expense.getCity(), queriedExpense.getCity());
		assertEquals(expense, queriedExpense);
		verify(expensesGateway, times(1)).getExpense(expense);
		verify(expensesValidator, times(1)).validate(expense,
				ValidationType.READ);
	}

	@Test
	public void testGetAllExpenses() {
		Expense queriedExpense = expensesManager.getAllUserExpenses(user)
				.get(0);
		assertEquals(expensesManager.getAllUserExpenses(user).size(), 1);
		assertEquals(expense.getValue(), queriedExpense.getValue());
		assertEquals(expense.getDateAndHour(), queriedExpense.getDateAndHour());
		assertEquals(expense.getLocation(), queriedExpense.getLocation());
		assertEquals(expense.getCity(), queriedExpense.getCity());
		assertEquals(expense, queriedExpense);
		verify(expensesGateway, times(2)).getAllUserExpenses(user);
	}

}