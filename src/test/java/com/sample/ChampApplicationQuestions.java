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
import com.expertsystem.expertsystem.Triple;
import com.sample.Conditions.*;
import com.sample.Conditions.RelationCommercialeEtablie.FluxAffaires;

class ChampApplicationQuestions {
	
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
    	scenario.put(ID.VictimeNom, "victime1");
	    scenario.put(ID.AuteurNom, "auteur1");
		scenario.put(ID.DuréeRelation, "12");
		scenario.put(ID.FluxAffaires, Integer.toString(FluxAffaires.IMPORTANT));
		scenario.put(ID.Activité, Activité.AssociationGérantDesEtablissementsMédicoSociaux.toString());    	
    	((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	        
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Contrat contrat = new Contrat(p1, p2, 0, 0, Activité.NA, 0);
    	contrat.setId(ID.Contrat);
    	Triple tripleContrat = new Triple(contrat, Relations.aActivité, "");
    	tripleContrat.setId(ID.Activité);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(contrat);
    	kSession.insert(tripleContrat);
    	
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
    	scenario.put(ID.VictimeNom, "personne1");
	    scenario.put(ID.AuteurNom, "personne2");
		scenario.put(ID.DuréeRelation, "12");
		scenario.put(ID.FluxAffaires, Integer.toString(FluxAffaires.IMPORTANT));
		scenario.put(ID.Activité, Activité.RelationEntreLesPartiesDUnContratDeGéranceMandat.toString());    	
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	        
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Contrat contrat = new Contrat(p1, p2, 0, 0, Activité.NA, 0);
    	contrat.setId(ID.Contrat);
    	Triple tripleContrat = new Triple(contrat, Relations.aActivité, "");
    	tripleContrat.setId(ID.Activité);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(contrat);
    	kSession.insert(tripleContrat);
    	
        kSession.fireAllRules();
        
        QueryResults results = kSession.getQueryResults( "getEstApplicable" ); 
        for ( QueryResultsRow row : results ) {
            EstApplicable classA = ( EstApplicable ) row.get( "$result" ); //you can retrieve all the bounded variables here
            assert (classA.applicable);
        }
        
        kSession.dispose();

	}
	
}
