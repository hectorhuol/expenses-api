package com.financial.analisys.expenses.api.gateways;

import java.util.List;

import com.financial.analisys.expenses.api.domain.Expense;
import com.financial.analisys.expenses.api.domain.User;

public interface ExpensesGateway {

	public Expense createExpense(Expense expense);

	public void updateExpense(Expense expense);

	public void deleteExpense(Expense expense);

	public Expense getExpense(Expense expense);

	public List<Expense> getAllExpenses();
	
	public List<Expense> getAllUserExpenses(User user);
}
