/*
 * 
 * 
 * 
 */
package classe.finale;

import classe.ClasseEnvoutement;
import classe.Declencheur;
import classe.Effet;
import classe.TypeCPhysique;
import java.util.Arrays;

/**
 * TestEnvoutement1.java
 * 
 */
public class TestEnvoutement1 extends ClasseEnvoutement {
	
//	public static final long ID = 1;

	public TestEnvoutement1(long id, int nbrTours) {
		super(id, Arrays.asList((Declencheur) (Effet effet) -> {
			return effet == Effet.DEBUT_COMBAT;
		}), Arrays.asList((Effet) (tuile, cible, lanceur) -> {
			lanceur.getcPhysique().merge(TypeCPhysique.VITESSE, 10, (t, u) -> 
				t + u
			);
		}), nbrTours);
	}

}
