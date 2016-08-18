package com.financial.analisys.expenses;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.financial.analisys.expenses.api.domain.User;
import com.financial.analisys.expenses.api.factory.FinancialsAnalisysFactory;
import com.financial.analisys.expenses.api.factory.GatewayType;
import com.financial.analisys.expenses.api.factory.ValidatorType;
import com.financial.analisys.expenses.api.gateways.UsersGateway;
import com.financial.analisys.expenses.api.managers.UsersManager;
import com.financial.analisys.expenses.api.validators.UsersValidator;
import com.financial.analisys.expenses.api.validators.ValidationResult;
import com.financial.analisys.expenses.api.validators.ValidationType;

public class UsertTest {

	FinancialsAnalisysFactory financialsAnalisysFactory;
	UsersManager usersManager;
	UsersGateway usersGateway;
	UsersValidator usersValidator;
	User user;

	@Before
	public void setup() {
		initMocks();
		usersManager = UsersManager.getNewUsersManager(
				financialsAnalisysFactory
						.createUsersGateway(GatewayType.MAP),
				financialsAnalisysFactory
						.createUsersValidator(ValidatorType.DEFAULT));
	}

	private void initMocks() {
		financialsAnalisysFactory = mock(FinancialsAnalisysFactory.class);
		usersGateway = mock(UsersGateway.class);
		usersValidator = mock(UsersValidator.class);

		user = new User();
		user.setUserId("1");
		user.setUserName("Name");
		user.setPassword("1234");

		List<User> users = new ArrayList<User>();
		users.add(user);

		ValidationResult validationResult = new ValidationResult();
		validationResult.setValid(true);

		when(usersGateway.createUser(user)).thenReturn(user);
		when(usersGateway.getUser(user)).thenReturn(user);
		when(usersGateway.getAllUsers()).thenReturn(users);
		when(usersValidator.validate(user, ValidationType.CREATE)).thenReturn(
				validationResult);
		when(usersValidator.validate(user, ValidationType.READ)).thenReturn(
				validationResult);
		when(usersValidator.validate(user, ValidationType.UPDATE)).thenReturn(
				validationResult);
		when(usersValidator.validate(user, ValidationType.DELETE)).thenReturn(
				validationResult);
		when(financialsAnalisysFactory.createUsersGateway(GatewayType.MAP))
				.thenReturn(usersGateway);
		when(financialsAnalisysFactory
						.createUsersValidator(ValidatorType.DEFAULT))
				.thenReturn(usersValidator);
	}

	@Test
	public void testCreateUser() {
		User createdUser = usersManager.createUser(user);
		assertEquals(user.getUserName(), createdUser.getUserName());
		assertEquals(user.getPassword(), createdUser.getPassword());
		assertEquals(user, createdUser);
		verify(usersGateway, times(1)).createUser(user);
		verify(usersValidator, times(1)).validate(user, ValidationType.CREATE);
	}

	@Test
	public void testUpdateUser() {
		usersManager.updateUser(user);
		verify(usersGateway, times(1)).updateUser(user);
		verify(usersValidator, times(1)).validate(user, ValidationType.UPDATE);
	}

	@Test
	public void testDeleteUser() {
		usersManager.deleteUser(user);
		verify(usersGateway, times(1)).deleteUser(user);
		verify(usersValidator, times(1)).validate(user, ValidationType.DELETE);
	}

	@Test
	public void testGetUser() {
		User queriedUser = usersManager.getUser(user);
		assertEquals(user.getUserId(), queriedUser.getUserId());
		assertEquals(user.getUserName(), queriedUser.getUserName());
		assertEquals(user.getPassword(), queriedUser.getPassword());
		assertEquals(user, queriedUser);
		verify(usersGateway, times(1)).getUser(user);
		verify(usersValidator, times(1)).validate(user, ValidationType.READ);
	}

	@Test
	public void testGetAllUsers() {
		User queriedUser = usersManager.getAllUsers().get(0);
		assertEquals(usersManager.getAllUsers().size(), 1);
		assertEquals(user.getUserId(), queriedUser.getUserId());
		assertEquals(user.getUserName(), queriedUser.getUserName());
		assertEquals(user.getPassword(), queriedUser.getPassword());
		assertEquals(user, queriedUser);
		verify(usersGateway, times(2)).getAllUsers();
	}

}
