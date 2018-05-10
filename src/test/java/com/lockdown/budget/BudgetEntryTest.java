package com.lockdown.budget;

import static com.lockdown.money.Money.dollars;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Period;

import org.junit.jupiter.api.Test;

import com.lockdown.money.Money;

public class BudgetEntryTest {

	@Test
	public void neverEntryEnsureCorrectAccumulatedTotal() {
		BudgetEntry entry = BudgetEntry.builder().with(dollars(5), FrequencyUnits.NEVER).build();
		assertEquals(Money.zero(), entry.getTotalAccumulatedAmount());
	}
	
	@Test
	public void weeklyEntryWith1OccurrenceEnsureCorrectAccumulatedTotal() {
		BudgetEntry entry = BudgetEntry.builder()
			.amount(dollars(5))
			.weekly()
			.life(Period.ofWeeks(1))
			.build();
		assertEquals(dollars(5), entry.getTotalAccumulatedAmount());
	}
	
	@Test
	public void monthlyEntryWith1OccurrenceEnsureCorrectAccumulatedTotal() {
		BudgetEntry entry = BudgetEntry.builder()
			.amount(dollars(5))
			.monthly()
			.life(Period.ofMonths(1))
			.build();
		assertEquals(dollars(5), entry.getTotalAccumulatedAmount());
	}
}
