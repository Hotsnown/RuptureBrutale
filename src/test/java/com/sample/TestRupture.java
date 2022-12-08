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
import com.sample.Conditions.AbsenceOffre;
import com.sample.Conditions.Activité;
import com.sample.Conditions.AppelOffre;
import com.sample.Conditions.Contrat;
import com.sample.Conditions.Dénonciation;
import com.sample.Conditions.Déréférencement;
import com.sample.Conditions.Justification;
import com.sample.Conditions.Relation;
import com.sample.Conditions.RetraitObligationExclusivité;
import com.sample.Conditions.RelationCommercialeEtablie.FluxAffaires;
import com.sample.Conditions.RuptureBrutale.Brutalité;
import com.sample.Conditions.RuptureBrutale.CessationDesCommandes;
import com.sample.Conditions.RuptureBrutale.DiminutionDuCA;
import com.sample.Conditions.RuptureBrutale.ModificationDesConditionsDeLaRelation;
import com.sample.Conditions.RuptureBrutale.Rupture;
import com.sample.Conditions.RuptureBrutale.SuspensionDuContrat;

class TestRupture {
	
	@Test
	void TestCessationDesCommandesVautRupture() {
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
    	CessationDesCommandes cdc = new CessationDesCommandes(victime, auteur);
    	    	
    	kSession.insert(r);
    	kSession.insert(cdc);
    	
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
    	QueryResults results = kSession.getQueryResults( "getRuptures" ); 
    	
    	for ( QueryResultsRow row : results ) {
    	    Rupture rupture = ( Rupture ) row.get( "$rupture" ); //you can retrieve all the bounded variables here
    	    assert(rupture.victime.nom == victime.nom);
    	    assert(rupture.auteur.nom == auteur.nom);
    	}
    	
        kSession.dispose();

        
	}
	
	//RUPTURE TOTALE
	
	@Test
	void TestAppelOffreEstUneRupture() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne auteur = new Personne("auteur1");
    	Personne victime = new Personne("victime1");
    	Personne tiers = new Personne("tiers1");
    	
    	Relation r = new Relation(victime, auteur, 8, FluxAffaires.IMPORTANT, Activité.ActivitéCommerciale);
       	AppelOffre ao = new AppelOffre(auteur, tiers);
    	Rupture rupture = new Rupture(victime, auteur);
    	    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	kSession.insert(ao);
    	    	        
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
        assert(rupture.totale);   
        
