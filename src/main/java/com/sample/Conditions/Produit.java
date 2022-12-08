package com.sample.Conditions;

import com.sample.Personne;

public class Produit {

    public Personne acheteur;
    public Personne vendeur;

    public QualificationBooleenne substituable;
    public QualificationBooleenne notoire;
    public QualificationBooleenne technique;
    public QualificationBooleenne distribuéSousMDD;
    public QualificationBooleenne saisonnier;
    public QualificationBooleenne nouveau;

    public Produit (Personne acheteur, Personne vendeur) {
        this.acheteur = acheteur;
        this.vendeur =  vendeur;
    }

    public void setSubstituable(QualificationBooleenne substituable) {
        this.substituable = substituable;
    }

    public QualificationBooleenne getSubstituable() {
        return this.substituable;
    }

    public void setNotoire(QualificationBooleenne notoire) {
        this.notoire = notoire;
    }
    public void setTechnique(QualificationBooleenne technique) {
        this.technique = technique;
    }

    public void setDistribuéSousMDD(QualificationBooleenne distribuéSousMDD) {
        this.distribuéSousMDD = distribuéSousMDD;
    }

    public void setNouveau(QualificationBooleenne nouveau) {
        this.nouveau = nouveau;
    }

    public void setSaisonnier(QualificationBooleenne saisonnier) {
        this.saisonnier = saisonnier;
    }

}
