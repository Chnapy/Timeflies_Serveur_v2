/*
 * 
 * 
 * 
 */
package classe;

import java.util.List;

/**
 * ClasseSort.java
 * 
 */
public abstract class ClasseSort extends Classe {
	
	private final List<Effet> effets;
	private final ClasseXP classeXP;

	public ClasseSort(long id, List<Effet> effets, ClasseXP classeXP) {
		super(id);
		this.effets = effets;
		this.classeXP = classeXP;
	}

	public List<Effet> getEffets() {
		return effets;
	}

	public ClasseXP getClasseXP() {
		return classeXP;
	}
	
	public boolean isActif() {
		return this instanceof ClasseSortActif;
	}

}
