package com.sample.Conditions;
import com.expertsystem.expertsystem.ID;
import com.expertsystem.expertsystem.Thing;
import com.sample.Clause;
import com.sample.Personne;
import com.sample.Relations;

public class Contrat extends Thing {

		public Personne p1;
		public Personne p2;
		public int durée;
		public int fluxAffaires;
		public Activité activité;
		public Clause[] estComposéDe;

		public int idGroupeDeContrat ;
		public ID id;

		
		public ID getId() {
			return id;
		}

		public void setId(ID id) {
			this.id = id;
		}

		public Contrat(Personne p1, Personne p2, int durée, int fluxAffaires, Activité activité, int idGroupeDeContrat) {
			this.p1 = p1;
			this.p2 = p2;
			this.durée = durée;
			this.fluxAffaires = fluxAffaires;
			this.activité = activité;
	        this.idGroupeDeContrat = idGroupeDeContrat;

		}
		
		public int getDurée() {
			return this.durée;
		}
		
		public void setDurée(int durée) {
			this.durée = durée;
		}
		
		public Personne getp1() {
			return this.p1;
		}
		
		public int idGroupeDeContrat () {
			return this.idGroupeDeContrat;
		}
		
		public int getFluxaffaires() {
			return fluxAffaires;
		}

		public void setFluxAffaires(int fluxAffaires) {
			this.fluxAffaires = fluxAffaires;
		}
		
		public Activité getActivité() {
			return activité;
		}

		public void setActivité(Activité activité) {
			this.activité = activité;
		}

		@Override
		public void setProperty(Relations relation, String property) {
			if (relation == Relations.aDurée) {
				this.setDurée(Integer.parseInt(property));
			}
			if (relation == Relations.aFluxAffaires) {
				this.setFluxAffaires(Integer.parseInt(property));
			}
			
			if (relation == Relations.aActivité) {
				this.setActivité(Activité.valueOf(property));
			}
		}

		@Override
		public String getProperty(Relations relation) {
			return String.valueOf(this.durée);
		}
		
}
