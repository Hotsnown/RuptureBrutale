package com.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.expertsystem.expertsystem.Dialogue;
import com.expertsystem.expertsystem.ID;
import com.expertsystem.expertsystem.TestDialogue;
import com.sample.Conditions.Activité;
import com.sample.Conditions.AltéritéDesParties;
import com.sample.Conditions.AppelOffre;
import com.sample.Conditions.ContinuationConditionnelle;
import com.sample.Conditions.Fréquence;
import com.sample.Conditions.ImmixtionSociétéMère;
import com.sample.Conditions.ImmixtionSociétéMèreContestée;
import com.sample.Conditions.Interruption;
import com.sample.Conditions.Relation;
import com.sample.Conditions.Réalité;
import com.sample.Conditions.RelationCommercialeEtablie.FluxAffaires;
import com.sample.Conditions.RuptureBrutale.*;

class TestRelationEtablie {
	
	@Test
	   public void testCheckForGoals() {
		 KieServices ks = KieServices.Factory.get();
	     KieContainer kContainer = ks.getKieClasspathContainer();
	 	 KieSession kSession = kContainer.newKieSession("ksession-rules");

	     Dialogue dialogue = new TestDialogue();
	     HashMap<ID, String> scenario = new HashMap<ID, String>();
		 ((TestDialogue) dialogue).setScenario(scenario);
	    	kSession.setGlobal("dialogue", dialogue);
	    	
	 	 List list = new ArrayList(  );
	
	      kSession.fireAllRules();
	
	      for ( Object o : kSession.getObjects() ) {
	          System.out.println( ">>> " + o );
	      }
	      
        kSession.dispose();


	}
	
	@Test
	void TestRelationEtablieBase() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, null);
    	r.setStable(true);
    	r.setSuivie(true);
    	r.setRégulière(true);
    	
    	kSession.insert(r);

    	kSession.fireAllRules();

    	assert(r.établie);
    	
        kSession.dispose();
    	
	}
	
	@Test
	void TestRelationEtablieEnFonctionDuCA() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, FluxAffaires.IMPORTANT, null);
    	
    	kSession.insert(r);

    	kSession.fireAllRules();

    	assert(r.établie);
        kSession.dispose();

	}
	
	//RELATION SUIVIE	
	@Test
	void TestRelationSuivie() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, null);
    	
    	kSession.insert(r);

    	kSession.fireAllRules();

    	assert(r.suivie);
        kSession.dispose();

	}
	
	@Test
	void TestFluxAffairesFaibleETDuréeInférieureA7MoisNEstPasUneRelationSuivie() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne auteur = new Personne("auteur1");
    	Personne victime = new Personne("victime1");
    	
    	Relation r = new Relation(victime, auteur, 6, FluxAffaires.FAIBLE, Activité.ActivitéCommerciale);
    	r.setStable(true);
    	
    	//Rupture
    	Rupture rupture = new Rupture(victime, auteur);
    	Brutalité brutalite = new Brutalité(victime, auteur);
    	Dommage dommage = new Dommage(victime);
    	
    	kSession.insert(r);
    	
    	kSession.insert(rupture);
    	kSession.insert(brutalite);
    	    	
        kSession.insert(dommage);
        
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
        assert(!auteur.responsabilité);
        assert(!victime.responsabilité);
        kSession.dispose();

	}
	
	
	@Test
	void TestInterruptionDeRelation() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, null);
    	Interruption interruption = new Interruption();
    	
    	kSession.insert(r);
    	kSession.insert(interruption);
    	
    	kSession.fireAllRules();

    	assert(!r.suivie);
        kSession.dispose();

	}
	
	@Test
	void TestAltéritéDesParties() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, null);
    	AltéritéDesParties adp = new AltéritéDesParties();
    	
    	kSession.insert(r);
    	kSession.insert(adp);
    	
    	kSession.fireAllRules();

    	assert(!r.suivie);
        kSession.dispose();
    	
	}
	
	@Test
	void TestImmixtionDeLaSociétéMère() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, null);
    	AltéritéDesParties adp = new AltéritéDesParties();
    	ImmixtionSociétéMère ism = new ImmixtionSociétéMère();
    	
    	kSession.insert(r);
    	kSession.insert(adp);
    	kSession.insert(ism);
    	
    	kSession.fireAllRules();

    	assert(r.suivie);
        kSession.dispose();
    	
	}
	
	@Test
	void TestImmixtionDeLaSociétéMèreContestée() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, null);
    	AltéritéDesParties adp = new AltéritéDesParties();
    	ImmixtionSociétéMère ism = new ImmixtionSociétéMère();
    	ImmixtionSociétéMèreContestée ismc = new ImmixtionSociétéMèreContestée();
    	
    	kSession.insert(r);
    	kSession.insert(adp);
    	kSession.insert(ism);
    	kSession.insert(ismc);
    	
    	kSession.fireAllRules();

    	assert(!r.suivie);
        kSession.dispose();
    	
	}

	//RELATION REGULIERE
	@Test
	void TestRelationRégulière() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, null);
    	
    	kSession.insert(r);

    	kSession.fireAllRules();

    	assert(r.régulière);
        kSession.dispose();
    	
	}
	
	@Test
	void TestRelationPonctuelle() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, null);
    	Transactions t = new Transactions(Fréquence.Ponctuelle, null);
    			
    	kSession.insert(r);
    	kSession.insert(t);

    	kSession.fireAllRules();

    	assert(!r.régulière);
        kSession.dispose();
    	
	}
	
	@Test
	void TestRelationNonSubstantielle() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, null);
    	Transactions t = new Transactions(null, Réalité.Formel);
    			
    	kSession.insert(r);
    	kSession.insert(t);

    	kSession.fireAllRules();

    	assert(!r.régulière);
        kSession.dispose();
    	
	}
	
	//RELATION STABLE	
	@Test
	void TestRelationStable() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, null);
    	
    	kSession.insert(r);

    	kSession.fireAllRules();

    	assert(r.stable);
        kSession.dispose();
    	
	}
	
	@Test
	void TestAppelOffre() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, null);
    	AppelOffre ao = new AppelOffre(p2, p1);
    	
    	kSession.insert(r);
    	kSession.insert(ao);

    	kSession.fireAllRules();

    	assert(!r.stable);
        kSession.dispose();
    	
	}
	
	@Test
	void TestContinuationConditionelle() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, null);
    	ContinuationConditionnelle cc = new ContinuationConditionnelle();
    	
    	kSession.insert(r);
    	kSession.insert(cc);

    	kSession.fireAllRules();

    	assert(!r.stable);
        kSession.dispose();
    	
	}
	
	 
}
