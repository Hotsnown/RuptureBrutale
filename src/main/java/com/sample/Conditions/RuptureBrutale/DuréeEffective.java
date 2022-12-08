package com.sample.Conditions.RuptureBrutale;

import com.expertsystem.expertsystem.ID;
import com.expertsystem.expertsystem.Thing;
import com.sample.Relations;

public class DuréeEffective extends Thing {
	public int durée;
	public ID id;
	
	public DuréeEffective(int durée) {
		this.durée = durée;
	}

	@Override
	public void setProperty(Relations relation, String property) {
		this.durée = Integer.parseInt(property);
		
	}

	@Override
	public String getProperty(Relations relation) {
		return String.valueOf(durée);
	}

	@Override
	public ID getId() {
		return this.id;
	}
}
