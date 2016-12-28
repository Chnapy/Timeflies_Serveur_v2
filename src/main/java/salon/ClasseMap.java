/*
 * 
 * 
 * 
 */
package salon;

import java.util.Set;

/**
 * ClasseMap.java
 * 
 */
public class ClasseMap {
	
	private final String nom, description;
	private final int versionMajeure, versionMineure, 
			nbrEquipeMax, nbrPersosEquipeMax, difficulte;
	private final Set<ClasseTuile> tuiles;

	public ClasseMap(String nom, String description, 
			int versionMajeure, int versionMineure, 
			int nbrEquipeMax, int nbrPersosEquipeMax, 
			int difficulte, Set<ClasseTuile> tuiles) {
		this.nom = nom;
		this.description = description;
		this.versionMajeure = versionMajeure;
		this.versionMineure = versionMineure;
		this.nbrEquipeMax = nbrEquipeMax;
		this.nbrPersosEquipeMax = nbrPersosEquipeMax;
		this.difficulte = difficulte;
		this.tuiles = tuiles;
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}

	public int getVersionMajeure() {
		return versionMajeure;
	}

	public int getVersionMineure() {
		return versionMineure;
	}

	public int getNbrEquipeMax() {
		return nbrEquipeMax;
	}

	public int getNbrPersosEquipeMax() {
		return nbrPersosEquipeMax;
	}

	public int getDifficulte() {
		return difficulte;
	}

	public Set<ClasseTuile> getTuiles() {
		return tuiles;
	}

}
