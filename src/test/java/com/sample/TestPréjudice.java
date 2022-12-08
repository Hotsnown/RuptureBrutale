package com.sample;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.sample.Conditions.*;
import com.expertsystem.expertsystem.Dialogue;
import com.expertsystem.expertsystem.ID;
import com.expertsystem.expertsystem.TestDialogue;
import com.sample.*;
import com.sample.Conditions.RuptureBrutale.*;

class TestPréjudice {
	
	@Test
	void TestExistenceDommage() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	CACommun acc = new CACommun(p1, p2);
    	acc.setVolume(1000);
    	Marge marge = new Marge(p1, p2);
    	marge.setTaux(0.20);
    	    	
    	DuréeEffective de = new DuréeEffective(10);
    	DuréeRaisonnable dr = new DuréeRaisonnable(p1, p2);
    	dr.setDuréePréavis(12);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(relation);
    	kSession.insert(acc);
    	kSession.insert(marge);
    	
    	kSession.insert(de);
    	kSession.insert(dr);
    	
		kSession.fireAllRules();
	     
	    QueryResults results2 = kSession.getQueryResults( "getDommage" ); 
	    for ( QueryResultsRow row : results2 ) {
	        Dommage dommage1 = ( Dommage ) row.get( "$result" ); //you can retrieve all the bounded variables here
	        assert (dommage1.montant == (1000 * 0.20) * 2);
	    }
	    
