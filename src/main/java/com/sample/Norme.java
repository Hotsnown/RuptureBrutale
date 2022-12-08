package com.sample;

public class Norme {
	public Norme(Personne débiteur, Personne créancier, ThèmeNormatif thème, int modalité) {
		this.débiteur = débiteur;
		this.créancier = créancier;
		this.thème = thème;
		this.modalité = modalité;
	}
	public Personne débiteur;
	public Personne créancier;
	public ThèmeNormatif thème;
	public int modalité;
}
