/*
 * 
 * 
 * 
 */
package classe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 * ClasseEntite.java
 * 
 */
public abstract class ClasseEntite extends Classe {
	
	private final List<ClasseSort> sorts;
	private final ClasseXP classeXP;
	private final Map<TypeCPhysique, Integer> cPhysique;
	
	public ClasseEntite(long id, List<ClasseSort> sorts, ClasseXP classeXP, 
			List<Pair<TypeCPhysique, Integer>> cPhysique) {
		super(id);
		this.sorts = sorts;
		this.classeXP = classeXP;
		this.cPhysique = new HashMap();
		cPhysique.forEach((p) -> this.cPhysique.put(p.getKey(), p.getValue()));
	}

	public List<ClasseSort> getSorts() {
		return sorts;
	}

	public ClasseXP getClasseXP() {
		return classeXP;
	}

	public Map<TypeCPhysique, Integer> getcPhysique() {
		return cPhysique;
	}

}
