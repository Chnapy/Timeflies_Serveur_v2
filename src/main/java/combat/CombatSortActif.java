/*
 * 
 * 
 * 
 */
package combat;

import classe.ClasseSortActif;

/**
 * CombatSortActif.java
 * 
 */
public class CombatSortActif extends CombatClasse<ClasseSortActif> {
	
	private final int niveau;

	public CombatSortActif(ClasseSortActif classe, int niveau) {
		super(classe);
		this.niveau = niveau;
	}

	public int getNiveau() {
		return niveau;
	}

}
