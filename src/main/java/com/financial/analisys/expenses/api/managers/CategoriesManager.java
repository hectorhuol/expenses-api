package com.financial.analisys.expenses.api.managers;

import java.util.List;

import com.financial.analisys.expenses.api.domain.Category;
import com.financial.analisys.expenses.api.exceptions.CategoryException;
import com.financial.analisys.expenses.api.gateways.CategoriesGateway;
import com.financial.analisys.expenses.api.validators.CategoriesValidator;
import com.financial.analisys.expenses.api.validators.ValidationResult;
import com.financial.analisys.expenses.api.validators.ValidationType;

public class CategoriesManager {

	private CategoriesGateway categoriesGateway;
	private CategoriesValidator categoriesValidator;

	private CategoriesManager() {
	}

	private CategoriesManager(CategoriesGateway categoriesGateway,
			CategoriesValidator categoriesValidator) {
		this.categoriesGateway = categoriesGateway;
		this.categoriesValidator = categoriesValidator;
	}

	public static CategoriesManager getNewCategoriesManager(
			CategoriesGateway categoriesGateway,
			CategoriesValidator categoriesValidator) {
		return new CategoriesManager(categoriesGateway, categoriesValidator);
	}

	public Category createCategory(Category category) {
		ValidationResult result = categoriesValidator.validate(category,
				ValidationType.CREATE);
		if (result.isValid())
			return categoriesGateway.createCategory(category);
		throw new CategoryException(result.getReason());
	}

	public void updateCategory(Category category) {
		ValidationResult result = categoriesValidator.validate(category,
				ValidationType.UPDATE);
		if (result.isValid())
			categoriesGateway.updateCategory(category);
		else
			throw new CategoryException(result.getReason());
	}

	public void deleteCategory(Category category) {
		ValidationResult result = categoriesValidator.validate(category,
				ValidationType.DELETE);
		if (result.isValid())
			categoriesGateway.deleteCategory(category);
		else
			throw new CategoryException(result.getReason());
	}

	public Category getCategory(Category category) {
		ValidationResult result = categoriesValidator.validate(category,
				ValidationType.READ);
		if (result.isValid())
			return categoriesGateway.getCategory(category);
		throw new CategoryException(result.getReason());
	}

	public List<Category> getAllCategories() {
		return categoriesGateway.getAllCategories();
	}
}