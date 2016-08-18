package com.financial.analisys.expenses.api.gateways;

import java.util.List;

import com.financial.analisys.expenses.api.domain.Category;

public interface CategoriesGateway {

	public Category createCategory(Category category);

	public void updateCategory(Category category);

	public void deleteCategory(Category category);

	public Category getCategory(Category category);

	public List<Category> getAllCategories();

}
