/*
 * 
 * 
 * 
 */
package salon;

import java.util.Set;
import netserv.Compressable;
import netserv.Compressed;

/**
 * ClasseMap.java
 *
 */
public class ClasseMap implements Compressable {

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

	@Override
	public Compressed getCompressed() {
		return new ClasseMapCompressed(this);
	}

	public class ClasseMapCompressed implements Compressed {

		private final String nom, description;
		private final int versionMajeure, versionMineure,
				nbrEquipeMax, nbrPersosEquipeMax, difficulte;

		public ClasseMapCompressed(ClasseMap map) {
			nom = map.getNom();
			description = map.getDescription();
			versionMajeure = map.getVersionMajeure();
			versionMineure = map.getVersionMineure();
			nbrEquipeMax = map.getNbrEquipeMax();
			nbrPersosEquipeMax = map.getNbrPersosEquipeMax();
			difficulte = map.getDifficulte();
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
	}

}
