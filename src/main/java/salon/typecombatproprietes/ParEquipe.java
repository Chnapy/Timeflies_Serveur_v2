/*
 * 
 * 
 * 
 */
package salon.typecombatproprietes;

/**
 * ParEquipe.java
 *
 */
public class ParEquipe extends SalonProprietes {
	
	private int nbrEquipe, nbrPersosEquipe, nbrPersosClasse, nbrPersosJoueur;

	public ParEquipe() {
		this.nbrEquipe = 0;
		this.nbrPersosEquipe = 0;
		this.nbrPersosJoueur = 0;
	}

	public int getNbrEquipe() {
		return nbrEquipe;
	}

	public void setNbrEquipe(int nbrEquipe) {
		this.nbrEquipe = nbrEquipe;
	}

	public int getNbrPersosEquipe() {
		return nbrPersosEquipe;
	}

	public void setNbrPersosEquipe(int nbrPersosEquipe) {
		this.nbrPersosEquipe = nbrPersosEquipe;
	}

	public int getNbrPersosJoueur() {
		return nbrPersosJoueur;
	}

	public void setNbrPersosJoueur(int nbrPersosJoueur) {
		this.nbrPersosJoueur = nbrPersosJoueur;
	}
}
