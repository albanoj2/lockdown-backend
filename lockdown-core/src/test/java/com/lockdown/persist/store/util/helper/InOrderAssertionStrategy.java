package com.lockdown.persist.store.util.helper;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lockdown.persist.store.util.data.cascade.domain.MockDomainObject;

public class InOrderAssertionStrategy implements OrderAssertionStrategy {
	
	private final List<UnorderedGroup> matchers;
	
	private InOrderAssertionStrategy() {
		this.matchers = new ArrayList<>();
	}
	
	public static InOrderAssertionStrategy first(MockDomainObject... domainObjects) {
		return new InOrderAssertionStrategy()
			.thenExpect(domainObjects);
	}
	
	public InOrderAssertionStrategy thenExpect(MockDomainObject... domainObjects) {
		this.matchers.add(new UnorderedGroup(domainObjects));
		return this;
	}

	@Override
	public void assertOrder(List<MockDomainObject> saved) {
		int lastVerifiedIndex = 0;
		
		for (UnorderedGroup group: matchers) {
			List<MockDomainObject> subList = saved.subList(lastVerifiedIndex, lastVerifiedIndex + group.size());
			assertTrue(group.matches(subList));
			lastVerifiedIndex += group.size();
		}
	}
	
	private static class UnorderedGroup {
		
		private final List<MockDomainObject> expected;
		
		public UnorderedGroup(MockDomainObject... expected) {
			this.expected = Arrays.asList(expected);
		}
		
		public int size() {
			return expected.size();
		}
		
		public boolean matches(List<MockDomainObject> objects) {
			return expected.size() == objects.size() && objects.containsAll(expected);
		}
	}
}
