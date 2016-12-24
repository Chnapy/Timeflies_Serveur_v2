/*
 * 
 * 
 * 
 */
package combat;

import classe.Classe;

/**
 * CombatClasse.java
 * 
 * @param <C>
 */
public abstract class CombatClasse<C extends Classe> {
	
	private final C classe;

	public CombatClasse(C classe) {
		this.classe = classe;
	}

	public C getClasse() {
		return classe;
	}

}
