/*
 * 
 * 
 * 
 */
package client;

import classe.ClasseEntite;
import classe.ClasseManager;
import classe.ClasseSort;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;

/**
 * Personnage.java
 *
 */
public class Personnage {

	@JsonIgnore
	private final Client client;

	private final long idPersonnage;
	private final String nom;
	private final ClasseEntite classe;
	private final PersoXPContainer persoXP;
	private final Map<ClasseSort, XPContainer> sortXP;

	public Personnage(Client client, long idPersonnage, String nom, long idClasse, Map<Long, Integer> sXP) {
		this.client = client;
		this.idPersonnage = idPersonnage;
		this.nom = nom;
		this.classe = ClasseManager.getEntiteFromId(idClasse);
		this.sortXP = new HashMap();
		sXP.forEach((idClasseSort, xp) -> {
			ClasseSort c = ClasseManager.getSortFromId(idClasseSort);
			this.sortXP.put(c, new XPContainer(xp, c.getClasseXP()));
		});
		this.persoXP = new PersoXPContainer(this.sortXP, classe.getClasseXP());
	}

	public Client getClient() {
		return client;
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

	public PersoXPContainer getPersoXP() {
		return persoXP;
	}

	public Map<ClasseSort, XPContainer> getSortXP() {
		return sortXP;
	}

	@JsonGetter("classe")
	public long getJSONClasse() {
		return classe.getId();
	}

	@JsonGetter("sortXP")
	public Map<Long, XPContainer> getJSONSortXP() {
		Map<Long, XPContainer> ret = new HashMap();
		sortXP.forEach((c, xpc) -> ret.put(c.getId(), xpc));
		return ret;
	}
}
