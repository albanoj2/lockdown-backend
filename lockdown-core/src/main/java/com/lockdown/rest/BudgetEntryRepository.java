package com.lockdown.rest;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lockdown.domain.budget.BudgetItem;

public interface BudgetEntryRepository extends MongoRepository<BudgetItem, Long> {}
