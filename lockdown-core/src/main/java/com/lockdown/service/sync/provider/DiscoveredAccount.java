package com.lockdown.service.sync.provider;

import java.util.ArrayList;

import com.lockdown.domain.Account;
import com.lockdown.domain.Account.Subtype;
import com.lockdown.domain.Account.Type;

public class DiscoveredAccount {

	private final String key;
	private final String name;
	private final Type type;
	private final Subtype subtype;
	
	public DiscoveredAccount(String key, String name, Type type, Subtype subtype) {
		this.key = key;
		this.name = name;
		this.type = type;
		this.subtype = subtype;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public Subtype getSubtype() {
		return subtype;
	}
	
	public Account toAccount() {
		return new Account(null, key, name, type, subtype, new ArrayList<>());	
	}
}
