package com.sample.Conditions;

import com.sample.Personne;

public class Relation {
	public Personne victime;
	public Personne auteur;
	public int durée;
	public int fluxaffaires;
	public Activité activité;
	public boolean commercial;
	public boolean stable;
	public boolean suivie;
	public boolean établie;
	public boolean régulière;
	
	public Relation(Personne victime, Personne auteur, int durée, int fluxaffaires, Activité activité) {
		this.victime = victime;
		this.auteur = auteur;
		this.durée = durée;
		this.fluxaffaires = fluxaffaires;
		this.activité = activité;
		
		this.commercial = false;
		this.stable = false;
		this.suivie = false;
		this.établie = false;
	}
	
	public void setCommercial(boolean commercial) {
		this.commercial = commercial;
	}
	
	public void setStable(boolean stable) {
		this.stable = stable;
	}
	
	public void setSuivie(boolean suivie) {
		this.suivie = suivie;
	}
	
	public void setEtablie(boolean établie) {
		this.établie = établie;
	}
	
	public void setRégulière(boolean régulière) {
		this.régulière = régulière;
	}
	
}