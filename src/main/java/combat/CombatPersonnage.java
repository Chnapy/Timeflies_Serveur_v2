/*
 * 
 * 
 * 
 */
package combat;

import classe.ClasseEntite;
import client.Personnage;
import classe.TypeCPhysique;
import java.util.List;
import javafx.util.Pair;

/**
 * CombatPersonnage.java
 *
 */
public class CombatPersonnage extends CombatEntiteActive {

	private final Personnage personnage;

	public CombatPersonnage(Personnage personnage, Equipe equipe,
			List<Pair<TypeCPhysique, Integer>> cPhysique,
			List<Pair<TypeCPhysique, Integer>> cPhysiqueMax,
			List<CombatSortActif> sortsActif) {
		super(personnage.getClasse(), personnage.getPersoXP().getNiveau(),
				equipe, cPhysique, cPhysiqueMax, sortsActif);
		this.personnage = personnage;
	}

	public Personnage getPersonnage() {
		return personnage;
	}

}
