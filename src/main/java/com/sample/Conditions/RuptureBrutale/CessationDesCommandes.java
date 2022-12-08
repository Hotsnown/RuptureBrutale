package com.sample.Conditions.RuptureBrutale;

import com.expertsystem.expertsystem.ID;
import com.expertsystem.expertsystem.Thing;
import com.sample.Personne;
import com.sample.Relations;

public class CessationDesCommandes extends Thing {
	public Personne victime;
	public Personne auteur;
	public ID id;
	public boolean estQualifié;
	
	public CessationDesCommandes(Personne victime, Personne auteur) {
		this.victime = victime;
		this.auteur = auteur;
	}

	@Override
	public void setProperty(Relations relation, String property) {
		this.estQualifié = Boolean.parseBoolean(property);		
	}

	@Override
	public String getProperty(Relations relation) {
		return String.valueOf(this.estQualifié);
	}

	@Override
	public ID getId() {
		return this.id;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
 }
