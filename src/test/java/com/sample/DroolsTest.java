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
import com.sample.Conditions.RelationCommercialeEtablie.*;
import com.sample.Conditions.RuptureBrutale.CessationDesCommandes;
import com.sample.Conditions.RuptureBrutale.DuréeEffective;
import com.sample.Conditions.RuptureBrutale.DuréeRaisonnable;
import com.sample.Conditions.RuptureBrutale.Rupture;

public class DroolsTest {
	
	

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
    	
    	Contrat r = new Contrat(victime, auteur, 8, FluxAffaires.IMPORTANT, Activité.ActivitéCommerciale, 0);
    	CessationDesCommandes cdc = new CessationDesCommandes(victime, auteur);
    	
    	DuréeEffective duréeEffective = new DuréeEffective(1);
    	
    	CACommun acc = new CACommun(victime, auteur);
    	acc.setPartDeCA(0.6);
    	acc.setVolume(1000);
    	
    	Marge marge = new Marge(victime, auteur);
    	marge.setTaux(0.20);
    	
    	kSession.insert(r);

    	kSession.insert(cdc);
    	kSession.insert(duréeEffective);  

        kSession.insert(auteur);
        kSession.insert(victime);
        
    	kSession.insert(acc);
    	kSession.insert(marge);
        
        assert(!auteur.responsabilité);
        
        kSession.fireAllRules();
        
    	QueryResults results = kSession.getQueryResults( "getRelations" ); 
    	
    	assert(results.size()>0);
    	for ( QueryResultsRow row : results ) {
    	    Relation relation = ( Relation ) row.get( "$relation" ); //you can retrieve all the bounded variables here
    	    assert(relation.victime.nom == victime.nom);
    	    assert(relation.auteur.nom == auteur.nom);
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
    	    assert(rupture.victime.nom == victime.nom);
    	    assert(rupture.auteur.nom == auteur.nom);
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
    		assert(fg.débiteur.nom == auteur.nom);
    		assert(fg.créancier.nom == victime.nom);
    		assert(fg.contenu=="rupture brutale");
    	}
    	
	    QueryResults results4 = kSession.getQueryResults( "getDommage" ); 
    	assert(results4.size()>0);

	    for ( QueryResultsRow row : results4 ) {
	        Dommage dommage1 = ( Dommage ) row.get( "$result" ); //you can retrieve all the bounded variables here
	        System.out.print(dommage1.montant);
	        assert (dommage1.montant == 200.0);
	        assert (dommage1.estIndemnisable);
	        assert (dommage1.victime.nom == victime.nom);
	    }
	    
	    QueryResults results5 = kSession.getQueryResults( "getCause" ); 
    	assert(results5.size()>0);

	    for ( QueryResultsRow row : results5 ) {
	        Causalite cause = ( Causalite ) row.get( "$cause" ); //you can retrieve all the bounded variables here
	        assert (cause.cause.débiteur.nom == auteur.nom);
	        assert (cause.cause.créancier.nom == victime.nom);
	        assert (cause.cause.contenu == "rupture brutale");
	        assert(cause.effet.victime.nom == victime.nom);
	    }
        
        assert(!victime.responsabilité);
        assert(auteur.responsabilité);

        kSession.dispose();
	}
}
