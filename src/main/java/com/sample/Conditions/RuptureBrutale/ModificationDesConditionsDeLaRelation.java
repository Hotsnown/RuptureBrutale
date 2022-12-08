package com.sample.Conditions.RuptureBrutale;

import com.sample.Personne;
import com.sample.Conditions.Justification;

public class ModificationDesConditionsDeLaRelation {
	public Personne victime;
	public Personne auteur;
	public boolean substantielle;
	public boolean justifiée;
	public Justification justification;
	
	
	public ModificationDesConditionsDeLaRelation(Personne victime, Personne auteur) {
		this.victime = victime;
		this.auteur = auteur;
	}
	
	public void setSubstantielle(boolean substantielle) {
		this.substantielle = substantielle;
	}
	
	public void setJustification(Justification justification) {
		this.justifiée = this.justification != Justification.NA;
		this.justification = justification;
	}
}
