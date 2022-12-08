package com.sample;

public class FaitGenerateur {
	public Personne débiteur;
	public Personne créancier;
	public String contenu;
	
	public FaitGenerateur(Personne debiteur, Personne créancier, String contenu) {
		this.débiteur = debiteur;
		this.créancier = créancier;
		this.contenu = contenu;
	}
}
