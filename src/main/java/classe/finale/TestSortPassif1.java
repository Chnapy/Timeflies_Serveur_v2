/*
 * 
 * 
 * 
 */
package classe.finale;

import classe.ClasseManager;
import classe.ClasseSort;
import classe.ClasseXP;
import classe.Effet;
import classe.FonctionXP;
import classe.TypeCPhysique;
import combat.CombatEnvoutement;
import java.util.Arrays;
import java.util.Map;

/**
 * TestSortPassif1.java
 * Sort passif
 */
public class TestSortPassif1 extends ClasseSort {

	public TestSortPassif1(long id, Map<TypeCPhysique, Integer> cphysique, ClasseXP classeXP, int xpParUse, int pourcDegressifXP) {
		super(id, Arrays.asList((Effet) (tuile, cible, lanceur) -> {
			lanceur.getEnvoutements().add(new CombatEnvoutement(
					ClasseManager.getEnvoutementFromClass(TestEnvoutement1.class))
			);
		}), cphysique, classeXP, xpParUse, pourcDegressifXP);
	}

}
