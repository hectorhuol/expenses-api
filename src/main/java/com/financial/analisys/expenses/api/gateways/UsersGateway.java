package com.financial.analisys.expenses.api.gateways;

import java.util.List;

import com.financial.analisys.expenses.api.domain.User;

public interface UsersGateway {

	public User createUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public User getUser(User user);

	public List<User> getAllUsers();

}
