package com.financial.analisys.expenses.api.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Expense {

	private String expenseId;
	private Double value;
	private String dateAndHour;
	private List<Companion> companions;
	private Card card;
	private Category category;
	private User user;
	private String location;
	private String city;

	public String getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getDateAndHour() {
		return dateAndHour;
	}

	public void setDateAndHour(String dateAndHour) {
		this.dateAndHour = dateAndHour;
	}

	public List<Companion> getCompanions() {
		if (companions != null)
			return companions;
		else
			companions = new ArrayList<>();
		return companions;
	}

	public void setCompanions(List<Companion> companions) {
		this.companions = companions;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Expense) {
			Expense expense = (Expense) obj;
			return new EqualsBuilder()
					.append(this.expenseId, expense.expenseId).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(expenseId).toHashCode();
	}
}