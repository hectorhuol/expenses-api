package com.financial.analisys.expenses.api.gateways.mapimpl;

import java.util.ArrayList;
import java.util.List;

import com.financial.analisys.expenses.api.domain.Companion;
import com.financial.analisys.expenses.api.gateways.CompanionsGateway;

public class CompanionsGatewayImpl implements CompanionsGateway {

	public Companion createCompanion(Companion companion) {
		if (companion.getCompanionId() == null)
			companion.setCompanionId(getNextCompanionId());
		Repository.companionsRepository.put(companion.getCompanionId(),
				companion);
		Repository.saveCompanionsRepository();
		return companion;
	}

	private String getNextCompanionId() {		
		List<Companion> values = getValuesList();
		if(values==null || values.isEmpty())
			return "1";
		Integer nextId = Integer.valueOf(values.get(values.size() - 1)
				.getCompanionId()) + 1;
		return nextId.toString();
	}

	public void updateCompanion(Companion companion) {
		Repository.companionsRepository.replace(companion.getCompanionId(),
				companion);
		Repository.saveCompanionsRepository();
	}

	public void deleteCompanion(Companion companion) {
		Repository.companionsRepository.remove(companion.getCompanionId());
		Repository.saveCompanionsRepository();
	}

	public Companion getCompanion(Companion companion) {
		return Repository.companionsRepository.get(companion.getCompanionId());

	}

	public List<Companion> getAllCompanions() {
		List<Companion> values = getValuesList();
		return values;
	}

	private List<Companion> getValuesList() {
		List<Companion> values = new ArrayList<>(
				Repository.companionsRepository.values());
		return values;
	}

}