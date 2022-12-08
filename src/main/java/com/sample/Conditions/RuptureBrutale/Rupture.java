package com.sample.Conditions.RuptureBrutale;

import java.util.Collection;

import com.sample.Personne;

public class Rupture <T> {
	public Personne victime;
	public Personne auteur;
	public T estImputableA;
	
	public boolean brutale;
	public boolean partielle;
	public boolean totale;
	
	public Rupture(Personne victime, Personne auteur) {
		this.victime = victime;
		this.auteur = auteur;
		
		this.brutale = false;
		this.partielle = false;
		this.totale = false;
	}
	
	public void setEstImputableA(T personne) {
		this.estImputableA = personne;
	}
	
	public boolean EstElleImputableA(Personne présuméResponsable) {
		if (this.estImputableA instanceof Collection<?>){
			return ((Collection) this.estImputableA).contains(présuméResponsable);
		}
		return this.estImputableA == présuméResponsable;
	}
	
	public void setBrutale(boolean brutale) {
		this.brutale = brutale;
	}
	
	public void setPartielle(boolean partielle) {
		this.partielle = partielle;
	}
	
	public void setTotale(boolean totale) {
		this.totale = totale;
	}
}
