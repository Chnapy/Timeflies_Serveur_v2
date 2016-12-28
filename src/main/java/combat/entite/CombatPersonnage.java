/*
 * 
 * 
 * 
 */
package combat.entite;

import client.Personnage;
import classe.TypeCPhysique;
import client.Client;
import combat.CombatSortActif;
import combat.CombatEquipe;
import java.util.List;
import javafx.util.Pair;

/**
 * CombatPersonnage.java
 *
 */
public class CombatPersonnage extends CombatEntiteActive {

	private final Personnage personnage;

	public CombatPersonnage(Client client, Personnage personnage, CombatEquipe equipe,
			List<Pair<TypeCPhysique, Integer>> cPhysique,
			List<Pair<TypeCPhysique, Integer>> cPhysiqueMax,
			List<CombatSortActif> sortsActif) {
		super(client, personnage.getClasse(), personnage.getPersoXP().getNiveau(),
				equipe, cPhysique, cPhysiqueMax, sortsActif);
		this.personnage = personnage;
	}

	public Personnage getPersonnage() {
		return personnage;
	}

}
