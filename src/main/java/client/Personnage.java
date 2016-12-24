/*
 * 
 * 
 * 
 */
package client;

import classe.ClasseEntite;
import classe.ClasseSort;
import client.XPContainer;
import java.util.HashMap;
import java.util.Map;

/**
 * Personnage.java
 *
 */
public class Personnage {

	private final long idPersonnage;
	private final String nom;
	private final ClasseEntite classe;
	private final XPContainer persoXP;
	private final Map<ClasseSort, XPContainer> sortXP;

	public Personnage(long idPersonnage, String nom, ClasseEntite classe, int persoXP, Map<ClasseSort, Integer> sortXP) {
		this.idPersonnage = idPersonnage;
		this.nom = nom;
		this.classe = classe;
		this.persoXP = new XPContainer(persoXP, classe.getClasseXP());
		this.sortXP = new HashMap();
		sortXP.forEach((ClasseSort c, Integer xp)
				-> this.sortXP.put(c, new XPContainer(xp, c.getClasseXP()))
		);
	}

	public long getIdPersonnage() {
		return idPersonnage;
	}

	public String getNom() {
		return nom;
	}

	public ClasseEntite getClasse() {
		return classe;
	}

	public XPContainer getPersoXP() {
		return persoXP;
	}

	public Map<ClasseSort, XPContainer> getSortXP() {
		return sortXP;
	}

}
