package com.financial.analisys.expenses.api.gateways.mapimpl;

import java.util.ArrayList;
import java.util.List;

import com.financial.analisys.expenses.api.domain.Expense;
import com.financial.analisys.expenses.api.domain.User;
import com.financial.analisys.expenses.api.gateways.ExpensesGateway;

public class ExpensesGatewayImpl implements ExpensesGateway {

	public Expense createExpense(Expense expense) {
		if (expense.getExpenseId() == null)
			expense.setExpenseId(getNextExpenseId());
		Repository.expensesRepository.put(expense.getExpenseId(), expense);
		Repository.saveExpensesRepository();
		return expense;
	}

	private String getNextExpenseId() {
		List<Expense> values = getValuesList();
		if (values == null || values.isEmpty())
			return "1";
		Integer nextId = Integer.valueOf(values.get(values.size() - 1)
				.getExpenseId()) + 1;
		return nextId.toString();
	}

	public void updateExpense(Expense expense) {
		Repository.expensesRepository.replace(expense.getExpenseId(), expense);
		Repository.saveExpensesRepository();
	}

	public void deleteExpense(Expense expense) {
		Repository.expensesRepository.remove(expense.getExpenseId());
		Repository.saveExpensesRepository();
	}

	public Expense getExpense(Expense expense) {
		return Repository.expensesRepository.get(expense.getExpenseId());

	}

	public List<Expense> getAllExpenses() {
		return getValuesList();
	}

	public List<Expense> getAllUserExpenses(User user) {
		List<Expense> values = new ArrayList<Expense>();

		for (Expense expense : getValuesList()) {
			if (expense.getUser().equals(user))
				values.add(expense);
		}

		return values;
	}

	private List<Expense> getValuesList() {
		return new ArrayList<>(Repository.expensesRepository.values());
	}

}