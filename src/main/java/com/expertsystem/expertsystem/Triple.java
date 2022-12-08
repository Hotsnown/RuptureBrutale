package com.expertsystem.expertsystem;

import com.sample.Relations;

public class Triple {
	
	public Thing sujet;
	public Relations relation;
	public String objet;
	public ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID string) {
		this.id = string;
	}
	
	public ID getSujetId() {
		return this.sujet.getId();
	}

	public Triple(Thing sujet, Relations relation, String objet) {
		this.sujet = sujet;
		this.relation = relation;
		this.objet = objet;
	}
	
}
