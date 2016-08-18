package com.financial.analisys.expenses.api.gateways.mapimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.financial.analisys.expenses.api.domain.Category;
import com.financial.analisys.expenses.api.domain.Companion;
import com.financial.analisys.expenses.api.domain.Expense;
import com.financial.analisys.expenses.api.domain.User;
import com.financial.analisys.expenses.api.gateways.ExpensesReportsGateway;
import com.financial.analisys.expenses.api.utils.FinancialUtils;

public class ExpensesReportsGatewayImpl implements ExpensesReportsGateway {

	public List<Expense> getExpensesByCategoryByUser(Category category,
			User user) {
		List<Expense> values = new ArrayList<Expense>();

		values = getValuesList()
				.stream()
				.filter(expense -> expense.getCategory().equals(category)
						&& expense.getUser().equals(user))
				.collect(Collectors.toList());
		
		return values;
	}

	public List<Expense> getExpensesByCityByUser(String cityName, User user) {
		List<Expense> values = new ArrayList<Expense>();

		for (Expense expense : getValuesList()) {
			if (isCityEqual(cityName, expense) && isUserEqual(user, expense))
				values.add(expense);
		}

		return values;
	}

	private boolean isCityEqual(String cityName, Expense expense) {
		return expense.getCity().equals(cityName);
	}

	public List<Expense> getExpensesByCompanionsByUser(
			List<Companion> companions, User user) {
		List<Expense> values = new ArrayList<Expense>();

		for (Expense expense : getValuesList()) {
			if (hasSomeCompanion(expense.getCompanions(), companions)
					&& isUserEqual(user, expense))
				values.add(expense);
		}

		return values;
	}

	private boolean hasSomeCompanion(List<Companion> expenseCompanions,
			List<Companion> companions) {
		for (Companion companion : expenseCompanions)
			if (companions.contains(companion))
				return true;
		return false;
	}

	public List<Expense> getExpensesByMonthByUser(LocalDate month, User user) {
		List<Expense> values = new ArrayList<Expense>();

		for (Expense expense : getValuesList()) {
			if (isMonthEqual(month, expense) && isUserEqual(user, expense))
				values.add(expense);
		}

		return values;
	}

	private boolean isMonthEqual(LocalDate month, Expense expense) {
		return FinancialUtils.getLocalDateTime(expense.getDateAndHour())
				.getMonth().equals(month.getMonth());
	}

	public List<Expense> getExpensesByDayByUser(LocalDate day, User user) {
		List<Expense> values = new ArrayList<Expense>();

		for (Expense expense : getValuesList()) {
			if (isDayEqual(day, expense) && isUserEqual(user, expense))
				values.add(expense);
		}

		return values;
	}

	private boolean isDayEqual(LocalDate day, Expense expense) {
		return FinancialUtils.getLocalDateTime(expense.getDateAndHour())
				.toLocalDate().isEqual(day);
	}

	public List<Expense> getExpensesBetweenDatesByUser(LocalDateTime startDate,
			LocalDateTime finishDate, User user) {
		List<Expense> values = new ArrayList<Expense>();

		for (Expense expense : getValuesList()) {
			if (isDateBetweenStartAndFinishDates(startDate, finishDate, expense)
					&& isUserEqual(user, expense))
				values.add(expense);
		}

		return values;
	}

	private boolean isDateBetweenStartAndFinishDates(LocalDateTime startDate,
			LocalDateTime finishDate, Expense expense) {
		return FinancialUtils.getLocalDateTime(expense.getDateAndHour())
				.toLocalDate().isAfter(startDate.toLocalDate())
				&& FinancialUtils.getLocalDateTime(expense.getDateAndHour())
						.toLocalDate().isBefore(finishDate.toLocalDate())
				|| FinancialUtils.getLocalDateTime(expense.getDateAndHour())
						.toLocalDate().isEqual(startDate.toLocalDate())
				|| FinancialUtils.getLocalDateTime(expense.getDateAndHour())
						.toLocalDate().isEqual(finishDate.toLocalDate());
	}

	private List<Expense> getValuesList() {
		List<Expense> values = new ArrayList<Expense>(
				Repository.expensesRepository.values());
		return values;
	}

	private boolean isUserEqual(User user, Expense expense) {
		return expense.getUser().equals(user);
	}
}
