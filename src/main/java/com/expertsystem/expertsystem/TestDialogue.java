package com.expertsystem.expertsystem;

import java.util.HashMap;

public class TestDialogue implements Dialogue {

	public TestDialogue() {}
	private HashMap<ID,String> scenario;
	
	public String ask (ID questionId, String question) {
		if (questionId == ID.VictimeNom)  {return "n";}
		return "y";
	}
	
	public void say(String text) {
	}
	
	public String askChoice(ID questionId, String question, String choices) {
		return "a";
	}
	
	public String askAttributeValue(ID questionId,  Object attribute) {
		return this.scenario.get(questionId);
	}

	public void setScenario(HashMap<ID, String> scenario2) {
		this.scenario = scenario2;
	}
	
}
