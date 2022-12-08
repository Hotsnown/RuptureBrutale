package com.expertsystem.expertsystem;

public interface Dialogue {
		
	public String ask (ID questionId, String question);
	public void say (String text);
	public String askChoice (ID questionId, String question, String choices);
	public String askAttributeValue(ID questionId,  Object attribute);
	 
}
