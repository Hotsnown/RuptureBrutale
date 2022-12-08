package com.expertsystem.expertsystem;

import com.sample.Relations;

public abstract class Thing {

	public ID id;
	
	public abstract void setProperty(Relations relation, String property);
	public abstract String getProperty(Relations relation);
	public abstract ID getId();

}
