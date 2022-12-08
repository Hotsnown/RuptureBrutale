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
import com.sample.Conditions.Activité;
import com.sample.Conditions.AppelOffre;
import com.sample.Conditions.CACommun;
import com.sample.Conditions.Relation;
import com.sample.Conditions.RelationCommercialeEtablie.FluxAffaires;
import com.sample.Conditions.RuptureBrutale.Brutalité;
import com.sample.Conditions.RuptureBrutale.DiminutionDuCA;
import com.sample.Conditions.RuptureBrutale.DuréeEffective;
import com.sample.Conditions.RuptureBrutale.DuréeRaisonnable;
import com.sample.Conditions.RuptureBrutale.ModificationDesConditionsDeLaRelation;
import com.sample.Conditions.RuptureBrutale.Rupture;
import static org.junit.Assert.assertEquals;

class TestBrutalité {
	
	@Test
	void TestBrutalitéPourPréavisInsuffisant() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne victime = new Personne("victime1");
    	Personne auteur = new Personne("auteur1");
    	
    	Relation r = new Relation(victime, auteur, 8, FluxAffaires.IMPORTANT, Activité.ActivitéCommerciale);
    	Rupture rupture = new Rupture(victime, auteur);
    	DuréeRaisonnable duréeRaisonnable = new DuréeRaisonnable(victime, auteur);
    	duréeRaisonnable.setDuréePréavis(9);
    	DuréeEffective duréeEffective = new DuréeEffective(8);
    	
    	kSession.insert(r);
    	
    	kSession.insert(rupture);
    	
    	kSession.insert(duréeRaisonnable);
    	kSession.insert(duréeEffective);  
    	        
        kSession.insert(auteur);
        kSession.insert(victime);
                
        kSession.fireAllRules();
        
        assert(rupture.brutale);
        
        kSession.dispose();

	}
	
	@Test
	void TestNonBrutalitéPourPréavisSuffisant() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne victime = new Personne("victime1");
    	Personne auteur = new Personne("auteur1");
    	
    	Relation r = new Relation(victime, auteur, 8, FluxAffaires.IMPORTANT, Activité.ActivitéCommerciale);
    	Rupture rupture = new Rupture(victime, auteur);
    	DuréeRaisonnable duréeRaisonnable = new DuréeRaisonnable(victime, auteur);
    	duréeRaisonnable.setDuréePréavis(7);
    	DuréeEffective duréeEffective = new DuréeEffective(8);
    	
    	kSession.insert(r);
    	
    	kSession.insert(rupture);
    	
    	kSession.insert(duréeRaisonnable);
    	kSession.insert(duréeEffective);  
    	        
        kSession.insert(auteur);
        kSession.insert(victime);
                
        kSession.fireAllRules();
        
        assert(!rupture.brutale);
        
        kSession.dispose();

	}
	
	@Test
	void TestBrutalitéPourPréavisInsuffisantDéterminéDuréeRelation() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne victime = new Personne("victime1");
    	Personne auteur = new Personne("auteur1");
    	
    	Relation r = new Relation(victime, auteur, 24, FluxAffaires.IMPORTANT, Activité.ActivitéCommerciale);    	
    	Rupture rupture = new Rupture(victime, auteur);
    	DuréeRaisonnable dr = new DuréeRaisonnable(victime, auteur);
    	DuréeEffective duréeEffective = new DuréeEffective(1);
        
    	kSession.insert(r);
    	kSession.insert(rupture);
    	kSession.insert(dr);
    	
    	kSession.insert(duréeEffective);
    	    	
        kSession.insert(auteur);
        kSession.insert(victime);
                
        kSession.fireAllRules();
        
        assertEquals(r.durée, 24);
        assertEquals(dr.durée, 2);       
        assert(rupture.brutale);
        
        kSession.dispose();

	}
	
	@Test
	void DuréeRaisonnableCA() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne victime = new Personne("victime1");
    	Personne auteur = new Personne("auteur1");
    	
    	Relation r = new Relation(victime, auteur, 12, FluxAffaires.IMPORTANT, Activité.ActivitéCommerciale);
      	Rupture rupture = new Rupture(victime, auteur);
    	
    	DuréeEffective duréeEffective = new DuréeEffective(1);
    	CACommun acc = new CACommun(victime, auteur);
    	acc.setPartDeCA(0.6);
    	    	    	   	
    	kSession.insert(r);
    	
    	kSession.insert(rupture);
    	
    	kSession.insert(duréeEffective);  
    	kSession.insert(acc);
    	
        kSession.insert(auteur);
        kSession.insert(victime);
                
        kSession.fireAllRules();
                        
        QueryResults results = kSession.getQueryResults( "getDuréeRaisonnable" );
        for ( QueryResultsRow row : results ) {
        	DuréeRaisonnable duréeRaisonnable = ( DuréeRaisonnable ) row.get( "$duréeRaisonnable" ); //you can retrieve all the bounded variables here
            assert(duréeRaisonnable.durée == 2);
        }
      
        assert(rupture.brutale);
        
        kSession.dispose();

	}
}
