package com.sample;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.expertsystem.expertsystem.Dialogue;
import com.expertsystem.expertsystem.ID;
import com.expertsystem.expertsystem.TestDialogue;
import com.sample.Conditions.*;

class TestExistenceRelation {
	
	@Test
	void RelationDoitExisterQuand() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Contrat contrat = new Contrat(p1, p2, 0, 0, Activité.ActivitéCommerciale, 1);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(contrat);
    	
    	kSession.fireAllRules();
    	
    	QueryResults results = kSession.getQueryResults( "getRelations" ); 
    	
    	for ( QueryResultsRow row : results ) {
    	    Relation relation = ( Relation ) row.get( "$relation" ); //you can retrieve all the bounded variables here
    	    assert(relation.victime.nom == p1.nom);
    	}
    	
        kSession.dispose();

    	
	}
	
	@Test
	void TestSuccesionDeContrats() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	   	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Contrat contrat = new Contrat(p1, p2, 2, 0, Activité.ActivitéCommerciale, 1);
    	Contrat contrat1 = new Contrat(p1, p2, 3, 0, Activité.AgenceCommerciale, 1);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(contrat);
    	kSession.insert(contrat1);
    	
    	kSession.fireAllRules();
    	
    	QueryResults results = kSession.getQueryResults( "getRelations" ); 

    	assert (results.size()==1);
    	for ( QueryResultsRow row : results ) {
    	    Relation relation = ( Relation ) row.get( "$relation" ); //you can retrieve all the bounded variables here
  
    	    assert(relation.durée == 5);
    	}
    	
        kSession.dispose();

    	
	}
	
	@Test
	void GroupdDuration() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	   	
    	Task task = new Task("1", 1);
    	Task task2 = new Task("1", 2);
    	Task task3 = new Task("3", 4);
    	
    	kSession.insert(task);
    	kSession.insert(task2);
    	kSession.insert(task3);
    	
    	kSession.fireAllRules();
    	    	
        kSession.dispose();

	}
	

}
