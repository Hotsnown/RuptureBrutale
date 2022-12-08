package com.expertsystem.expertsystem;

import java.util.ArrayList;

public class TripleStore {
	public ArrayList<Triple> tripleStore;
	
	public TripleStore() {
		this.tripleStore = new ArrayList<Triple>();
	}
	
	public void add(Triple triple) {
		this.tripleStore.add(triple);
	}
}
