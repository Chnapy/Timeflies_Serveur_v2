/*
 * 
 * 
 * 
 */
package client;

import classe.ClasseEntite;
import classe.ClasseManager;
import classe.ClasseSort;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;
import netserv.Compressable;
import netserv.Compressed;

/**
 * Personnage.java
 *
 */
public class Personnage implements Compressable {

	@JsonIgnore
	private final Client client;
	
	private final long idPersonnage;
	private final String nom;
	private final ClasseEntite classe;
	private final XPContainer persoXP;
	private final Map<ClasseSort, XPContainer> sortXP;

	public Personnage(Client client, long idPersonnage, String nom, long idClasse, Map<Long, Integer> sortXP) {
		this.client = client;
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

		private final long idPersonnage;
		private final String nom;
		private final long classe;
		private final Compressed xpContainer;
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

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 59 * hash + (int) (this.idPersonnage ^ (this.idPersonnage >>> 32));
			return hash;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final PersonnageCompressed other = (PersonnageCompressed) obj;
			if (this.idPersonnage != other.idPersonnage) {
				return false;
			}
			return true;
		}

	}

}
