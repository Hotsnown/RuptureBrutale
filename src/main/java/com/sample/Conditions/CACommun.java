package com.sample.Conditions;

import com.expertsystem.expertsystem.ID;
import com.expertsystem.expertsystem.Thing;
import com.sample.*;

public class CACommun extends Thing {
	public Personne partenaire1;
	public Personne partenaire2;
	public double volume;
	public double partDeCA;
	
	public CACommun (Personne partenaire1, Personne partenaire2){
		this.partenaire1 = partenaire1;
		this.partenaire2 = partenaire2;
	}
	
	public void setVolume (double volume) {
		this.volume = volume;
	}
	
	public void setPartDeCA (double partDeCA) {
		this.partDeCA = partDeCA;
	}

	@Override
	public void setProperty(Relations relation, String property) {
		if (relation == Relations.volumeDeCA) {
			this.volume = Double.parseDouble(property);
		}
		if (relation == Relations.partDeCA) {
			this.partDeCA = Double.parseDouble(property);
		}
		
	}

	@Override
	public String getProperty(Relations relation) {
		if (relation == Relations.volumeDeCA) {
			return String.valueOf(this.volume);
		}
		if (relation == Relations.partDeCA) {
			return String.valueOf(this.partDeCA);
		}
		return null;
	}

	@Override
	public ID getId() {
		return this.id;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
}
