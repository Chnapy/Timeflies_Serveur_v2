/*
 * 
 * 
 * 
 */
package salon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ClasseMap.java
 *
 */
public class ClasseMap  {
	
	@JsonIgnore
	private String chemin;
	
	private int idmap;

	private String nom, description;
	private long idcreateur;
	private int versionMajeure, versionMineure,
			nbrequipemax, nbrpersosequipemax, difficulte;
	private Set<TypeCombat> typecombat;
	
//	@JsonIgnore
	private Set<ClasseTuile> tuiles;

	public ClasseMap() {
	}

	public int getIdmap() {
		return idmap;
	}

	public void setIdmap(int idmap) {
		this.idmap = idmap;
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

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
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

}
