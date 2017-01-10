/*
 * 
 * 
 * 
 */
package combat;

import classe.ClasseSort;
import client.XPContainer;

/**
 * CombatSortActif.java
 * 
 */
public class CombatSortActif extends CombatClasse<ClasseSort> {
	
	private final XPContainer sortXP;

	public CombatSortActif(ClasseSort classe, XPContainer niveau) {
		super(classe);
		this.sortXP = niveau;
	}

	public XPContainer getSortXP() {
		return sortXP;
	}

}
