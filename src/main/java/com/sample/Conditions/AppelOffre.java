package com.sample.Conditions;

import com.sample.Personne;

public class AppelOffre {
	public Personne soumissionaire;
	public Personne commanditaire;
	
	public AppelOffre(Personne commanditaire, Personne soumissionaire) {
		this.commanditaire = commanditaire;
		this.soumissionaire = soumissionaire;
	}
}
