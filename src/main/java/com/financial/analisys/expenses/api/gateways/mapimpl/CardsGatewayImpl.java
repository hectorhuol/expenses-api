package com.financial.analisys.expenses.api.gateways.mapimpl;

import java.util.ArrayList;
import java.util.List;

import com.financial.analisys.expenses.api.domain.Card;
import com.financial.analisys.expenses.api.gateways.CardsGateway;

public class CardsGatewayImpl implements CardsGateway {

	public Card createCard(Card card) {
		if (card.getCardId() == null)
			card.setCardId(getNextCardId());
		Repository.cardsRepository.put(card.getCardId(), card);
		Repository.saveCardsRepository();
		return card;
	}

	private String getNextCardId() {
		List<Card> values = getValuesList();
		if (values == null || values.isEmpty())
			return "1";
		Integer nextId = Integer.valueOf(values.get(values.size() - 1)
				.getCardId()) + 1;
		return nextId.toString();
	}

	public void updateCard(Card card) {
		Repository.cardsRepository.replace(card.getCardId(), card);
		Repository.saveCardsRepository();
	}

	public void deleteCard(Card card) {
		Repository.cardsRepository.remove(card.getCardId());
		Repository.saveCardsRepository();
	}

	public Card getCard(Card card) {
		return Repository.cardsRepository.get(card.getCardId());

	}

	public List<Card> getAllCards() {
		List<Card> values = getValuesList();
		return values;
	}

	private List<Card> getValuesList() {
		return new ArrayList<>(Repository.cardsRepository.values());
	}

}