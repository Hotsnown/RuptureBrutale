package com.sample.Conditions;

import com.sample.Personne;

public class RetraitObligationExclusivité {
	public Personne créancier;
	public Personne débiteur;
	public boolean unilatéral;
	
	public RetraitObligationExclusivité (Personne créancier, Personne débiteur) {
		this.créancier = créancier;
		this.débiteur = débiteur;
	}
	
	public void setUnilatéral(boolean unilatéral) {
		this.unilatéral = unilatéral;
	}
}
