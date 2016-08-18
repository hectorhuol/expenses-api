package com.financial.analisys.expenses.api.gateways;

import java.util.List;

import com.financial.analisys.expenses.api.domain.Companion;

public interface CompanionsGateway {

	public Companion createCompanion(Companion companion);

	public void updateCompanion(Companion companion);

	public void deleteCompanion(Companion companion);

	public Companion getCompanion(Companion companion);

	public List<Companion> getAllCompanions();

}
