/*
 * 
 * 
 * 
 */
package classe.finale;

import classe.ClasseEntite;
import classe.ClasseSort;
import classe.ClasseXP;
import classe.TypeCPhysique;
import java.util.Map;
import java.util.Set;

/**
 * TestEntite1.java
 * 
 */
public class TestEntite1 extends ClasseEntite {

	public TestEntite1(long id, boolean actif, Set<ClasseSort> sorts, ClasseXP classeXP,
			Map<TypeCPhysique, Integer> cPhysique) {
		super(id, actif, sorts, classeXP, cPhysique);
	}

}
