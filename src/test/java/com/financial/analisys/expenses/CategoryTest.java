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

import com.financial.analisys.expenses.api.domain.Category;
import com.financial.analisys.expenses.api.factory.FinancialsAnalisysFactory;
import com.financial.analisys.expenses.api.factory.GatewayType;
import com.financial.analisys.expenses.api.factory.ValidatorType;
import com.financial.analisys.expenses.api.gateways.CategoriesGateway;
import com.financial.analisys.expenses.api.managers.CategoriesManager;
import com.financial.analisys.expenses.api.validators.CategoriesValidator;
import com.financial.analisys.expenses.api.validators.ValidationResult;
import com.financial.analisys.expenses.api.validators.ValidationType;

public class CategoryTest {

	FinancialsAnalisysFactory financialsAnalisysFactory;
	CategoriesManager categoriesManager;
	CategoriesGateway categoriesGateway;
	CategoriesValidator categoriesValidator;
	Category category;

	@Before
	public void setup() {
		initMocks();
		categoriesManager = CategoriesManager.getNewCategoriesManager(
				financialsAnalisysFactory
						.createCategoriesGateway(GatewayType.MAP),
				financialsAnalisysFactory
						.createCategoriesValidator(ValidatorType.DEFAULT));
	}

	private void initMocks() {
		financialsAnalisysFactory = mock(FinancialsAnalisysFactory.class);
		categoriesGateway = mock(CategoriesGateway.class);
		categoriesValidator = mock(CategoriesValidator.class);

		category = new Category();
		category.setCategoryId("1");
		category.setName("Name");

		List<Category> categories = new ArrayList<>();
		categories.add(category);

		ValidationResult validationResult = new ValidationResult();
		validationResult.setValid(true);

		when(categoriesGateway.createCategory(category)).thenReturn(category);
		when(categoriesGateway.getCategory(category)).thenReturn(category);
		when(categoriesGateway.getAllCategories()).thenReturn(categories);
		when(categoriesValidator.validate(category, ValidationType.CREATE))
				.thenReturn(validationResult);
		when(categoriesValidator.validate(category, ValidationType.READ))
				.thenReturn(validationResult);
		when(categoriesValidator.validate(category, ValidationType.UPDATE))
				.thenReturn(validationResult);
		when(categoriesValidator.validate(category, ValidationType.DELETE))
				.thenReturn(validationResult);
		when(financialsAnalisysFactory.createCategoriesGateway(GatewayType.MAP))
				.thenReturn(categoriesGateway);
		when(financialsAnalisysFactory
						.createCategoriesValidator(ValidatorType.DEFAULT))
				.thenReturn(categoriesValidator);
	}

	@Test
	public void testCreateCategory() {
		Category createdCategory = categoriesManager.createCategory(category);
		assertEquals(category.getName(), createdCategory.getName());
		assertEquals(category, createdCategory);
		verify(categoriesGateway, times(1)).createCategory(category);
		verify(categoriesValidator, times(1)).validate(category,
				ValidationType.CREATE);
	}

	@Test
	public void testUpdateCategory() {
		categoriesManager.updateCategory(category);
		verify(categoriesGateway, times(1)).updateCategory(category);
		verify(categoriesValidator, times(1)).validate(category,
				ValidationType.UPDATE);
	}

	@Test
	public void testDeleteCategory() {
		categoriesManager.deleteCategory(category);
		verify(categoriesGateway, times(1)).deleteCategory(category);
		verify(categoriesValidator, times(1)).validate(category,
				ValidationType.DELETE);
	}

	@Test
	public void testGetCategory() {
		Category queriedCategory = categoriesManager.getCategory(category);
		assertEquals(category.getCategoryId(), queriedCategory.getCategoryId());
		assertEquals(category.getName(), queriedCategory.getName());
		assertEquals(category, queriedCategory);
		verify(categoriesGateway, times(1)).getCategory(category);
		verify(categoriesValidator, times(1)).validate(category,
				ValidationType.READ);
	}

	@Test
	public void testGetAllCategorys() {
		Category queriedCategory = categoriesManager.getAllCategories().get(0);
		assertEquals(categoriesManager.getAllCategories().size(), 1);
		assertEquals(category.getCategoryId(), queriedCategory.getCategoryId());
		assertEquals(category.getName(), queriedCategory.getName());
		assertEquals(category, queriedCategory);
		verify(categoriesGateway, times(2)).getAllCategories();
	}

}