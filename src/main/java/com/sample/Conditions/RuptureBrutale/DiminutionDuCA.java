package com.sample.Conditions.RuptureBrutale;

import com.sample.Personne;

public class DiminutionDuCA {
	public Personne victime;
	public Personne auteur;
	public double taux;
	public boolean habituelle;
	
	public DiminutionDuCA(Personne victime, Personne auteur, double taux) {
		this.victime = victime;
		this.auteur = auteur;
		this.taux = taux;
	}
	
	public void setHabituelle (boolean habituelle) {
		this.habituelle = habituelle;
	}
}
