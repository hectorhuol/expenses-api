package com.financial.analisys.expenses.api.managers;

import java.util.List;

import com.financial.analisys.expenses.api.domain.User;
import com.financial.analisys.expenses.api.exceptions.UserException;
import com.financial.analisys.expenses.api.gateways.UsersGateway;
import com.financial.analisys.expenses.api.validators.UsersValidator;
import com.financial.analisys.expenses.api.validators.ValidationResult;
import com.financial.analisys.expenses.api.validators.ValidationType;

public class UsersManager {

	private UsersGateway usersGateway;
	private UsersValidator usersValidator;

	private UsersManager() {
	}

	private UsersManager(UsersGateway usersGateway,
			UsersValidator usersValidator) {
		this.usersGateway = usersGateway;
		this.usersValidator = usersValidator;
	}

	public static UsersManager getNewUsersManager(UsersGateway usersGateway,
			UsersValidator usersValidator) {
		return new UsersManager(usersGateway, usersValidator);
	}

	public User createUser(User user) {
		ValidationResult result = usersValidator.validate(user,
				ValidationType.CREATE);
		if (result.isValid())
			return usersGateway.createUser(user);
		throw new UserException(result.getReason());
	}

	public void updateUser(User user) {
		ValidationResult result = usersValidator.validate(user,
				ValidationType.UPDATE);
		if (result.isValid())
			usersGateway.updateUser(user);
		else
			throw new UserException(result.getReason());
	}

	public void deleteUser(User user) {
		ValidationResult result = usersValidator.validate(user,
				ValidationType.DELETE);
		if (result.isValid())
			usersGateway.deleteUser(user);
		else
			throw new UserException(result.getReason());
	}

	public User getUser(User user) {
		ValidationResult result = usersValidator.validate(user,
				ValidationType.READ);
		if (result.isValid())
			return usersGateway.getUser(user);
		throw new UserException(result.getReason());
	}

	public List<User> getAllUsers() {
		return usersGateway.getAllUsers();
	}
}