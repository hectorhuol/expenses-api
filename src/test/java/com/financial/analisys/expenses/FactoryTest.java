package com.financial.analisys.expenses;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.financial.analisys.expenses.api.domain.Card;
import com.financial.analisys.expenses.api.domain.CardType;
import com.financial.analisys.expenses.api.domain.Category;
import com.financial.analisys.expenses.api.domain.Companion;
import com.financial.analisys.expenses.api.domain.Expense;
import com.financial.analisys.expenses.api.domain.User;
import com.financial.analisys.expenses.api.factory.FinancialsAnalisysFactory;
import com.financial.analisys.expenses.api.factory.GatewayType;
import com.financial.analisys.expenses.api.factory.impl.FinancialsAnalisysFactoryImpl;
import com.financial.analisys.expenses.api.gateways.CardsGateway;
import com.financial.analisys.expenses.api.gateways.CategoriesGateway;
import com.financial.analisys.expenses.api.gateways.CompanionsGateway;
import com.financial.analisys.expenses.api.gateways.ExpensesGateway;
import com.financial.analisys.expenses.api.gateways.ExpensesReportsGateway;
import com.financial.analisys.expenses.api.gateways.UsersGateway;
import com.financial.analisys.expenses.api.gateways.mapimpl.Repository;
import com.financial.analisys.expenses.api.utils.FinancialUtils;

public class FactoryTest {

	FinancialsAnalisysFactory financialsAnalisysFactory;
	CardsGateway cardsGateway;
	CategoriesGateway categoriesGateway;
	CompanionsGateway companionsGateway;
	ExpensesGateway expensesGateway;
	ExpensesReportsGateway expensesReportsGateway;
	UsersGateway usersGateway;
	Card card;
	Category category;
	Companion companion;
	Expense expense;
	User user;

	@Before
	public void setup() {
		initData();
	}

	private void initData() {
		financialsAnalisysFactory = new FinancialsAnalisysFactoryImpl();

		card = new Card();
		card.setCardId("1");
		card.setName("Name");
		card.setType(CardType.CREDIT);

		category = new Category();
		category.setCategoryId("1");
		category.setName("Name");

		companion = new Companion();
		companion.setCompanionId("1");
		companion.setName("Name");
		companion.setAlias("Alias");

		user = new User();
		user.setUserId("1");
		user.setUserName("Name");
		user.setPassword("1234");

		expense = new Expense();
		expense.setExpenseId("1");
		expense.setValue(10.0);
		expense.setDateAndHour(FinancialUtils
				.getLocalDateTimeString(LocalDateTime.now()));
		expense.setLocation("Location");
		expense.setCity("City");
		expense.setUser(user);
		expense.getCompanions().add(companion);
		expense.setCategory(category);
		expense.setCard(card);
		
		Repository
		.setPropertiesPath("src/test/resources/repository.properties");
	}

	@Test
	public void testCardsGateway() {
		cardsGateway = financialsAnalisysFactory
				.createCardsGateway(GatewayType.MAP);
		String newName = "NameTest";
		cardsGateway.createCard(card);
		card.setName(newName);
		cardsGateway.updateCard(card);
		card = cardsGateway.getCard(card);
		assertEquals(card.getName(), newName);
		cardsGateway.deleteCard(card);
		card = cardsGateway.getCard(card);
		assertEquals(card, null);
	}

	@Test
	public void testCategoriesGateway() {
		categoriesGateway = financialsAnalisysFactory
				.createCategoriesGateway(GatewayType.MAP);
		String newName = "NameTest";
		categoriesGateway.createCategory(category);
		category.setName(newName);
		categoriesGateway.updateCategory(category);
		category = categoriesGateway.getCategory(category);
		assertEquals(category.getName(), newName);
		categoriesGateway.deleteCategory(category);
		category = categoriesGateway.getCategory(category);
		assertEquals(category, null);
	}

	@Test
	public void testCompanionsGateway() {
		companionsGateway = financialsAnalisysFactory
				.createCompanionsGateway(GatewayType.MAP);
		String newName = "NameTest";
		companionsGateway.createCompanion(companion);
		companion.setName(newName);
		companionsGateway.updateCompanion(companion);
		companion = companionsGateway.getCompanion(companion);
		assertEquals(companion.getName(), newName);
		companionsGateway.deleteCompanion(companion);
		companion = companionsGateway.getCompanion(companion);
		assertEquals(companion, null);
	}

	@Test
	public void testUsersGateway() {
		usersGateway = financialsAnalisysFactory
				.createUsersGateway(GatewayType.MAP);
		String newName = "NameTest";
		usersGateway.createUser(user);
		user.setUserName(newName);
		usersGateway.updateUser(user);
		user = usersGateway.getUser(user);
		assertEquals(user.getUserName(), newName);
		usersGateway.deleteUser(user);
		user = usersGateway.getUser(user);
		assertEquals(user, null);
	}

	@Test
	public void testExpensesGateway() {
		expensesGateway = financialsAnalisysFactory
				.createExpensesGateway(GatewayType.MAP);
		String newCity = "CityTest";
		expensesGateway.createExpense(expense);
		expense.setCity(newCity);
		expensesGateway.updateExpense(expense);
		expense = expensesGateway.getExpense(expense);
		assertEquals(expense.getCity(), newCity);
		expensesGateway.deleteExpense(expense);
		expense = expensesGateway.getExpense(expense);
		assertEquals(expense, null);
	}

	@Test
	public void testExpensesReportsGateway() {
		expensesGateway = financialsAnalisysFactory
				.createExpensesGateway(GatewayType.MAP);
		expensesReportsGateway = financialsAnalisysFactory
				.createExpensesReportsGateway(GatewayType.MAP);
		expensesGateway.createExpense(expense);

		LocalDate now = LocalDate.now();
		LocalDateTime nowTime = LocalDateTime.now();

		List<Expense> expenses = expensesReportsGateway
				.getExpensesByMonthByUser(now, user);
		assertEquals(expense, expenses.get(0));
		expenses = expensesReportsGateway.getExpensesBetweenDatesByUser(
				nowTime.minus(1, ChronoUnit.DAYS),
				nowTime.plus(1, ChronoUnit.DAYS), user);
		assertEquals(expense, expenses.get(0));
		expenses = expensesReportsGateway.getExpensesByCategoryByUser(category,
				user);
		assertEquals(expense, expenses.get(0));
		expenses = expensesReportsGateway.getExpensesByCityByUser(
				expense.getCity(), user);
		assertEquals(expense, expenses.get(0));
		expenses = expensesReportsGateway.getExpensesByCompanionsByUser(
				expense.getCompanions(), user);
		assertEquals(expense, expenses.get(0));
		expenses = expensesReportsGateway.getExpensesByDayByUser(now, user);
		assertEquals(expense, expenses.get(0));
	}
}