        kSession.dispose();

	}
	
	@Test
	void TestDénonciationDuContratVautRupture() {
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
    	Rupture rupture = new Rupture(victime, auteur);
    	Contrat contrat = new Contrat(victime, auteur, 8, FluxAffaires.IMPORTANT, Activité.ActivitéCommerciale, 0);
    	Dénonciation dénonciation = new Dénonciation(contrat);
    	    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	kSession.insert(contrat);
    	kSession.insert(dénonciation);
    	
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
        assert(rupture.totale);  
        
        kSession.dispose();

	}
	
	@Test
	void TestDéréférencementVautRupture() {
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
    	Rupture rupture = new Rupture(victime, auteur);
    	Déréférencement dénonciation = new Déréférencement(victime, auteur);
    	    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	kSession.insert(dénonciation);
    	
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
        assert(rupture.totale);  
        
        kSession.dispose();

	}
	
	@Test
	void ModificationSubstantielleDesConditionsDeLaRelationVautRuptureTotale() {
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
    	
    	ModificationDesConditionsDeLaRelation mod = new ModificationDesConditionsDeLaRelation(victime, auteur);
    	mod.setSubstantielle(true);
    	Rupture rupture = new Rupture(victime, auteur);
    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	
    	kSession.insert(mod);
    	    	
        
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
    	assert(rupture.totale);
    	
        kSession.dispose();

	}
	
	@Test
	void ModificationNonSubstantielleDesConditionsDeLaRelationNeVautPasRuptureTotale() {
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
    	
    	ModificationDesConditionsDeLaRelation mod = new ModificationDesConditionsDeLaRelation(victime, auteur);
    	mod.setSubstantielle(false);
    	Rupture rupture = new Rupture(victime, auteur);
    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	
    	kSession.insert(mod);
    	    	
        
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
    	assert(!rupture.totale);
    	
        kSession.dispose();

	}
	
	@Test
	void ModificationSubstantielleJustifiéeDesConditionsDeLaRelationNeVautPasRuptureTotale() {
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
    	
    	ModificationDesConditionsDeLaRelation mod = new ModificationDesConditionsDeLaRelation(victime, auteur);
    	mod.setSubstantielle(true);
    	mod.setJustification(Justification.CriseEconomique);
    	Rupture rupture = new Rupture(victime, auteur);
    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	
    	kSession.insert(mod);
    	    	
        
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
    	assert(!rupture.totale);
    	
        kSession.dispose();

	}
	
	@Test
	void ModificationSubstantielleNonJustifiéeDesConditionsDeLaRelationNeVautPasRuptureTotale() {
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
    	
    	ModificationDesConditionsDeLaRelation mod = new ModificationDesConditionsDeLaRelation(victime, auteur);
    	mod.setSubstantielle(true);
    	mod.setJustification(Justification.NA);
    	Rupture rupture = new Rupture(victime, auteur);
    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	
    	kSession.insert(mod);
    	    	
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
    	assert(!rupture.totale);
    	
        kSession.dispose();

	}
	
	@Test
	void LibertéContractuelleNeVautPasRuptureTotale() {
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
    	
    	AbsenceOffre no = new AbsenceOffre(victime, auteur);
    	Rupture rupture = new Rupture(victime, auteur);
    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	
    	kSession.insert(no);
    	    	
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
    	assert(!rupture.totale);
    	
        kSession.dispose();

	}
	
	@Test
	void SuspensionDuContratNeVautPasRuptureTotale() {
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
    	
    	SuspensionDuContrat sus = new SuspensionDuContrat(victime, auteur);
    	Rupture rupture = new Rupture(victime, auteur);
    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	
    	kSession.insert(sus);
    	    	
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
    	assert(!rupture.totale);
    	
        kSession.dispose();

	}
	
	//RUPTURE PARTIELLE
	@Test
	void ModificationSubstantielleDesConditionsDeLaRelation() {
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
    	
    	ModificationDesConditionsDeLaRelation mod = new ModificationDesConditionsDeLaRelation(victime, auteur);
    	mod.setSubstantielle(true);
    	Rupture rupture = new Rupture(victime, auteur);
    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	
    	kSession.insert(mod);
    	    	
        
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
    	assert(rupture.partielle);
    	
        kSession.dispose();

	}
	
	@Test
	void ModificationSubstantielleJustifiéeDesConditionsDeLaRelation() {
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
    	
    	ModificationDesConditionsDeLaRelation mod = new ModificationDesConditionsDeLaRelation(victime, auteur);
    	mod.setSubstantielle(true);
    	mod.setJustification(Justification.CriseSanitaire);
    	Rupture rupture = new Rupture(victime, auteur);
    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	
    	kSession.insert(mod);
    	    	
        
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
    	assert(!rupture.partielle);
    	
        kSession.dispose();

	}
	
	@Test
	void RetraitObligationExclusivitéVautRupturePartielle() {
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
    	
    	RetraitObligationExclusivité retrait = new RetraitObligationExclusivité(victime, auteur);
    	Rupture rupture = new Rupture(victime, auteur);
    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	
    	kSession.insert(retrait);
    	retrait.setUnilatéral(true);
    	    	
        
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
    	assert(rupture.partielle);
    	
        kSession.dispose();

	}
	

	@Test
	void TestDiminutionDuCAEstUneRuptureTaux61() {
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
    	
        Rupture rupture = new Rupture(victime, auteur);
    	DiminutionDuCA dca = new DiminutionDuCA(victime, auteur, 0.61);
    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	kSession.insert(dca);
    	    	
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
		assert(rupture.partielle);
		
        kSession.dispose();

	}
	
	@Test
	void TestDiminutionDuCAHabituelleNeVautPasRupturePartielle() {
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
    	
        Rupture rupture = new Rupture(victime, auteur);
    	DiminutionDuCA dca = new DiminutionDuCA(victime, auteur, 0.61);
    	dca.setHabituelle(true);
    	
    	kSession.insert(r);
    	kSession.insert(rupture);
    	kSession.insert(dca);
    	    	
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
		assert(!rupture.partielle);
		
        kSession.dispose();

	}
	
	
	@Test
	void TestDiminutionDuCAEstUneRuptureTaux50() {
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
    	
    	//Rupture
    	DiminutionDuCA dca = new DiminutionDuCA(victime, auteur, 0.50);
    	Brutalité brutalite = new Brutalité(victime, auteur);
    	Dommage dommage = new Dommage(victime);
    	
    	kSession.insert(r);
    	
    	kSession.insert(dca);
    	kSession.insert(brutalite);
    	    	
        kSession.insert(dommage);
        
        kSession.insert(auteur);
        kSession.insert(victime);
        
        kSession.fireAllRules();
        
        assert(!auteur.responsabilité);
        assert(!victime.responsabilité);
        
        kSession.dispose();

	}
}
