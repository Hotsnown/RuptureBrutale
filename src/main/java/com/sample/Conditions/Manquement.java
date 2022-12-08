package com.sample.Conditions;

import com.sample.Personne;

public class Manquement {
	public boolean grave;
	public Personne fautif;
	
	public Manquement(Personne fautif, boolean grave) {
		this.fautif = fautif;
		this.grave = grave;
	}
}
