package com.sample;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.expertsystem.expertsystem.Dialogue;
import com.expertsystem.expertsystem.ID;
import com.expertsystem.expertsystem.TestDialogue;
import com.sample.Conditions.Activité;
import com.sample.Conditions.Relation;

class TestRC {
	
	//Principe
	@Test
	void PrésomptionCommercialité() {
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
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	    	
    	assert(r.commercial);
    	
        kSession.dispose();

	}
	
	@Test
	void Commercialité() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.ActivitéCommerciale);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	    	
    	assert(r.commercial);
    	
        kSession.dispose();

	}
	
	//Qualification rejetée
	@Test
	void AgentCommercial() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.AgentCommercial);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	    	
    	assert(!r.commercial);
    	
        kSession.dispose();

	}
	
	@Test
	void RelationEntreUneSociétéCoopérativeDeCommerçantsDétaillantsEtUnAssocié() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.RelationEntreUneSociétéCoopérativeDeCommerçantsDétaillantsEtUnAssocié);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assert(!r.commercial);
    	
        kSession.dispose();

	}
	
	@Test
	void RelationEntreUneAssociationEtUneSociété() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.RelationEntreUneAssociationEtUneSociété);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assert(!r.commercial);
    	
        kSession.dispose();
}
	
	@Test
	void RelationEntreUnChirurgienDentisteEtSonFournisseurDeMatérielDentaire() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.RelationEntreUnChirurgienDentisteEtSonFournisseurDeMatérielDentaire);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assert(!r.commercial);
    	
        kSession.dispose();

	}
	
	@Test
	void RelationEntreUneSociétéMèreEtUneSociétéFille() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.RelationEntreUneSociétéMèreEtUneSociétéFille);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assert(!r.commercial);
    	
        kSession.dispose();

	}
	
	@Test
	void RelationEntreUnIntermédiaireEtUneSociété() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.RelationEntreUnIntermédiaireEtUneSociété);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assert(!r.commercial);
    	
        kSession.dispose();

	}
	
	@Test
	void RelationEntreUneCliniqueEtUneSociétéDeTransports() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.RelationEntreUneCliniqueEtUneSociétéDeTransports);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assert(!r.commercial);
    	
        kSession.dispose();

	}
	
	//Qualification retenue
	@Test
	void Bail() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.Bail);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assert(r.activité == Activité.Bail);
    	assert(r.commercial);
    	
        kSession.dispose();

	}
	
	@Test
	void LocationDeVéhiculeAvecChauffeur() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.LocationDeVéhiculeAvecChauffeur);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	    	
    	assert(r.commercial);
    	
        kSession.dispose();

	}
	
	
	@Test
	void ContratDePrestationPédagogique() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.ContratDePrestationPédagogique);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assert(r.activité == Activité.ContratDePrestationPédagogique);

    	assert(r.commercial);
    	
        kSession.dispose();

	}
	
	
	@Test
	void RelationEntreUnSyndicatDeCopropriétairesEtUneAutrePartie() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.RelationEntreUnSyndicatDeCopropriétairesEtUneAutrePartie);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assert(r.commercial);
    	
        kSession.dispose();

	}
	
		
	@Test
	void RelationEntreLesPartiesDUnContratDeGéranceMandat() {
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
    	
    	assert(r.commercial);
    	
        kSession.dispose();

	}
	
	@Test
	void RelationEntreUneFédérationSportiveProfessionnelleEtLePrestataireOrganisateurDeVoyages() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.RelationEntreUneFédérationSportiveProfessionnelleEtLePrestataireOrganisateurDeVoyages);
    	
    	kSession.insert(p1);
    	kSession.insert(p2);
    	
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assert(r.commercial);
    	
        kSession.dispose();

	}
}
