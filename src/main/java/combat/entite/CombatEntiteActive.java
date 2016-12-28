/*
 * 
 * 
 * 
 */
package combat.entite;

import classe.ClasseEntite;
import classe.TypeCPhysique;
import client.Client;
import combat.CombatSortActif;
import combat.CombatEquipe;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 * CombatEntiteActive.java
 *
 */
public class CombatEntiteActive extends CombatEntite {

	private final Map<Long, CombatSortActif> sortsActif;

	public CombatEntiteActive(Client client, ClasseEntite classe, int niveau, CombatEquipe equipe,
			List<Pair<TypeCPhysique, Integer>> cPhysique,
			List<Pair<TypeCPhysique, Integer>> cPhysiqueMax,
			List<CombatSortActif> sortsActif
	) {
		super(client, classe, niveau, equipe, cPhysique, cPhysiqueMax);
		this.sortsActif = new HashMap();
		sortsActif.forEach((s) -> this.sortsActif.put(s.getClasse().getId(), s));
	}

	public Map<Long, CombatSortActif> getSortsActif() {
		return sortsActif;
	}

}
