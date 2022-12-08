package com.sample.Conditions;

public class Investissement {

	public boolean spécifique;
	public boolean amorti;
	public boolean efficient;
	public boolean important;
	
	public Investissement () {
		
	}
	
	public boolean isSpécifique() {
		return spécifique;
	}

	public void setSpécifique(boolean spécifique) {
		this.spécifique = spécifique;
	}

	public boolean isAmorti() {
		return amorti;
	}

	public void setAmorti(boolean amorti) {
		this.amorti = amorti;
	}

	public boolean isEfficient() {
		return efficient;
	}

	public void setEfficient(boolean efficient) {
		this.efficient = efficient;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}
	
}
