package com.lockdown.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lockdown.domain.Budget;
import com.lockdown.persist.store.BudgetDataStore;
import com.lockdown.rest.error.ResourceNotFoundException;
import com.lockdown.rest.resource.BudgetResource;
import com.lockdown.rest.resource.assembler.BudgetResourceAssembler;

@RestController
@ExposesResourceFor(Budget.class)
@RequestMapping("/budget")
public class BudgetsController {

	@Autowired
	private BudgetDataStore budgetDataStore;
	
	@Autowired
	private BudgetResourceAssembler assembler;
	
	@GetMapping
	public ResponseEntity<List<BudgetResource>> getBudgets() {
		List<Budget> budgets = budgetDataStore.findAll();
		return new ResponseEntity<>(assembler.toResources(budgets), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BudgetResource> getBudget(@PathVariable String id) {
		Budget budget = budgetDataStore.findById(id)
			.orElseThrow(ResourceNotFoundException.supplierForResource("budget", id));
		return new ResponseEntity<>(assembler.toResource(budget), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BudgetResource> createBudget(@RequestBody Budget budget) {
		Budget createdBudget = budgetDataStore.saveAndCascade(budget);
		return new ResponseEntity<>(assembler.toResource(createdBudget), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BudgetResource> updateBudget(@PathVariable String id, @RequestBody Budget budget) {
		Budget updatedBudget = budgetDataStore.saveAndCascade(budget);
		return new ResponseEntity<>(assembler.toResource(updatedBudget), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBudget(@PathVariable String id) {
		budgetDataStore.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}