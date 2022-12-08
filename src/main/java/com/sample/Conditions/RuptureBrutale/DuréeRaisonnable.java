package com.sample.Conditions.RuptureBrutale;

import com.sample.Personne;

public class DuréeRaisonnable {
	public int durée;
	public Personne victime;
	public Personne auteur;
	
	public DuréeRaisonnable(Personne victime, Personne auteur) {
		this.victime = victime;
		this.auteur = auteur;
	}
	
	public void setDuréePréavis(int durée) {
		this.durée = durée;
	}
}
