/*
 * 
 * 
 * 
 */
package salon;

import classe.ClasseEntite;
import client.Client;
import client.Personnage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import main.Const;
import netserv.Compressable;
import netserv.Compressed;
import salon.net.NetSalon;
import salon.net.events.NetSalonEventSetProprietes;
import salon.typecombatproprietes.SalonProprietes;

/**
 * Salon.java
 *
 */
public class Salon implements Compressable {

	private static int newID = 0;

	private final int id;
	private ClasseMap map;
	private TypeVisibilite visibilite;
	private TypeCombat typeCombat;
	private SalonProprietes typeCombatProprietes;
	private int nbrPersoClasse;
	private final Map<Client, Boolean> clients;
	private Client dirigeant;
	private final Map<Integer, SalonEquipe> equipes;

	public Salon(Client client) {
		this.id = getNewId();
		this.map = null;
		this.visibilite = TypeVisibilite.PUBLIQUE;
		this.typeCombat = TypeCombat.EQUIPE;
		this.typeCombatProprietes = this.typeCombat.getNewSalonProprietes();
		this.nbrPersoClasse = -1;
		this.clients = new HashMap();
		this.clients.put(client, false);
		this.dirigeant = client;
		this.equipes = new HashMap();
	}

	public int getId() {
		return id;
	}

	public void sendToAll(String event, Object... obj) {
		this.clients.keySet().forEach(c -> c.getSocketClient().sendEvent(event, obj));
	}

	public ClasseMap getMap() {
		return map;
	}

	public TypeVisibilite getVisibilite() {
		return visibilite;
	}

	public TypeCombat getTypeCombat() {
		return typeCombat;
	}

	public SalonProprietes getTypeCombatProprietes() {
		return typeCombatProprietes;
	}

	public int getNbrPersoClasse() {
		return nbrPersoClasse;
	}

	public Map<Client, Boolean> getClients() {
		return clients;
	}

	public Map<Integer, SalonEquipe> getEquipes() {
		return equipes;
	}

	public void removeClient(Client c) {
		this.equipes.values().forEach(e -> e.removeClient(c));
		this.clients.remove(c);
	}

	public void addClient(Client c) {
		if (this.clients.size() < Const.NBR_CLIENT_MAX) {
			this.clients.put(c, false);
		}
	}

	public void setMap(ClasseMap map) {
		this.map = map;
	}

	public void setVisibilite(TypeVisibilite visibilite) {
		this.visibilite = visibilite;
	}

	public void setTypeCombat(TypeCombat typeCombat) {
		this.typeCombat = typeCombat;
		this.setTypeCombatProprietes(typeCombat.getNewSalonProprietes());
	}

	private void setTypeCombatProprietes(SalonProprietes typeCombatProprietes) {
		this.typeCombatProprietes = typeCombatProprietes;
	}

	public void setNbrPersoClasse(int nbrPersoClasse) {
		if (nbrPersoClasse > 0) {
			this.nbrPersoClasse = nbrPersoClasse;
		}
	}

	public Client getDirigeant() {
		return dirigeant;
	}

	public void setDirigeant(Client dirigeant) {
		this.dirigeant = dirigeant;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 71 * hash + this.id;
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
		final Salon other = (Salon) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public Compressed getCompressed() {
		return new SalonCompressed(this);
	}

	private static int getNewId() {
		newID++;
		return newID;
	}

	public boolean ajouterPerso(Client c, long idperso, int idequipe) {
		if (this.clients.get(c) != false) {
			return false;
		}

		Personnage p = c.getPersonnages().get(idperso);
		if (p == null) {
			return false;
		}

		SalonEquipe se = this.equipes.get(idequipe);
		if (se == null || containsPerso(p)) {
			return false;
		}

		se.add(p);

		if (!checkProprietes()) {
			se.remove(p);
			return false;
		}
		return true;
	}

	public boolean retraitPerso(Client c, long idperso) {
		if (this.clients.get(c) != false) {
			return false;
		}

		Personnage p = c.getPersonnages().get(idperso);
		if (p == null) {
			return false;
		}

		this.equipes.values().forEach((se) -> se.remove(p));
		return true;
	}

	public boolean containsPerso(Personnage p) {
		return this.equipes.values().stream().anyMatch((se) -> (se.contains(p)));
	}

	public boolean checkProprietes() {
		HashMap<ClasseEntite, Integer> nbrClasses = new HashMap();
		this.equipes.values().forEach((se)
				-> se.forEach(p -> nbrClasses.merge(p.getClasse(), 1, (t, u) -> t + 1)));
		return nbrClasses.keySet().stream().noneMatch((ce)
				-> (nbrClasses.get(ce) > this.nbrPersoClasse))
				&& this.typeCombatProprietes.checkProprietes(this);
	}

	public void setProprietes(NetSalonEventSetProprietes.RecSetProprietes data, NetSalon ns) {

		if (data.getIdmap() != null) {
			//TODO
		}

		if (data.getVisibilite() != null) {
			try {
				setVisibilite(TypeVisibilite.getFromId(data.getVisibilite()));
			} catch (IllegalArgumentException ex) {
				Logger.getGlobal().warning(ex.toString());
				data.setVisibilite(null);
			}
		}

		if (data.getTypecombat() != null) {
			try {
				setTypeCombat(TypeCombat.getFromId(data.getTypecombat()));
			} catch (IllegalArgumentException ex) {
				Logger.getGlobal().warning(ex.toString());
				data.setTypecombat(null);
			}
		}

		if (data.getNbr_perso_classe() != null) {
			setNbrPersoClasse(data.getNbr_perso_classe());
			data.setNbr_perso_classe(getNbrPersoClasse());
		}

		if (data.getIddirigeant() != null) {
			try {
				setDirigeant(
						ns.getClientFromId(data.getIddirigeant())
				);
			} catch (IllegalArgumentException ex) {
				Logger.getGlobal().warning(ex.toString());
				data.setIddirigeant(null);
			}
		}

		this.typeCombatProprietes.setProprietes(data);
	}

	public static class SalonCompressed implements Compressed {

		private final int id;
		private final Compressed map;
		private final int visibilite;
		private final int typeCombat;
		private final SalonProprietes typeCombatProprietes;
		private final int nbrPersoClasse;
		private final Set<Compressed> clients;
		private final long dirigeant;
		private final Set<Compressed> equipes;

		public SalonCompressed(Salon s) {
			id = s.getId();
			map = s.map == null ? null : s.map.getCompressed();
			visibilite = s.getVisibilite().getId();
			typeCombat = s.getTypeCombat().getId();
			typeCombatProprietes = s.getTypeCombatProprietes();
			nbrPersoClasse = s.getNbrPersoClasse();
			clients = new HashSet();
			s.getClients().forEach((c, b) -> clients.add(c.getCompressed()));
			dirigeant = s.getDirigeant().getId();
			equipes = s.getEquipes().values().stream().map(e -> e.getCompressed()).collect(Collectors.toSet());
		}

		public int getId() {
			return id;
		}

		public Compressed getMap() {
			return map;
		}

		public int getVisibilite() {
			return visibilite;
		}

		public int getTypeCombat() {
			return typeCombat;
		}

		public SalonProprietes getTypeCombatProprietes() {
			return typeCombatProprietes;
		}

		public int getNbrPersoClasse() {
			return nbrPersoClasse;
		}

		public Set<Compressed> getClients() {
			return clients;
		}

		public long getDirigeant() {
			return dirigeant;
		}

		public Set<Compressed> getEquipes() {
			return equipes;
		}

	}

}
