package com.sample;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.expertsystem.expertsystem.*;

import com.sample.Conditions.Activité;
import com.sample.Conditions.CACommun;
import com.sample.Conditions.Contrat;
import com.sample.Conditions.EstApplicable;
import com.sample.Conditions.Marge;
import com.sample.Conditions.Relation;
import com.sample.Conditions.RelationCommercialeEtablie.FluxAffaires;
import com.sample.Conditions.RuptureBrutale.CessationDesCommandes;
import com.sample.Conditions.RuptureBrutale.DuréeEffective;
import com.sample.Conditions.RuptureBrutale.DuréeRaisonnable;
import com.sample.Conditions.RuptureBrutale.Rupture;

public class DroolsTest2 {
	
	   @Test
	   public void TestTriplePersonne(){
		   
			KieServices ks = KieServices.Factory.get();
		    KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rules");
			 
			Dialogue dialogue = new TestDialogue();
			HashMap<ID, String> scenario = new HashMap<ID, String>();
			scenario.put(ID.VictimeNom, "victime1");
			scenario.put(ID.AuteurNom, "auteur1");
			((TestDialogue) dialogue).setScenario(scenario);
			kSession.setGlobal("dialogue", dialogue);
			
			Personne victime = new Personne("");
			victime.setId(ID.Victime);
			kSession.insert(victime);
	
			Triple tripleVictime = new Triple(victime, Relations.aNom, "");
			tripleVictime.setId(ID.VictimeNom);
			kSession.insert(tripleVictime);
			
			assertEquals(ID.Victime, tripleVictime.getSujetId());
			assertEquals(ID.VictimeNom, tripleVictime.getId());
			
			Personne auteur = new Personne("");
			auteur.setId(ID.Auteur);
			kSession.insert(auteur);
	
			Triple tripleAuteur = new Triple(auteur, Relations.aNom, "");
			tripleAuteur.setId(ID.AuteurNom);
			kSession.insert(tripleAuteur);
			
			assertEquals(ID.Auteur, tripleAuteur.getSujetId());
			assertEquals(ID.AuteurNom, tripleAuteur.getId());
	        
			kSession.fireAllRules();
	    	   	
	    	QueryResults victimes = kSession.getQueryResults( "getVictimes" ); 
	    	assert(victimes.size() > 0);
	    	
	    	for ( QueryResultsRow row : victimes ) {
	    	    Personne victime1 = ( Personne ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    	    assertEquals("victime1", victime1.nom);
	    	}
	    	
	    	QueryResults auteurs = kSession.getQueryResults( "getAuteurs" ); 
		    assert (auteurs.size() > 0);
		    
	    	for ( QueryResultsRow row : auteurs ) {
	    	    Personne auteur1 = ( Personne ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    	    assertEquals("auteur1", auteur1.nom);
	    	}
	    	
	    	QueryResults personnes = kSession.getQueryResults( "getPersonnes" ); 
		    
	    	for ( QueryResultsRow row : personnes ) {
	    	    Personne personne = ( Personne ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    	    assert(personne.nom != "");
	    	}
	        
	        kSession.dispose();
	     
	   }
	   
	   @Test
	   public void TestContratTriple() {
		    KieServices ks = KieServices.Factory.get();
		    KieContainer kContainer = ks.getKieClasspathContainer();
		    KieSession kSession = kContainer.newKieSession("ksession-rules");
			 
		    Dialogue dialogue = new TestDialogue();
		    HashMap<ID, String> scenario = new HashMap<ID, String>();
		    scenario.put(ID.VictimeNom, "victime1");
		    scenario.put(ID.AuteurNom, "auteur1");
			scenario.put(ID.DuréeRelation, "12");
			scenario.put(ID.FluxAffaires, Integer.toString(FluxAffaires.IMPORTANT));
			scenario.put(ID.Activité, Activité.ActivitéCommerciale.toString());


		    ((TestDialogue) dialogue).setScenario(scenario);
		    kSession.setGlobal("dialogue", dialogue);
		   
		    Personne victime = new Personne("victime1");
		    Personne auteur = new Personne("auteur1");
		   
		    Contrat contrat = new Contrat(victime, auteur, 0, 0, Activité.NA, 0);
		    contrat.setId(ID.Contrat);
		    kSession.insert(contrat);
		   
		    Triple tripleDurée = new Triple(contrat, Relations.aDurée, "");
		    tripleDurée.setId(ID.DuréeRelation);
		    kSession.insert(tripleDurée);
		    		    
		    assertEquals(ID.Contrat, tripleDurée.getSujetId());
		    assertEquals(ID.DuréeRelation, tripleDurée.getId());
		    
		    Triple tripleFluxAffaires = new Triple(contrat, Relations.aFluxAffaires, "");
		    tripleFluxAffaires.setId(ID.FluxAffaires);
		    kSession.insert(tripleFluxAffaires);
		    		    
		    assertEquals(ID.Contrat, tripleFluxAffaires.getSujetId());
		    assertEquals(ID.FluxAffaires, tripleFluxAffaires.getId());
		    
		    Triple tripleActivité = new Triple(contrat, Relations.aActivité, "");
		    tripleActivité.setId(ID.Activité);
		    kSession.insert(tripleActivité);
		    		    
		    assertEquals(ID.Contrat, tripleActivité.getSujetId());
		    assertEquals(ID.Activité, tripleActivité.getId());
		    
		    kSession.fireAllRules();
		    
			QueryResults answers = kSession.getQueryResults( "getAnswers" ); 
			
	    	for ( QueryResultsRow row : answers ) {
	    	    Answer answer = ( Answer ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    	    System.out.print(answer.getQuestion().getText() + " " + answer.answer + "\n");
	    	}
		   
	    	QueryResults contrats = kSession.getQueryResults( "getContrats" ); 
		    assert (contrats.size() > 0);
		    
	    	for ( QueryResultsRow row : contrats ) {
	    	    Contrat contrat1 = ( Contrat ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    	    assertEquals(12, contrat1.durée);
	    	    assertEquals(1, contrat1.fluxAffaires);
	    	    assertEquals(Activité.ActivitéCommerciale, contrat.activité);
	    	}
	    	
	        kSession.dispose();
		   
	   }
	   
	   @Test
	   public void TestCDC() {
		   KieServices ks = KieServices.Factory.get();
		    KieContainer kContainer = ks.getKieClasspathContainer();
		    KieSession kSession = kContainer.newKieSession("ksession-rules");
			 
		    Dialogue dialogue = new TestDialogue();
		    HashMap<ID, String> scenario = new HashMap<ID, String>();
		    scenario.put(ID.VictimeNom, "victime1");
		    scenario.put(ID.AuteurNom, "auteur1");
			scenario.put(ID.DuréeRelation, "12");
			scenario.put(ID.FluxAffaires, Integer.toString(FluxAffaires.IMPORTANT));
			scenario.put(ID.Activité, Activité.ActivitéCommerciale.toString());
			scenario.put(ID.CessationDesCommandes, "true");
			scenario.put(ID.DuréeEffective, "1");
		    ((TestDialogue) dialogue).setScenario(scenario);
		    kSession.setGlobal("dialogue", dialogue);
		    
		    Personne victime = new Personne("victime1");
		    Personne auteur = new Personne("auteur1");
		    
		    CessationDesCommandes cdc = new CessationDesCommandes(victime, auteur);
		    kSession.insert(cdc);
		    
		    Triple tripleCDC = new Triple(cdc, Relations.estQualifié, "");
		    tripleCDC.setId(ID.CessationDesCommandes);
		    kSession.insert(tripleCDC);
		    
		    assertEquals(false, cdc.estQualifié);
		    
		    kSession.fireAllRules();
		    
		    QueryResults cdcs = kSession.getQueryResults( "getCDC" ); 
		    assert (cdcs.size() > 0);
		    
	    	for ( QueryResultsRow row : cdcs ) {
	    	    CessationDesCommandes cdc1 = ( CessationDesCommandes ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    	    assertEquals(true, cdc1.estQualifié);
	    	}
		    
	   }
	   
	   @Test
	   public void TestDuréeEffective() {
		   KieServices ks = KieServices.Factory.get();
		    KieContainer kContainer = ks.getKieClasspathContainer();
		    KieSession kSession = kContainer.newKieSession("ksession-rules");
			 
		    Dialogue dialogue = new TestDialogue();
		    HashMap<ID, String> scenario = new HashMap<ID, String>();
		    scenario.put(ID.VictimeNom, "victime1");
		    scenario.put(ID.AuteurNom, "auteur1");
			scenario.put(ID.DuréeRelation, "12");
			scenario.put(ID.FluxAffaires, Integer.toString(FluxAffaires.IMPORTANT));
			scenario.put(ID.Activité, Activité.ActivitéCommerciale.toString());
			scenario.put(ID.CessationDesCommandes, "true");
			scenario.put(ID.DuréeEffective, "1");
		    ((TestDialogue) dialogue).setScenario(scenario);
		    kSession.setGlobal("dialogue", dialogue);
		    
		    
		    DuréeEffective de = new DuréeEffective(0);
		    kSession.insert(de);
		    
		    Triple tripleDuréeEffective = new Triple(de, Relations.aDurée, "");
		    tripleDuréeEffective.setId(ID.DuréeEffective);
		    kSession.insert(tripleDuréeEffective);
		    
		    assertEquals(0, de.durée);
		    
		    kSession.fireAllRules();
		    
		    QueryResults des = kSession.getQueryResults( "getDE" ); 
		    assert (des.size() > 0);
		    
	    	for ( QueryResultsRow row : des ) {
	    	    DuréeEffective de1 = ( DuréeEffective ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    	    System.out.print(tripleDuréeEffective.getSujetId());
	    	    assertEquals(1, de1.durée);
	    	}
		    
	   }
	   
	   @Test
	   public void TestCACommun() {
		   KieServices ks = KieServices.Factory.get();
		    KieContainer kContainer = ks.getKieClasspathContainer();
		    KieSession kSession = kContainer.newKieSession("ksession-rules");
			 
		    Dialogue dialogue = new TestDialogue();
		    HashMap<ID, String> scenario = new HashMap<ID, String>();
		    scenario.put(ID.VictimeNom, "victime1");
		    scenario.put(ID.AuteurNom, "auteur1");
			scenario.put(ID.DuréeRelation, "12");
			scenario.put(ID.FluxAffaires, Integer.toString(FluxAffaires.IMPORTANT));
			scenario.put(ID.Activité, Activité.ActivitéCommerciale.toString());
			scenario.put(ID.CessationDesCommandes, "true");
			scenario.put(ID.DuréeEffective, "1");
			scenario.put(ID.PartDeCA, "0.6");
			scenario.put(ID.VolumeDeCA, "1000");
		    ((TestDialogue) dialogue).setScenario(scenario);
		    kSession.setGlobal("dialogue", dialogue);
		    
		    Personne victime = new Personne("victime1");
		    Personne auteur = new Personne("auteur1");
		    
		    CACommun cac = new CACommun(victime, auteur);
		    cac.setId(ID.CACommun);
		    kSession.insert(cac);
		    
		    Triple triplepartDeCA = new Triple(cac, Relations.partDeCA, "");
		    triplepartDeCA.setId(ID.PartDeCA);
		    kSession.insert(triplepartDeCA);
		    
		    assertEquals(ID.CACommun, triplepartDeCA.getSujetId());
		    assertEquals(ID.PartDeCA, triplepartDeCA.getId());
		    
		    
		    Triple tripleVolumeCACommun = new Triple(cac, Relations.volumeDeCA, "");
		    tripleVolumeCACommun.setId(ID.VolumeDeCA);
		    kSession.insert(tripleVolumeCACommun);
		    
		    assertEquals(ID.CACommun, tripleVolumeCACommun.getSujetId());
		    assertEquals(ID.VolumeDeCA, tripleVolumeCACommun.getId());
		    		    
		    kSession.fireAllRules();
		    
			QueryResults answers = kSession.getQueryResults( "getAnswers" ); 
			
	    	for ( QueryResultsRow row : answers ) {
	    	    Answer answer = ( Answer ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    	    System.out.print(answer.getQuestion().getText() + " " + answer.answer + "\n");
	    	}
		    
		    QueryResults cacs = kSession.getQueryResults( "getCAC" ); 
		    assert (cacs.size() > 0);
		    
	    	for ( QueryResultsRow row : cacs ) {
	    		CACommun cac1 = ( CACommun ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    	    System.out.print(cac.id);
	    		assertEquals(0.6, cac1.partDeCA, 0.0001);
	    	    assertEquals(1000, cac1.volume, 0.0001);
	    	}
		    
	   }
	   
	   @Test
	   public void TestEnd2End() {
		   KieServices ks = KieServices.Factory.get();
		    KieContainer kContainer = ks.getKieClasspathContainer();
		    KieSession kSession = kContainer.newKieSession("ksession-rules");
			 
		    Dialogue dialogue = new TestDialogue();
		    HashMap<ID, String> scenario = new HashMap<ID, String>();
		    scenario.put(ID.VictimeNom, "victime1");
		    scenario.put(ID.AuteurNom, "auteur1");
			scenario.put(ID.DuréeRelation, "12");
			scenario.put(ID.FluxAffaires, Integer.toString(FluxAffaires.IMPORTANT));
			scenario.put(ID.Activité, Activité.ActivitéCommerciale.toString());
			scenario.put(ID.CessationDesCommandes, "true");
			scenario.put(ID.DuréeEffective, "1");
			scenario.put(ID.PartDeCA, "0.6");
			scenario.put(ID.VolumeDeCA, "1000");
			scenario.put(ID.TauxMarge, "0.2");
		    ((TestDialogue) dialogue).setScenario(scenario);
		    kSession.setGlobal("dialogue", dialogue);
		    
		       
		    Personne victime = new Personne("");
			victime.setId(ID.Victime);
			kSession.insert(victime);

			Triple tripleVictime = new Triple(victime, Relations.aNom, "");
			tripleVictime.setId(ID.VictimeNom);
			kSession.insert(tripleVictime);
			
			assertEquals(ID.Victime, tripleVictime.getSujetId());
			assertEquals(ID.VictimeNom, tripleVictime.getId());
			
			Personne auteur = new Personne("");
			auteur.setId(ID.Auteur);
			kSession.insert(auteur);

			Triple tripleAuteur = new Triple(auteur, Relations.aNom, "");
			tripleAuteur.setId(ID.AuteurNom);
			kSession.insert(tripleAuteur);
			
			assertEquals(ID.Auteur, tripleAuteur.getSujetId());
			assertEquals(ID.AuteurNom, tripleAuteur.getId());
		        	 
		    Contrat contrat = new Contrat(victime, auteur, 0, 0, Activité.NA, 0);
		    contrat.setId(ID.Contrat);
		    kSession.insert(contrat);
		   
		    Triple tripleDurée = new Triple(contrat, Relations.aDurée, "");
		    tripleDurée.setId(ID.DuréeRelation);
		    kSession.insert(tripleDurée);
		    		    
		    assertEquals(ID.Contrat, tripleDurée.getSujetId());
		    assertEquals(ID.DuréeRelation, tripleDurée.getId());
		    
		    Triple tripleFluxAffaires = new Triple(contrat, Relations.aFluxAffaires, "");
		    tripleFluxAffaires.setId(ID.FluxAffaires);
		    kSession.insert(tripleFluxAffaires);
		    		    
		    assertEquals(ID.Contrat, tripleFluxAffaires.getSujetId());
		    assertEquals(ID.FluxAffaires, tripleFluxAffaires.getId());
		    
		    Triple tripleActivité = new Triple(contrat, Relations.aActivité, "");
		    tripleActivité.setId(ID.Activité);
		    kSession.insert(tripleActivité);
		    		    
		    assertEquals(ID.Contrat, tripleActivité.getSujetId());
		    assertEquals(ID.Activité, tripleActivité.getId());
		    		    
		    CessationDesCommandes cdc = new CessationDesCommandes(victime, auteur);
		    cdc.setId(ID.CessationDesCommandes);
		    kSession.insert(cdc);
		    
		    Triple tripleCDC = new Triple(cdc, Relations.estQualifié, "");
		    tripleCDC.setId(ID.CessationDesCommandes);
		    kSession.insert(tripleCDC);
		    
		    assertEquals(ID.CessationDesCommandes, tripleCDC.getId());
		    assertEquals(ID.CessationDesCommandes, tripleCDC.getSujetId());
		    assertEquals(false, cdc.estQualifié);
		    
		    DuréeEffective de = new DuréeEffective(12);
		    kSession.insert(de);
		    
		    Triple tripleDuréeEffective = new Triple(de, Relations.aDurée, "");
		    tripleDuréeEffective.setId(ID.DuréeEffective);
		    kSession.insert(tripleDuréeEffective);
		    
		    CACommun cac = new CACommun(victime, auteur);
		    cac.setId(ID.CACommun);
		    kSession.insert(cac);
		    
		    Triple triplepartDeCA = new Triple(cac, Relations.partDeCA, "");
		    triplepartDeCA.setId(ID.PartDeCA);
		    kSession.insert(triplepartDeCA);
		    
		    assertEquals(ID.CACommun, triplepartDeCA.getSujetId());
		    assertEquals(ID.PartDeCA, triplepartDeCA.getId());
		    		    
		    Triple tripleVolumeCACommun = new Triple(cac, Relations.volumeDeCA, "");
		    tripleVolumeCACommun.setId(ID.VolumeDeCA);
		    kSession.insert(tripleVolumeCACommun);
		    
		    assertEquals(ID.CACommun, tripleVolumeCACommun.getSujetId());
		    assertEquals(ID.VolumeDeCA, tripleVolumeCACommun.getId());
		    
		    Marge marge = new Marge(victime, auteur);
		    marge.setId(ID.Marge);
		    kSession.insert(marge);
		    
		    Triple tripleMarge = new Triple(marge, Relations.aTaux, "");
		    tripleMarge.setId(ID.TauxMarge);
		    kSession.insert(tripleMarge);
		    
		    assertEquals(ID.Marge, tripleMarge.getSujetId());
		    assertEquals(ID.TauxMarge, tripleMarge.getId());
		    
		    
		    DuréeRaisonnable duréeRaisonnable = new DuréeRaisonnable(victime, auteur);
	    	duréeRaisonnable.setDuréePréavis(24);
	    	kSession.insert(duréeRaisonnable);
	    	
		    kSession.fireAllRules(); 	
	    	
	    	QueryResults victimes = kSession.getQueryResults( "getVictimes" ); 
		    assert (victimes.size() > 0);
		    
	    	for ( QueryResultsRow row : victimes ) {
	    	    Personne victime1 = ( Personne ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    	    assertEquals("victime1", victime1.nom);
	    	}
	    	
	    	QueryResults auteurs = kSession.getQueryResults( "getAuteurs" ); 
		    assert (auteurs.size() > 0);
		    
	    	for ( QueryResultsRow row : auteurs ) {
	    	    Personne auteur1 = ( Personne ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    	    assertEquals("auteur1", auteur1.nom);
	    	}
	    	
	    	QueryResults contrats = kSession.getQueryResults( "getContrats" ); 
		    assert (contrats.size() > 0);
		    
	    	for ( QueryResultsRow row : contrats ) {
	    	    Contrat contrat1 = ( Contrat ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    	    assertEquals(12, contrat1.durée);
	    	    assertEquals(1, contrat1.fluxAffaires);
	    	    assertEquals(Activité.ActivitéCommerciale, contrat.activité);
	    	}
	    	
	    	QueryResults results = kSession.getQueryResults( "getRelations" ); 
	    	
	    	assert(results.size()>0);
	    	for ( QueryResultsRow row : results ) {
	    	    Relation relation = ( Relation ) row.get( "$relation" ); //you can retrieve all the bounded variables here
	    	    assertEquals(relation.victime.nom, victime.nom);
	    	    assertEquals(relation.auteur.nom, auteur.nom);
	    	    assert(relation.commercial);
	    	    assert(relation.établie);
	    	    assert(relation.stable);
	    	    assert(relation.régulière);
	    	    assert(relation.suivie);
	    	}
	    	
	    	QueryResults results1 = kSession.getQueryResults( "getRuptures" ); 
	    	assert(results1.size()>0);

	    	for ( QueryResultsRow row : results1 ) {
	    	    Rupture rupture = ( Rupture ) row.get( "$rupture" ); //you can retrieve all the bounded variables here
	    	    assertEquals(rupture.victime.nom, victime.nom);
	    	    assertEquals(rupture.auteur.nom, auteur.nom);
	    	    assert(rupture.brutale);
	    	}
	    	
	    	
	        QueryResults results2 = kSession.getQueryResults( "getEstApplicable" ); 
	    	assert(results2.size()>0);

	        for ( QueryResultsRow row : results2 ) {
	            EstApplicable estApplicable = ( EstApplicable ) row.get( "$result" ); //you can retrieve all the bounded variables here
	            assert (estApplicable.applicable);
	        }
	        
	    	QueryResults results3 = kSession.getQueryResults( "getFaute" ); 
	    	assert(results3.size()>0);

	    	for ( QueryResultsRow row : results3 ) {
	    		FaitGenerateur fg = ( FaitGenerateur ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    		assertEquals(fg.débiteur.nom, auteur.nom);
	    		assertEquals(fg.créancier.nom, victime.nom);
	    		assertEquals(fg.contenu, "rupture brutale");
	    	}
	    	
		    QueryResults results4 = kSession.getQueryResults( "getDommage" ); 
	    	assert(results4.size()>0);

		    for ( QueryResultsRow row : results4 ) {
		        Dommage dommage1 = ( Dommage ) row.get( "$result" ); //you can retrieve all the bounded variables here
		        assertEquals (200.0, dommage1.montant, 0.0001);
		        assert (dommage1.estIndemnisable);
		        assertEquals (dommage1.victime.nom, victime.nom);
		    }
		    
		    QueryResults results5 = kSession.getQueryResults( "getCause" ); 
	    	assert(results5.size()>0);

		    for ( QueryResultsRow row : results5 ) {
		        Causalite cause = ( Causalite ) row.get( "$cause" ); //you can retrieve all the bounded variables here
		        assertEquals (cause.cause.débiteur.nom, auteur.nom);
		        assertEquals (cause.cause.créancier.nom, victime.nom);
		        assertEquals (cause.cause.contenu, "rupture brutale");
		        assertEquals(cause.effet.victime.nom, victime.nom);
		    }
	    	
	        assert(!victime.responsabilité);
	        assert(auteur.responsabilité);
	        
	        kSession.dispose();
		    
	   }
	   
	   
	   @Test
	   public void TestExit() {
		  KieServices ks = KieServices.Factory.get();
	      KieContainer kContainer = ks.getKieClasspathContainer();
		  KieSession kSession = kContainer.newKieSession("ksession-rules");
		 		  
		  Dialogue dialogue = new TestDialogue();
		  HashMap<ID, String> scenario = new HashMap<ID, String>();
		  scenario.put(ID.VictimeNom, "exit");
		  ((TestDialogue) dialogue).setScenario(scenario);
		  kSession.setGlobal("dialogue", dialogue);
		  
		  kSession.fireAllRules();
		  
		  kSession.dispose();
			
	   }
	   
	   @Test
	   public void TestMarge() {
		   KieServices ks = KieServices.Factory.get();
		    KieContainer kContainer = ks.getKieClasspathContainer();
		    KieSession kSession = kContainer.newKieSession("ksession-rules");
			 
		    Dialogue dialogue = new TestDialogue();
		    HashMap<ID, String> scenario = new HashMap<ID, String>();
		    scenario.put(ID.VictimeNom, "victime1");
		    scenario.put(ID.AuteurNom, "auteur1");
			scenario.put(ID.DuréeRelation, "12");
			scenario.put(ID.FluxAffaires, Integer.toString(FluxAffaires.IMPORTANT));
			scenario.put(ID.Activité, Activité.ActivitéCommerciale.toString());
			scenario.put(ID.CessationDesCommandes, "true");
			scenario.put(ID.DuréeEffective, "1");
			scenario.put(ID.PartDeCA, "0.6");
			scenario.put(ID.VolumeDeCA, "1000");
			scenario.put(ID.TauxMarge, "0.2");
		    ((TestDialogue) dialogue).setScenario(scenario);
		    kSession.setGlobal("dialogue", dialogue);
		    
		    Personne victime = new Personne("victime1");
		    Personne auteur = new Personne("auteur1");
		    
		    Marge marge = new Marge(victime, auteur);
		    marge.setId(ID.Marge);
		    kSession.insert(marge);
		    
		    Triple tripleMarge = new Triple(marge, Relations.aTaux, "");
		    tripleMarge.setId(ID.TauxMarge);
		    kSession.insert(tripleMarge);
		    
		    assertEquals(ID.Marge, tripleMarge.getSujetId());
		    assertEquals(ID.TauxMarge, tripleMarge.getId());
		    
		    		    
		    kSession.fireAllRules();
		    
			QueryResults answers = kSession.getQueryResults( "getAnswers" ); 
			
		    
		    QueryResults marges = kSession.getQueryResults( "getMarge" ); 
		    assert (marges.size() > 0);
		    
	    	for ( QueryResultsRow row : marges ) {
	    		Marge marge1 = ( Marge ) row.get( "$result" ); //you can retrieve all the bounded variables here
	    		assertEquals(0.2, marge1.taux, 0.0001);
	    	}
	    	
			  kSession.dispose();
	   }
}
