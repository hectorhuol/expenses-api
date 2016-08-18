package com.financial.analisys.expenses.api.gateways.mapimpl;

import java.util.ArrayList;
import java.util.List;

import com.financial.analisys.expenses.api.domain.User;
import com.financial.analisys.expenses.api.gateways.UsersGateway;

public class UsersGatewayImpl implements UsersGateway {

	public User createUser(User user) {
		if (user.getUserId() == null)
			user.setUserId(getNextUserId());
		Repository.usersRepository.put(user.getUserId(), user);
		Repository.saveUsersRepository();
		return user;
	}

	private String getNextUserId() {
		List<User> values = getValuesList();
		if(values==null || values.isEmpty())
			return "1";
		Integer nextId = Integer.valueOf(values.get(values.size() - 1)
				.getUserId()) + 1;
		return nextId.toString();
	}

	public void updateUser(User user) {
		Repository.usersRepository.replace(user.getUserId(), user);
		Repository.saveUsersRepository();
	}

	public void deleteUser(User user) {
		Repository.usersRepository.remove(user.getUserId());
		Repository.saveUsersRepository();
	}

	public User getUser(User user) {
		return Repository.usersRepository.get(user.getUserId());

	}

	public List<User> getAllUsers() {
		List<User> values = getValuesList();
		return values;
	}

	private List<User> getValuesList() {
		List<User> values = new ArrayList<User>(
				Repository.usersRepository.values());
		return values;
	}

}