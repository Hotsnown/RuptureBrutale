package com.sample.Conditions;

import com.sample.Personne;

public class Dépendance {
    
    public Personne dépendant;
    public Personne dépendu;
    public boolean volontaire;

    public Dépendance (Personne dépendant, Personne dépendu) {
        this.dépendant = dépendant;
        this.dépendu = dépendu;
    }

    public void setVolontaire (boolean volontaire) {
        this.volontaire = volontaire;
    }

}
