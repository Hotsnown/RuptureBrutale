package com.sample;

public class Dommage {
	public Personne victime;
	public boolean estIndemnisable;
	public double montant;
	
	public Dommage(Personne victime) {
		this.victime = victime;
	}
	
	public void setEstIndemnisable(boolean estIndemnisable) {
		this.estIndemnisable = estIndemnisable;
	}
	
	public void setMontant(double montant) {
		this.montant = montant;
	}
}
