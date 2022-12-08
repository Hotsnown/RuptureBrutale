package com.sample.Conditions;

public enum CoûtsVariables {
	Transport("Transport"),
	MainOeuvre("Main d'Oeuvre"),
	Consommables("Consommables"),
	CoûtsDeFabrication("Coûts de Fabrication"),
	CoûtsDesMatièresPremières("Coûts des Matières Premières"),;

	private String string;

	CoûtsVariables(String string) {
		this.string = string;
	}
	
	public String getCoûtVariable() {
		return this.string;
	}
}
