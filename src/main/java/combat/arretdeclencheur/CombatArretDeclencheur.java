/*
 * 
 * 
 * 
 */
package combat.arretdeclencheur;

import combat.Combat;
import combat.entite.CombatEntite;
import combat.CombatEquipe;

/**
 * CombatArretDeclencheur
 * Interface
 */
public interface CombatArretDeclencheur {

	public static final CombatArretDeclencheur CPS_ARRET_DECLENCHEUR = new CombatArretDeclencheur() {
		@Override
		public boolean isTermine(Combat combat) {
			int nbrEnVie = 0;
			for (CombatEntite e : combat.getEntites()) {
				if (e.isPersonnage() && e.isEnVie()) {
					nbrEnVie++;
					if (nbrEnVie > 1) {
						return false;
					}
				}
			}
			return true;
		}

		@Override
		public CombatEquipe getGagnant(Combat combat) {
			for (CombatEntite e : combat.getEntites()) {
				if (e.isPersonnage() && e.isEnVie()) {
					return e.getEquipe();
				}
			}
			return null;
		}
	};
	

	public static final CombatArretDeclencheur EQUIPE_ARRET_DECLENCHEUR = new CombatArretDeclencheur() {
		@Override
		public boolean isTermine(Combat combat) {
			int nbrEnVie = 0;
			for (CombatEquipe e : combat.getEquipes()) {
				if(e.streamCombatPersonnages().anyMatch((cp) -> cp.isEnVie())) {
					nbrEnVie++;
					if(nbrEnVie > 1) {
						return false;
					}
				}
			}
			return true;
		}

		@Override
		public CombatEquipe getGagnant(Combat combat) {
			for (CombatEquipe e : combat.getEquipes()) {
				if(e.streamCombatPersonnages().anyMatch((cp) -> cp.isEnVie())) {
					return e;
				}
			}
			return null;
		}
	};

	public boolean isTermine(Combat combat);

	public CombatEquipe getGagnant(Combat combat);

}
