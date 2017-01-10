/*
 * 
 * 
 * 
 */
package classe.finale;

import classe.ClasseEnvoutement;
import classe.ClasseSort;
import classe.ClasseXP;
import classe.Effet;
import classe.TypeCPhysique;
import classe.zone.Zone;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * TestSortActif1.java
 *
 */
public class TestSortActif1 extends ClasseSort {

	public TestSortActif1(long id, boolean actif, Map<TypeCPhysique, Integer> cphysique, ClasseXP classeXP,
			int xpParUse, int pourcDegressifXP, Zone zonePortee, Zone zoneAction, Set<ClasseEnvoutement> envoutements) {
		super(id, actif, Arrays.asList((Effet) (tuile, cible, lanceur) -> {

		}), cphysique, classeXP, xpParUse, pourcDegressifXP, zonePortee, zoneAction, envoutements);
	}

}
