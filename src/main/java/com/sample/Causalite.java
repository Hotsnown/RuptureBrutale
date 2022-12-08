package com.sample;

public class Causalite {
	public FaitGenerateur cause;
	public Dommage effet;
	
	public Causalite(FaitGenerateur cause, Dommage effet) {
		this.cause = cause;
		this.effet = effet;
	}
}

