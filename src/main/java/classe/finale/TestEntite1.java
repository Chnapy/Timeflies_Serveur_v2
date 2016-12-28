/*
 * 
 * 
 * 
 */
package classe.finale;

import classe.ClasseEntite;
import classe.ClasseManager;
import classe.ClasseXP;
import classe.FonctionXP;
import classe.TypeCPhysique;
import java.util.Arrays;
import java.util.HashSet;
import javafx.util.Pair;

/**
 * TestEntite1.java
 * 
 */
public class TestEntite1 extends ClasseEntite {
	
	public static final long ID = 1;

	public TestEntite1() {
		super(ID, new HashSet(Arrays.asList(
				ClasseManager.getSortFromClass(TestSortPassif1.class)
		)), new ClasseXP(FonctionXP.SQRT, 0), Arrays.asList(
				new Pair(TypeCPhysique.VITALITE, 100),
				new Pair(TypeCPhysique.TEMPSACTION, 40000),
				new Pair(TypeCPhysique.TEMPSSUP, 5000),
				new Pair(TypeCPhysique.VITESSE, 100),
				new Pair(TypeCPhysique.FATIGUE, 5)
		));
	}

}
