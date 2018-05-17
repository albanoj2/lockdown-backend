package com.lockdown.persist.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lockdown.domain.Budget;
import com.lockdown.domain.Portfolio;
import com.lockdown.persist.dto.PortfolioDto;

@Service
@DataStoreFor(Portfolio.class)
public class PortfolioDataStore extends AbstractDataStore<Portfolio, PortfolioDto> {
	
	@Autowired
	private BudgetDataStore budgetDataStore;
	
	@Autowired
	private AccountDataStore accountDataStore;
	
	@Autowired
	private CredentialsDataStore credentialsDataStore;

	@Override
	protected PortfolioDto fromDomainObject(Portfolio domainObject) {
		return new PortfolioDto(domainObject);
	}

	@Override
	protected Portfolio toDomainObject(PortfolioDto dto) {
		return new Portfolio(
			dto.getId(), 
			getBudget(dto),
			accountDataStore.findAllById(dto.getAccountsIds()), 
			credentialsDataStore.findAllById(dto.getCredentialsIds())
		);
	}

	private Budget getBudget(PortfolioDto dto) {
		// Assume this budget will be available when get() is called because the ID is found in a DTO
		return budgetDataStore.findById(dto.getBudgetId()).get();
	}

}
