/*
 * 
 * 
 * 
 */
package classe.finale;

import classe.ClasseSortActif;
import classe.ClasseXP;
import classe.Effet;
import classe.TypeCPhysique;
import java.util.Arrays;
import java.util.Map;

/**
 * TestSortActif1.java
 *
 */
public class TestSortActif1 extends ClasseSortActif {

	public TestSortActif1(long id, Map<TypeCPhysique, Integer> cphysique, ClasseXP classeXP, int xpParUse, int pourcDegressifXP) {
		super(id, Arrays.asList((Effet) (tuile, cible, lanceur) -> {

		}), cphysique, classeXP, xpParUse, pourcDegressifXP,
				null, null);
	}

}
