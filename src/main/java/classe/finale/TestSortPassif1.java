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
import combat.CombatEnvoutement;
import java.util.Arrays;

/**
 * TestSortPassif1.java
 * Sort passif
 */
public class TestSortPassif1 extends ClasseSort {

	private static final long ID = 1;

	public TestSortPassif1() {
		super(ID, Arrays.asList((Effet) (tuile, cible, lanceur) -> {
			lanceur.getEnvoutements().add(new CombatEnvoutement(
					ClasseManager.getEnvoutementFromClass(TestEnvoutement1.class))
			);
		}), new ClasseXP(FonctionXP.SQRT, 0));
	}

}