        kSession.dispose();

        
	}
	
	@Test
	void Montant() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	CACommun acc = new CACommun(p1, p2);
    	acc.setVolume(1000);
    	Marge marge = new Marge(p1, p2);
    	marge.setTaux(0.20);
    	
    	Dommage dommage = new Dommage(p1);
    	
    	DuréeEffective de = new DuréeEffective(10);
    	DuréeRaisonnable dr = new DuréeRaisonnable(p1, p2);
    	dr.setDuréePréavis(12);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(relation);
    	kSession.insert(acc);
    	kSession.insert(marge);
    	kSession.insert(dommage);
    	
    	kSession.insert(de);
    	kSession.insert(dr);
    	
    	kSession.fireAllRules();
    	    	
    	assert(dommage.montant == (1000 * 0.20) * 2);
    	
        kSession.dispose();

	}
	
	@Test
	void MontantPrestationDeService() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	   	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.PrestationDeService);
    	CACommun acc = new CACommun(p1, p2);
    	acc.setVolume(1000);
    	
    	Marge marge = new Marge(p1, p2);
    	
    	Dommage dommage = new Dommage(p1);
    	
    	DuréeEffective de = new DuréeEffective(10);
    	DuréeRaisonnable dr = new DuréeRaisonnable(p1, p2);
    	dr.setDuréePréavis(12);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(relation);
    	
    	kSession.insert(acc);
    	kSession.insert(marge);
    	kSession.insert(dommage);
    	
    	kSession.insert(de);
    	kSession.insert(dr);
    	
    	kSession.fireAllRules();
    	
    	assert(marge.taux == 1);
    	assert(relation.activité == Activité.PrestationDeService);
    	assert(acc.volume == 1000);
    	assert(dommage.montant == 1000.0 * 2);
    	
        kSession.dispose();

	}
	
	@Test
	void CoûtsBruts() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	CACommun acc = new CACommun(p1, p2);
    	acc.setVolume(1000);
    	Coûts cv = new Coûts(p1, p2, 800);
    	Marge marge = new Marge(p1, p2);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(relation);
    	
    	kSession.insert(acc);
    	kSession.insert(cv);
    	kSession.insert(marge);
    	
    	kSession.fireAllRules();
    	    	
    	assert(marge.taux == 0.20);
    	assert(!marge.variable);
    	
        kSession.dispose();

    	
	}
	
	@Test
	void MargeVariable() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	
    	CACommun acc = new CACommun(p1, p2);
    	acc.setVolume(1000);
    	
    	ChargesVariables cv = new ChargesVariables(p1, p2, 800);
    	Marge marge = new Marge(p1, p2);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(relation);
    	
    	kSession.insert(acc);
    	kSession.insert(cv);
    	kSession.insert(marge);
    	
    	kSession.fireAllRules();
    	    	
    	assert(marge.taux == 0.20);
    	assert(marge.variable);
    	
        kSession.dispose();

	}
	
	@Test
	void MargeVariableFraisDeTransport() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	   	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	
    	CACommun acc = new CACommun(p1, p2);
    	acc.setVolume(1000);
    	
    	Frais ft = new Frais(800, CoûtsVariables.Transport.getCoûtVariable());
    	Marge marge = new Marge(p1, p2);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(relation);
    	
    	kSession.insert(ft);
    	
    	kSession.insert(acc);
    	kSession.insert(marge);
    	
    	kSession.fireAllRules();
    	
    	assert(marge.variable);
    	assert(marge.taux == 0.20);
    	
        kSession.dispose();

	}
	
	@Test
	void NonMargeVariable() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	
    	CACommun acc = new CACommun(p1, p2);
    	acc.setVolume(1000);
    	
    	Frais ft = new Frais(800, "Machine à Café");
    	Marge marge = new Marge(p1, p2);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(relation);
    	
    	kSession.insert(ft);
    	
    	kSession.insert(acc);
    	kSession.insert(marge);
    	
    	kSession.fireAllRules();
    	
    	assert(marge.taux == 0.00);
    	assert(!marge.variable);
    	
        kSession.dispose();


	}
	
	@Test
	void MargeVariableFraisDeMainOeuvre() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	
    	CACommun acc = new CACommun(p1, p2);
    	acc.setVolume(1000);
    	
    	Frais ft = new Frais(800, CoûtsVariables.MainOeuvre.getCoûtVariable());
    	Marge marge = new Marge(p1, p2);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(relation);
    	
    	kSession.insert(ft);
    	
    	kSession.insert(acc);
    	kSession.insert(marge);
    	
    	kSession.fireAllRules();
    	
    	assert(marge.variable);
    	assert(marge.taux == 0.20);

    	kSession.dispose();

	}
	
	@Test
	void MargeVariableFraisConsommable() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	   	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	
    	CACommun acc = new CACommun(p1, p2);
    	acc.setVolume(1000);
    	
    	Frais ft = new Frais(800, CoûtsVariables.Consommables.getCoûtVariable());
    	Marge marge = new Marge(p1, p2);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(relation);
    	
    	kSession.insert(ft);
    	
    	kSession.insert(acc);
    	kSession.insert(marge);
    	
    	kSession.fireAllRules();
    	
    	assert(marge.taux == 0.20);
    	assert(marge.variable);
    	
        kSession.dispose();


	}
	
	@Test
	void Assiette() {

		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	   	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	Prestation prestation1 = new Prestation(p1, p2, true, 1000, 2017);
    	Prestation prestation2 = new Prestation(p1, p2, true, 1000, 2018);
    	Prestation prestation3 = new Prestation(p1, p2, true, 1000, 2019);
    	
    	CACommun acc = new CACommun(p1, p2);
    	
    	Année année1 = new Année(2017);
    	Année année2 = new Année(2018);
    	Année année3 = new Année(2019);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(relation);
    	
    	kSession.insert(prestation1);
    	kSession.insert(prestation2);
    	kSession.insert(prestation3);
    	
    	kSession.insert(année1);
    	kSession.insert(année2);
    	kSession.insert(année3);
    	
    	kSession.insert(acc);
    	
    	kSession.fireAllRules();
    	
    	assert (acc.volume == 1000);
    	
        kSession.dispose();

    	    	    	
	}
	
	@Test
	void Assiette2() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	Prestation prestation1 = new Prestation(p1, p2, true, 1000, 2016);
    	Prestation prestation2 = new Prestation(p1, p2, true, 1000, 2017);
    	Prestation prestation3 = new Prestation(p1, p2, true, 1000, 2019);
    	
    	CACommun acc = new CACommun(p1, p2);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(relation);
    	
    	Année année1 = new Année(2016);
    	Année année2 = new Année(2017);
    	Année année3 = new Année(2018);
    	Année année4 = new Année(2019);
    	
    	kSession.insert(prestation1);
    	kSession.insert(prestation2);
    	kSession.insert(prestation3);
    	
    	kSession.insert(année1);
    	kSession.insert(année2);
    	kSession.insert(année3);
    	kSession.insert(année4);
    	
    	kSession.insert(acc);
    	
    	kSession.fireAllRules();
    	
    	assert (acc.volume != 1000);
    	assert (acc.volume == 666);
    	
        kSession.dispose();


	}
	
	@Test
	void Assiette3() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	Prestation prestation1 = new Prestation(p1, p2, true, 1000, 2017);
    	Prestation prestation2 = new Prestation(p1, p2, true, 1000, 2018);
    	Prestation prestation3 = new Prestation(p1, p2, true, 1000, 2018);
    	Prestation prestation4 = new Prestation(p1, p2, true, 1000, 2018);
    	Prestation prestation5 = new Prestation(p1, p2, true, 1000, 2019);
    	Prestation prestation6 = new Prestation(p1, p2, true, 1000, 2019);
    	
    	CACommun acc = new CACommun(p1, p2);
    	
    	
    	Année année1 = new Année(2017);
    	Année année2 = new Année(2018);
    	Année année3 = new Année(2019);
    	
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(relation);
    	
    	kSession.insert(prestation1);
    	kSession.insert(prestation2);
    	kSession.insert(prestation3);
    	kSession.insert(prestation4);
    	kSession.insert(prestation5);
    	kSession.insert(prestation6);
    	
    	kSession.insert(année1);
    	kSession.insert(année2);
    	kSession.insert(année3);
    	
    	kSession.insert(acc);
    	
    	kSession.fireAllRules();
    	
    	assert (acc.volume == 2000);
    	
        kSession.dispose();

    	    	    	
	}
	
	@Test
	void TestAssietteExceptionnelle2() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	Prestation prestation1 = new Prestation(p1, p2, true, 1000, 2017);
    	Prestation prestation2 = new Prestation(p1, p2, true, 100000, 2018);
    	prestation2.setExceptionnel(true);
    	Prestation prestation3 = new Prestation(p1, p2, true, 1000, 2019);
    	
    	CACommun acc = new CACommun(p1, p2);
    	
    	Année année1 = new Année(2017);
    	Année année2 = new Année(2018);
    	Année année3 = new Année(2019);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(relation);
    	
    	kSession.insert(prestation1);
    	kSession.insert(prestation2);
    	kSession.insert(prestation3);
    	
    	kSession.insert(année1);
    	kSession.insert(année2);
    	kSession.insert(année3);
    	
    	kSession.insert(acc);
    	
    	kSession.fireAllRules();
    	
    	assert (acc.volume == 1000);
    	
        kSession.dispose();

    	    	    	
	}
	
	@Test
	void TestAssietteExceptionnelle3() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation relation = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	Prestation prestation1 = new Prestation(p1, p2, true, 1000, 2017);
    	Prestation prestation2 = new Prestation(p1, p2, true, 1000, 2018);
    	Prestation prestation3 = new Prestation(p1, p2, true, 100000, 2019);
    	prestation3.setExceptionnel(true);
    	
    	CACommun acc = new CACommun(p1, p2);
    	
    	Année année1 = new Année(2017);
    	Année année2 = new Année(2018);
    	Année année3 = new Année(2019);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	kSession.insert(relation);
    	
    	kSession.insert(prestation1);
    	kSession.insert(prestation2);
    	kSession.insert(prestation3);
    	
    	kSession.insert(année1);
    	kSession.insert(année2);
    	kSession.insert(année3);
    	
    	kSession.insert(acc);
    	
    	kSession.fireAllRules();
    	
    	assert (acc.volume == 1000);
    	
        kSession.dispose();

    	    	    	
	}
	
	
	
}
