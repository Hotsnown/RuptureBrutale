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
import com.sample.Conditions.RuptureBrutale.DuréeRaisonnable;
import com.sample.Conditions.*;
import static org.junit.Assert.assertEquals;

class TestFacteurs {
	
	@Test
	void NormeContractuelle() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.NA);
    	Norme cp = new Norme(p1, p2, ThèmeNormatif.préavis, 10);
    	
    	Clause clausePréavis = new Clause(cp);
    	DuréeRaisonnable dr = new DuréeRaisonnable(p1, p2);
    	
    	kSession.insert(clausePréavis);
    	kSession.insert(dr);
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assertEquals(cp.modalité, dr.durée);
    	
        kSession.dispose();

	}
	
	@Test
	void NormeAgentsCommerciaux() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.NA);
    	NormeAgentsCommerciaux nac = new NormeAgentsCommerciaux();
    	
    	DuréeRaisonnable dr = new DuréeRaisonnable(p1, p2);
    	
    	kSession.insert(nac);
    	kSession.insert(dr);
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assertEquals(10, dr.durée);
    	
        kSession.dispose();

	}
	
	@Test
	void NormeGérantsMandatairesDeMaisonsDalimentation() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.NA);
    	NormeGérantsMandatairesDeMaisonsDalimentation norme = new NormeGérantsMandatairesDeMaisonsDalimentation();
    	
    	DuréeRaisonnable dr = new DuréeRaisonnable(p1, p2);
    	
    	kSession.insert(norme);
    	kSession.insert(dr);
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assertEquals(10, dr.durée);
    	
        kSession.dispose();

	}
	
	@Test
	void NormeContratTypeLOTI() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.NA);
    	NormeContratTypeLOTI norme = new NormeContratTypeLOTI();
    	
    	DuréeRaisonnable dr = new DuréeRaisonnable(p1, p2);
    	
    	kSession.insert(norme);
    	kSession.insert(dr);
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assertEquals(10, dr.durée);
    	
        kSession.dispose();

	}
	
	@Test
	void NormeConcoursBancaire() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.NA);
    	NormeConcoursBancaire norme = new NormeConcoursBancaire();
    	
    	DuréeRaisonnable dr = new DuréeRaisonnable(p1, p2);
    	
    	kSession.insert(norme);
    	kSession.insert(dr);
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assertEquals(10, dr.durée);
    	
        kSession.dispose();

	}
	
	@Test
	void InvestissementSpécifique() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Relation r = new Relation(p1, p2, 0, 0, Activité.NA);
    	Investissement investissement = new Investissement();
    	
    	DuréeRaisonnable dr = new DuréeRaisonnable(p1, p2);
    	
    	kSession.insert(investissement);
    	kSession.insert(dr);
    	kSession.insert(r);
    	
    	kSession.fireAllRules();
    	
    	assertEquals(2, dr.durée);
    	
        kSession.dispose();

	}

	@Test
	void Dépendance() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	
    	Dépendance dépendance = new Dépendance(p1, p2);

		kSession.insert(p1);
		kSession.insert(p2);
		kSession.insert(dépendance);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" ); 
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceProduitNonSubstituable() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Produit produit = new Produit(p1, p2);
		produit.setSubstituable(QualificationBooleenne.Faux);
    	
		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(produit);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceProduitNotoire() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Produit produit = new Produit(p1, p2);
		produit.setNotoire(QualificationBooleenne.Vrai);
    	
		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(produit);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceProduiTechnique() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Produit produit = new Produit(p1, p2);
		produit.setTechnique(QualificationBooleenne.Vrai);
    	
		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(produit);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceProduitDistribuéSousMDD() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Produit produit = new Produit(p1, p2);
		produit.setDistribuéSousMDD(QualificationBooleenne.Vrai);
    	
		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(produit);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceProduitSaisonnier() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Produit produit = new Produit(p1, p2);
		produit.setSaisonnier(QualificationBooleenne.Vrai);
    	
		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(produit);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceProduitNouveau() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Produit produit = new Produit(p1, p2);
		produit.setNouveau(QualificationBooleenne.Vrai);
    	
		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(produit);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceObligationExclusivité() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Relation relation = new Relation(p1, p2, 0, 0, null);

		Clause clause = new Clause(new Norme(p1, p2, ThèmeNormatif.ObligationExclusivité, 0));
    	
		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(relation);
		kSession.insert(clause);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendancePacteDePréférence() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Relation relation = new Relation(p1, p2, 0, 0, null);

		PacteDePréférence pdp = new PacteDePréférence();

		kSession.insert(relation);

		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(pdp);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceClauseDeNonConcurrence() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Relation relation = new Relation(p1, p2, 0, 0, null);

		ClauseDeNonConcurrence cnc = new ClauseDeNonConcurrence();

		kSession.insert(relation);

		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(cnc);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceClauseNonAffiliation() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Relation relation = new Relation(p1, p2, 0, 0, null);

		ClauseDeNonAffiliation cna = new ClauseDeNonAffiliation();

		kSession.insert(relation);

		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(cna);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceObligationsExigiblesDuFaitDeLaRupture() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Relation relation = new Relation(p1, p2, 0, 0, null);

		ObligationsExigiblesDuFaitDeLaRupture exigible = new ObligationsExigiblesDuFaitDeLaRupture();

		kSession.insert(relation);

		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(exigible);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceGroupeDeContrat() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Relation relation = new Relation(p1, p2, 0, 0, null);

		GroupeDeContrat groupe = new GroupeDeContrat();

		kSession.insert(relation);

		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(groupe);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceObligationIndemnisations() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Relation relation = new Relation(p1, p2, 0, 0, null);

		ObligationIndemnisation indemnisation = new ObligationIndemnisation();

		kSession.insert(relation);

		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(indemnisation);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DépendanceObligationDestruction() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");

		Relation relation = new Relation(p1, p2, 0, 0, null);

		ObligationIndemnisation indemnisation = new ObligationIndemnisation();

		kSession.insert(relation);

		kSession.insert(p1);
		kSession.insert(p2);

		kSession.insert(indemnisation);
    	
    	kSession.fireAllRules();
    			  
	    QueryResults dépendances = kSession.getQueryResults( "getDépendances" );
		assert(dépendances.size() > 0);

	    for ( QueryResultsRow row : dépendances ) {
	        Dépendance dépendance1 = ( Dépendance ) row.get( "$result" ); //you can retrieve all the bounded variables here
			assertEquals(dépendance1.dépendant.nom, p1.nom);
			assertEquals(dépendance1.dépendu.nom, p2.nom);
		}
    	
        kSession.dispose();

	}

	@Test
	void DixHuitMoisMax() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

    	Dialogue dialogue = new TestDialogue();
    	HashMap<ID, String> scenario = new HashMap<ID, String>();
	    ((TestDialogue) dialogue).setScenario(scenario);
    	kSession.setGlobal("dialogue", dialogue);
    	    	
    	Personne p1 = new Personne("personne1");
    	Personne p2 = new Personne("personne2");
    	     	
    	DuréeRaisonnable dr = new DuréeRaisonnable(p1, p2);
    	dr.setDuréePréavis(24);

    	kSession.insert(dr);
    	
    	kSession.fireAllRules();
    	
    	assertEquals(18, dr.durée);
    	
        kSession.dispose();

	}

}
