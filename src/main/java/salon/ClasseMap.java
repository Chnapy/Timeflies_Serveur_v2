/*
 * 
 * 
 * 
 */
package salon;

import java.util.Set;
import java.util.stream.Collectors;
import netserv.Compressable;
import netserv.Compressed;

/**
 * ClasseMap.java
 *
 */
public class ClasseMap implements Compressable {

	private String nom, description;
	private long idcreateur;
	private int versionMajeure, versionMineure,
			nbrequipemax, nbrpersosequipemax, difficulte;
	private Set<TypeCombat> typecombat;
	private Set<ClasseTuile> tuiles;

	public ClasseMap() {
	}

	public ClasseMap(String nom, String description,
			int versionMajeure, int versionMineure,
			int nbrEquipeMax, int nbrPersosEquipeMax,
			int difficulte, Set<ClasseTuile> tuiles) {
		this.nom = nom;
		this.description = description;
		this.versionMajeure = versionMajeure;
		this.versionMineure = versionMineure;
		this.nbrequipemax = nbrEquipeMax;
		this.nbrpersosequipemax = nbrPersosEquipeMax;
		this.difficulte = difficulte;
		this.tuiles = tuiles;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getIdcreateur() {
		return idcreateur;
	}

	public void setIdcreateur(long idcreateur) {
		this.idcreateur = idcreateur;
	}

	public void setVersionMajeure(int versionMajeure) {
		this.versionMajeure = versionMajeure;
	}

	public void setVersionMineure(int versionMineure) {
		this.versionMineure = versionMineure;
	}

	public void setNbrequipemax(int nbrEquipeMax) {
		this.nbrequipemax = nbrEquipeMax;
	}

	public void setNbrpersosequipemax(int nbrPersosEquipeMax) {
		this.nbrpersosequipemax = nbrPersosEquipeMax;
	}

	public void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

	public void setTuiles(Set<ClasseTuile> tuiles) {
		this.tuiles = tuiles;
	}

	public Set<TypeCombat> getTypecombat() {
		return typecombat;
	}

	public void setTypecombat(Set<Integer> typecombat) {
		this.typecombat = typecombat.stream().map(i -> TypeCombat.getFromId(i)).collect(Collectors.toSet());
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

	public int getNbrequipemax() {
		return nbrequipemax;
	}

	public int getNbrpersosequipemax() {
		return nbrpersosequipemax;
	}

	public int getDifficulte() {
		return difficulte;
	}

	public Set<ClasseTuile> getTuiles() {
		return tuiles;
	}

	@Override
	public String toString() {
		return "ClasseMap{" + "nom=" + nom + ", description=" + description + ", versionMajeure=" + versionMajeure + ", versionMineure=" + versionMineure + ", nbrEquipeMax=" + nbrequipemax + ", nbrPersosEquipeMax=" + nbrpersosequipemax + ", difficulte=" + difficulte + ", tuiles=" + tuiles + '}';
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
			nbrEquipeMax = map.getNbrequipemax();
			nbrPersosEquipeMax = map.getNbrpersosequipemax();
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

		public int getNbrpersosequipemax() {
			return nbrPersosEquipeMax;
		}

		public int getDifficulte() {
			return difficulte;
		}
	}

}
