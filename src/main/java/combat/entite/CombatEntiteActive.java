/*
 * 
 * 
 * 
 */
package combat.entite;

import classe.ClasseEntite;
import classe.ClasseSort;
import client.Client;
import client.XPContainer;
import combat.CombatSortActif;
import combat.CombatEquipe;
import java.util.HashMap;
import java.util.Map;

/**
 * CombatEntiteActive.java
 *
 */
public class CombatEntiteActive extends CombatEntite {

	private final Map<Long, CombatSortActif> sortsActif;

	public CombatEntiteActive(Client client, ClasseEntite classe, int niveau, CombatEquipe equipe,
			Map<ClasseSort, XPContainer> sorts) {
		super(client, classe, niveau, equipe);

		this.sortsActif = new HashMap();
		sorts.forEach((cs, xp) -> {
			if (cs.isActif()) {
				this.sortsActif.put(cs.getId(), new CombatSortActif(cs, xp));
			}
		});
	}

	public Map<Long, CombatSortActif> getSortsActif() {
		return sortsActif;
	}

}
