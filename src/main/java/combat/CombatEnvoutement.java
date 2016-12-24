/*
 * 
 * 
 * 
 */
package combat;

import classe.ClasseEnvoutement;

/**
 * CombatEnvoutement.java
 * 
 */
public class CombatEnvoutement extends CombatClasse<ClasseEnvoutement> {
	
	private int nbrToursRestant;

	public CombatEnvoutement(ClasseEnvoutement classe) {
		super(classe);
		this.nbrToursRestant = classe.getDuree();
	}

	public int getNbrToursRestant() {
		return nbrToursRestant;
	}

	public void setNbrToursRestant(int nbrToursRestant) {
		this.nbrToursRestant = nbrToursRestant;
	}

	public void subNbrToursRestant(int sub) {
		this.nbrToursRestant -= sub;
	}

}
