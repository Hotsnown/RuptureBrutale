package com.sample;

import com.expertsystem.expertsystem.ID;
import com.expertsystem.expertsystem.Thing;

public class Personne extends Thing {
	public String nom;
	public boolean responsabilité;
	public ID id;
	
	@Override
	public ID getId() {
		return id;
	}

	public void setId(ID victime) {
		this.id = victime;
	}

	public Personne(String nom) {
		this.nom = nom;
		this.responsabilité = false; //Présomption de non-responsabilité
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setResponsabilité( boolean responsable) {
		System.out.print(this.nom + responsable + "\n");
		this.responsabilité = responsable;
	}

	@Override
	public void setProperty(Relations relation, String nom) {
		if (relation == Relations.aNom) {
			this.setNom(nom);
		}
	}

	@Override
	public String getProperty(Relations relation) {
		if (relation == Relations.aNom) {
			return this.getNom();
		}	
		return null;
	}
}