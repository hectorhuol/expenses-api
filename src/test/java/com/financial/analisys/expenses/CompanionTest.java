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

import com.financial.analisys.expenses.api.domain.Companion;
import com.financial.analisys.expenses.api.factory.FinancialsAnalisysFactory;
import com.financial.analisys.expenses.api.factory.GatewayType;
import com.financial.analisys.expenses.api.factory.ValidatorType;
import com.financial.analisys.expenses.api.gateways.CompanionsGateway;
import com.financial.analisys.expenses.api.managers.CompanionsManager;
import com.financial.analisys.expenses.api.validators.CompanionsValidator;
import com.financial.analisys.expenses.api.validators.ValidationResult;
import com.financial.analisys.expenses.api.validators.ValidationType;

public class CompanionTest {

	FinancialsAnalisysFactory financialsAnalisysFactory;
	CompanionsManager companionsManager;
	CompanionsGateway companionsGateway;
	CompanionsValidator companionsValidator;
	Companion companion;

	@Before
	public void setup() {
		initMocks();
		companionsManager = CompanionsManager.getNewCompanionsManager(
				financialsAnalisysFactory
						.createCompanionsGateway(GatewayType.MAP),
				financialsAnalisysFactory
						.createCompanionsValidator(ValidatorType.DEFAULT));
	}

	private void initMocks() {
		financialsAnalisysFactory = mock(FinancialsAnalisysFactory.class);
		companionsGateway = mock(CompanionsGateway.class);
		companionsValidator = mock(CompanionsValidator.class);

		companion = new Companion();
		companion.setCompanionId("1");
		companion.setName("Name");
		companion.setAlias("Alias");

		List<Companion> companions = new ArrayList<>();
		companions.add(companion);

		ValidationResult validationResult = new ValidationResult();
		validationResult.setValid(true);

		when(companionsGateway.createCompanion(companion))
				.thenReturn(companion);
		when(companionsGateway.getCompanion(companion)).thenReturn(companion);
		when(companionsGateway.getAllCompanions()).thenReturn(companions);
		when(companionsValidator.validate(companion, ValidationType.CREATE))
				.thenReturn(validationResult);
		when(companionsValidator.validate(companion, ValidationType.READ))
				.thenReturn(validationResult);
		when(companionsValidator.validate(companion, ValidationType.UPDATE))
				.thenReturn(validationResult);
		when(companionsValidator.validate(companion, ValidationType.DELETE))
				.thenReturn(validationResult);
		when(financialsAnalisysFactory.createCompanionsGateway(GatewayType.MAP))
				.thenReturn(companionsGateway);
		when(financialsAnalisysFactory
						.createCompanionsValidator(ValidatorType.DEFAULT))
				.thenReturn(companionsValidator);
	}

	@Test
	public void testCreateCompanion() {
		Companion createdCompanion = companionsManager
				.createCompanion(companion);
		assertEquals(companion.getName(), createdCompanion.getName());
		assertEquals(companion.getAlias(), createdCompanion.getAlias());
		assertEquals(companion, createdCompanion);
		verify(companionsGateway, times(1)).createCompanion(companion);
		verify(companionsValidator, times(1)).validate(companion,
				ValidationType.CREATE);
	}

	@Test
	public void testUpdateCompanion() {
		companionsManager.updateCompanion(companion);
		verify(companionsGateway, times(1)).updateCompanion(companion);
		verify(companionsValidator, times(1)).validate(companion,
				ValidationType.UPDATE);
	}

	@Test
	public void testDeleteCompanion() {
		companionsManager.deleteCompanion(companion);
		verify(companionsGateway, times(1)).deleteCompanion(companion);
		verify(companionsValidator, times(1)).validate(companion,
				ValidationType.DELETE);
	}

	@Test
	public void testGetCompanion() {
		Companion queriedCompanion = companionsManager.getCompanion(companion);
		assertEquals(companion.getCompanionId(),
				queriedCompanion.getCompanionId());
		assertEquals(companion.getName(), queriedCompanion.getName());
		assertEquals(companion.getAlias(), queriedCompanion.getAlias());
		assertEquals(companion, queriedCompanion);
		verify(companionsGateway, times(1)).getCompanion(companion);
		verify(companionsValidator, times(1)).validate(companion,
				ValidationType.READ);
	}

	@Test
	public void testGetAllCompanions() {
		Companion queriedCompanion = companionsManager.getAllCompanions()
				.get(0);
		assertEquals(companionsManager.getAllCompanions().size(), 1);
		assertEquals(companion.getCompanionId(),
				queriedCompanion.getCompanionId());
		assertEquals(companion.getName(), queriedCompanion.getName());
		assertEquals(companion.getAlias(), queriedCompanion.getAlias());
		assertEquals(companion, queriedCompanion);
		verify(companionsGateway, times(2)).getAllCompanions();
	}

}