/*
 * 
 * 
 * 
 */
package salon;

import client.Client;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import netserv.Compressable;
import netserv.Compressed;
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
	private final Set<SalonEquipe> equipes;

	public Salon(Client client) {
		this.id = getNewId();
		this.map = null;
		this.visibilite = TypeVisibilite.PUBLIQUE;
		this.typeCombat = TypeCombat.EQUIPE;
		this.typeCombatProprietes = this.typeCombat.getNewSalonProprietes();
		this.nbrPersoClasse = 0;
		this.clients = new HashMap();
		this.clients.put(client, false);
		this.dirigeant = client;
		this.equipes = new HashSet();
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

	public Set<SalonEquipe> getEquipes() {
		return equipes;
	}

	public void setMap(ClasseMap map) {
		this.map = map;
	}

	public void setVisibilite(TypeVisibilite visibilite) {
		this.visibilite = visibilite;
	}

	public void setTypeCombat(TypeCombat typeCombat) {
		this.typeCombat = typeCombat;
	}

	public void setTypeCombatProprietes(SalonProprietes typeCombatProprietes) {
		this.typeCombatProprietes = typeCombatProprietes;
	}

	public void setNbrPersoClasse(int nbrPersoClasse) {
		this.nbrPersoClasse = nbrPersoClasse;
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
			equipes = s.getEquipes().stream().map(e -> e.getCompressed()).collect(Collectors.toSet());
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
