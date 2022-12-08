package com.expertsystem.expertsystem;

public class SituationFactuelle {
	public static Question newQ1() {
        return new Question(ID.VictimeNom, "Quel est le nom de la victime?");
    }

    public static Question newQ2() {
        return new Question(ID.AuteurNom, "Quel est le nom de l'auteur?");
    }
    
    public static Question newQ3() {
        return new Question(ID.DuréeRelation, "Quelle est la durée du contrat ?");
    }
    
    public static Question newQ4() {
        return new Question(ID.FluxAffaires, "Quelle est l'intensité du flux d'affaires ?");
    }
    
    public static Question newQ5() {
        return new Question(ID.Activité, "Quelle est l'activité ?");
    }
    
    public static Question newQ6() {
        return new Question(ID.CessationDesCommandes, "Il y a-t-il une cessation des commandes ?");
    }
    
    public static Question newQ7() {
        return new Question(ID.DuréeEffective, "Quelle est la durée effective de préavis ?");
    }
    
    public static Question newQ8() {
        return new Question(ID.PartDeCA, "Quelle est la part du CA commun ?");
    }
    
    public static Question newQ9() {
        return new Question(ID.VolumeDeCA, "Quelle est le volume de CA commun ?");
    }
        
    public static Question newQ10() {
        return new Question(ID.TauxMarge, "Quelle est le taux de marge ?");
    }
}