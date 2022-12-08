package com.sample;

import java.util.HashMap;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.expertsystem.expertsystem.Dialogue;
import com.expertsystem.expertsystem.ID;
import com.expertsystem.expertsystem.TestDialogue;
import com.sample.Conditions.Activité;
import com.sample.Conditions.ForceMajeure;
import com.sample.Conditions.Manquement;
import com.sample.Conditions.Relation;
import com.sample.Conditions.RuptureBrutale.*;

class TestExceptions {
	
	@Test
	void ForceMajeure() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");
    	
    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

    	Rupture rupture = new Rupture(p1, p2);
    	rupture.setBrutale(true);
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	ForceMajeure fm = new ForceMajeure();
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(rupture);
    	kSession.insert(relation);
    	
    	kSession.insert(fm);
    	
    	kSession.fireAllRules();
    	
    	assert(!rupture.brutale);
    	
        kSession.dispose();
	}
	
	@Test
	void FauteDuPartenaire() {
		
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");
    	
    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

    	Rupture rupture = new Rupture(p1, p2);
    	rupture.setBrutale(true);
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	
    	Manquement f = new Manquement(p1, true);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(rupture);
    	kSession.insert(relation);
    	
    	kSession.insert(f);
    	
    	kSession.fireAllRules();
    	
    	assert(!rupture.brutale);
    	
        kSession.dispose();


	}
	
	@Test
	void DéfautsDePaiements() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");
    	
    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

    	Rupture rupture = new Rupture(p1, p2);
    	rupture.setBrutale(true);
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	
    	DéfautsDePaiements f = new DéfautsDePaiements(p1);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(rupture);
    	kSession.insert(relation);
    	
    	kSession.insert(f);
    	
    	kSession.fireAllRules();
    	
    	assert(!rupture.brutale);
    	
        kSession.dispose();


	}
}
