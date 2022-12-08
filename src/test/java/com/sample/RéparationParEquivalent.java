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
import com.sample.Conditions.RuptureBrutale.*;
import com.sample.Conditions.RelationCommercialeEtablie.*;

class RéparationParEquivalent {
	
	@Test
	void testPrésomptionInnocence() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p = new Personne("personne1");
    	
    	kSession.insert(p);
    	
    	kSession.fireAllRules();
    	
    	assert(!p.responsabilité);
        kSession.dispose();

	}

	@Test
	void TestRéparationParEquivalent() {
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
    	r.setEtablie(true);
    	r.setCommercial(true);
    	
    	Rupture rupture = new Rupture(victime, auteur);
    	rupture.setBrutale(true);
    	
    	FaitGenerateur fg = new FaitGenerateur(auteur, victime, "rupture brutale");
    	Dommage dommage = new Dommage(victime);
    	dommage.setEstIndemnisable(true);
    	Causalite cause = new Causalite(fg, dommage);
    	   	
    	kSession.insert(r);

    	kSession.insert(rupture);
        kSession.insert(dommage);
        kSession.insert(fg);
        kSession.insert(cause);
        
        kSession.insert(auteur);
        kSession.insert(victime);
        
        assert(!auteur.responsabilité);
        
        kSession.fireAllRules();
        
        assert(auteur.responsabilité);
        assert(!victime.responsabilité);
        kSession.dispose();

	}
	
	@Test
	void TestFaitGénérateur() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne auteur = new Personne("auteur1");
    	Personne victime = new Personne("victime1");
    	
    	Relation r = new Relation(victime, auteur, 8, FluxAffaires.IMPORTANT, Activité.ActivitéCommerciale);
    	r.setCommercial(true);
    	r.setEtablie(true);
    	Rupture rupture = new Rupture(victime, auteur);
    	rupture.setBrutale(true);
    	
    	EstApplicable ea = new EstApplicable(true);
    	
    	kSession.insert(auteur);
    	kSession.insert(victime);
    	kSession.insert(r);
    	kSession.insert(rupture);
    	kSession.insert(ea);
    	
    	kSession.fireAllRules();
    	
    	QueryResults results = kSession.getQueryResults( "getFaute" ); 
    	assert(results.size()>0);

    	for ( QueryResultsRow row : results ) {
    		FaitGenerateur fg = ( FaitGenerateur ) row.get( "$result" ); //you can retrieve all the bounded variables here
    		assert(fg.débiteur.nom == auteur.nom);
    		assert(fg.créancier.nom == victime.nom);
    		assert(fg.contenu=="rupture brutale");
    	}
        kSession.dispose();

	}
	
	@Test
	void TestPrésomptionCausalité() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne auteur = new Personne("auteur1");
    	Personne victime = new Personne("victime1");
    	
    	FaitGenerateur fg = new FaitGenerateur(auteur, victime, "préavis raisonable");
    	
    	kSession.insert(fg);
    	
    	kSession.fireAllRules();
    	
    	assert(fg.débiteur == auteur);
        kSession.dispose();

	}
	
	@Test
	void TestPrésomptionImputabilité() {
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
    	r.setCommercial(true);
    	r.setEtablie(true);
    	
    	Rupture rupture = new Rupture(victime, auteur);
    	Brutalité brutalité = new Brutalité(victime, auteur);
    	
    	Dommage dommage = new Dommage(victime);
    	   	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	kSession.insert(brutalité);
        kSession.insert(dommage);
        
        kSession.insert(auteur);
        kSession.insert(victime);
                
        kSession.fireAllRules();
        
        assert(rupture.estImputableA == auteur);
        kSession.dispose();

	}
	
	@Test
	void TestImputabilitéVolontéCommune() {
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
    	Brutalité brutalité = new Brutalité(victime, auteur);
    	
    	RetardsDePaiements rdp = new RetardsDePaiements(victime);
    	
    	Dommage dommage = new Dommage(victime);
    	   	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	kSession.insert(brutalité);
        
    	kSession.insert(rdp);
    	
    	kSession.insert(dommage);
        
        kSession.insert(auteur);
        kSession.insert(victime);
                
        kSession.fireAllRules();
        
        assert(rupture.EstElleImputableA(auteur));
        assert(rupture.EstElleImputableA(victime));
        kSession.dispose();

        
	}
	

}
