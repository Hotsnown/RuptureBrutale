package com.sample.Conditions;

import com.expertsystem.expertsystem.ID;
import com.expertsystem.expertsystem.Thing;
import com.sample.Personne;
import com.sample.Relations;

public class Marge extends Thing {
	public Personne partenaire1;
	public Personne partenaire2;
	public boolean variable;
	public double taux;
	
	public Marge (Personne partenaire1, Personne partenaire2){
		this.partenaire1 = partenaire1;
		this.partenaire2 = partenaire2;
	}
	
	public void setTaux(double taux) {
		this.taux = taux;
	}
	
	public void setVariable(boolean variable) {
		this.variable = variable;
	}

	@Override
	public void setProperty(Relations relation, String property) {
		this.taux = Double.parseDouble(property);
		
	}

	@Override
	public String getProperty(Relations relation) {
		return String.valueOf(this.taux);
	}

	@Override
	public ID getId() {
		return this.id;
	}

	public void setId(ID id) {
		this.id = id;
	}
}
