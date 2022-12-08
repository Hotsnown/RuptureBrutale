package com.sample.Conditions;

import com.sample.Personne;

public class Prestation {
	public Personne partenaire1;
	public Personne partenaire2;
	public boolean estAdmissible;
	public int année;
	public int montant;
	public boolean exceptionnel;
	
	public Prestation (Personne partenaire1, Personne partenaire2, boolean estAdmissible, int montant, int année){
		this.partenaire1 = partenaire1;
		this.partenaire2 = partenaire2;
		this.estAdmissible = estAdmissible;
		this.montant = montant;
		this.année = année;
		this.exceptionnel = false;
	}
	
	public void setExceptionnel(boolean exceptionnel) {
		this.exceptionnel = exceptionnel;
	}
	
}
