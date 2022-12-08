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

class ChampApplication {
	
	@Test
	void PresomptionApplicable() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
        kSession.fireAllRules();
        
        QueryResults results = kSession.getQueryResults( "getEstApplicable" ); 
        for ( QueryResultsRow row : results ) {
            EstApplicable classA = ( EstApplicable ) row.get( "$result" ); //you can retrieve all the bounded variables here
            assert (classA.applicable);
        }
        
        kSession.dispose();


	}
	
	@Test
	void AssociationGérantDesEtablissementsMédicoSociauxApplicable() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	        
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.AssociationGérantDesEtablissementsMédicoSociaux);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(r);
    	
        kSession.fireAllRules();
        
        QueryResults results = kSession.getQueryResults( "getEstApplicable" ); 
        for ( QueryResultsRow row : results ) {
            EstApplicable classA = ( EstApplicable ) row.get( "$result" ); //you can retrieve all the bounded variables here
            assert (!classA.applicable);
        }
        kSession.dispose();

	}
	
	@Test
	void RelationEntreLesPartiesDUnContratDeGéranceMandatApplicable() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	        
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.RelationEntreLesPartiesDUnContratDeGéranceMandat);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(r);
    	
        kSession.fireAllRules();
        
        QueryResults results = kSession.getQueryResults( "getEstApplicable" ); 
        for ( QueryResultsRow row : results ) {
            EstApplicable classA = ( EstApplicable ) row.get( "$result" ); //you can retrieve all the bounded variables here
            assert (classA.applicable);
        }
        
        kSession.dispose();

	}
	
}
