/*
 * 
 * 
 * 
 */
package client;

import classe.ClasseEntite;
import classe.ClasseManager;
import classe.ClasseSort;
import client.XPContainer.XPCompressed;
import java.util.HashMap;
import java.util.Map;
import netserv.Compressable;
import netserv.Compressed;

/**
 * Personnage.java
 *
 */
public class Personnage implements Compressable {

	private final long idPersonnage;
	private final String nom;
	private final ClasseEntite classe;
	private final XPContainer persoXP;
	private final Map<ClasseSort, XPContainer> sortXP;

	public Personnage(long idPersonnage, String nom, long idClasse, Map<Long, Integer> sortXP) {
		this.idPersonnage = idPersonnage;
		this.nom = nom;
		this.classe = ClasseManager.getEntiteFromId(idClasse);
		this.persoXP = new XPContainer(-1, classe.getClasseXP());
		this.sortXP = new HashMap();
		sortXP.forEach((Long idClasseSort, Integer xp) -> {
			ClasseSort c = ClasseManager.getSortFromId(idClasseSort);
			this.sortXP.put(c, new XPContainer(xp, c.getClasseXP()));
		});
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

	@Override
	public Compressed getCompressed() {
		return new PersonnageCompressed(this);
	}

	public class PersonnageCompressed implements Compressed {

		private long idPersonnage;
		private String nom;
		private long classe;
		private Compressed xpContainer;
		private Map<Long, Compressed> sortXP;

		private PersonnageCompressed(Personnage p) {
			this.idPersonnage = p.getIdPersonnage();
			this.nom = p.getNom();
			this.classe = p.getClasse().getId();
			this.xpContainer = p.getPersoXP().getCompressed();
			sortXP = new HashMap();
			p.getSortXP().forEach((ClasseSort cs, XPContainer xpc)
					-> sortXP.put(cs.getId(), xpc.getCompressed()));
		}

		public long getIdPersonnage() {
			return idPersonnage;
		}

		public String getNom() {
			return nom;
		}

		public long getClasse() {
			return classe;
		}

		public Compressed getXpContainer() {
			return xpContainer;
		}

		public Map<Long, Compressed> getSortXP() {
			return sortXP;
		}

	}

}
