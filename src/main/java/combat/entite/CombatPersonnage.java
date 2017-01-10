/*
 * 
 * 
 * 
 */
package combat.entite;

import client.Personnage;
import combat.CombatEquipe;

/**
 * CombatPersonnage.java
 *
 */
public class CombatPersonnage extends CombatEntiteActive {

	private final Personnage personnage;

	public CombatPersonnage(Personnage perso, CombatEquipe equipe) {
		super(
				perso.getClient(),
				perso.getClasse(),
				perso.getPersoXP().getNiveau(),
				equipe,
				perso.getSortXP()
		);
		this.personnage = perso;
	}

	public Personnage getPersonnage() {
		return personnage;
	}

}
