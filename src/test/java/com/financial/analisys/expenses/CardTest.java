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

import com.financial.analisys.expenses.api.domain.Card;
import com.financial.analisys.expenses.api.domain.CardType;
import com.financial.analisys.expenses.api.factory.FinancialsAnalisysFactory;
import com.financial.analisys.expenses.api.factory.GatewayType;
import com.financial.analisys.expenses.api.factory.ValidatorType;
import com.financial.analisys.expenses.api.gateways.CardsGateway;
import com.financial.analisys.expenses.api.managers.CardsManager;
import com.financial.analisys.expenses.api.validators.CardsValidator;
import com.financial.analisys.expenses.api.validators.ValidationResult;
import com.financial.analisys.expenses.api.validators.ValidationType;

public class CardTest {

	FinancialsAnalisysFactory financialsAnalisysFactory;
	CardsManager cardsManager;
	CardsGateway cardsGateway;
	CardsValidator cardsValidator;
	Card card;

	@Before
	public void setup() {
		initMocks();
		cardsManager = CardsManager.getNewCardsManager(
				financialsAnalisysFactory.createCardsGateway(GatewayType.MAP),
				financialsAnalisysFactory
						.createCardsValidator(ValidatorType.DEFAULT));
	}

	private void initMocks() {
		financialsAnalisysFactory = mock(FinancialsAnalisysFactory.class);
		cardsGateway = mock(CardsGateway.class);
		cardsValidator = mock(CardsValidator.class);

		card = new Card();
		card.setCardId("1");
		card.setName("Name");
		card.setType(CardType.CREDIT);

		List<Card> cards = new ArrayList<>();
		cards.add(card);

		ValidationResult validationResult = new ValidationResult();
		validationResult.setValid(true);

		when(cardsGateway.createCard(card)).thenReturn(card);
		when(cardsGateway.getCard(card)).thenReturn(card);
		when(cardsGateway.getAllCards()).thenReturn(cards);
		when(cardsValidator.validate(card, ValidationType.CREATE)).thenReturn(
				validationResult);
		when(cardsValidator.validate(card, ValidationType.READ)).thenReturn(
				validationResult);
		when(cardsValidator.validate(card, ValidationType.UPDATE)).thenReturn(
				validationResult);
		when(cardsValidator.validate(card, ValidationType.DELETE)).thenReturn(
				validationResult);
		when(financialsAnalisysFactory.createCardsGateway(GatewayType.MAP))
				.thenReturn(cardsGateway);
		when(financialsAnalisysFactory
						.createCardsValidator(ValidatorType.DEFAULT))
				.thenReturn(cardsValidator);
	}

	@Test
	public void testCreateCard() {
		Card createdCard = cardsManager.createCard(card);
		assertEquals(card.getName(), createdCard.getName());
		assertEquals(card.getType(), createdCard.getType());
		assertEquals(card, createdCard);
		verify(cardsGateway, times(1)).createCard(card);
		verify(cardsValidator, times(1)).validate(card, ValidationType.CREATE);
	}

	@Test
	public void testUpdateCard() {
		cardsManager.updateCard(card);
		verify(cardsGateway, times(1)).updateCard(card);
		verify(cardsValidator, times(1)).validate(card, ValidationType.UPDATE);
	}

	@Test
	public void testDeleteCard() {
		cardsManager.deleteCard(card);
		verify(cardsGateway, times(1)).deleteCard(card);
		verify(cardsValidator, times(1)).validate(card, ValidationType.DELETE);
	}

	@Test
	public void testGetCard() {
		Card queriedCard = cardsManager.getCard(card);
		assertEquals(card.getCardId(), queriedCard.getCardId());
		assertEquals(card.getName(), queriedCard.getName());
		assertEquals(card.getType(), queriedCard.getType());
		assertEquals(card, queriedCard);
		verify(cardsGateway, times(1)).getCard(card);
		verify(cardsValidator, times(1)).validate(card, ValidationType.READ);
	}

	@Test
	public void testGetAllCards() {
		Card queriedCard = cardsManager.getAllCards().get(0);
		assertEquals(cardsManager.getAllCards().size(), 1);
		assertEquals(card.getCardId(), queriedCard.getCardId());
		assertEquals(card.getName(), queriedCard.getName());
		assertEquals(card.getType(), queriedCard.getType());
		assertEquals(card, queriedCard);
		verify(cardsGateway, times(2)).getAllCards();
	}

}