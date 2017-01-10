/*
 * 
 * 
 * 
 */
package salon;

import client.Client;
import client.Personnage;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import main.Const;
import netserv.Sendable;
import salon.net.NetSalon;
import salon.net.events.NetSalonEventSetProprietes;
import salon.proprietes.Propriete;
import salon.proprietes.PropMap;
import salon.proprietes.PropNbrPersoClasse;
import salon.proprietes.PropTypeCombat;
import salon.proprietes.PropVisibilite;
import salon.proprietes.Proprietable;
import salon.proprietes.TypePropriete;

/**
 * Salon.java
 *
 */
public class Salon extends Proprietable {

//	private static int newID = 0;

	private final long id;

	@JsonIgnore
	private final ModeleSalon modele;

	private final Map<Client, Boolean> clients;
	private Client dirigeant;
	private final Map<Integer, SalonEquipe> equipes;

//	@JsonIgnore
	private boolean lock;

	public Salon(long id, Client client) {
		this.id = id;
		this.modele = new ModeleSalon();
		this.clients = new HashMap();
		this.clients.put(client, false);
		this.dirigeant = client;
		this.equipes = new HashMap();
		this.lock = false;

		this.initProprietes();
	}

	@Override
	public List<Propriete> getAllProp() {
		return Arrays.asList(
				new PropMap(this),
				new PropVisibilite(this),
				new PropTypeCombat(this),
				new PropNbrPersoClasse(this)
		);
	}

	@Override
	public <P extends Propriete> P getProp(TypePropriete tp) {
		try {
			return super.getProp(tp);
		} catch (IllegalArgumentException ex) {
			return this.<PropTypeCombat>getProp(TypePropriete.TYPECOMBAT).getTypeCombatProprietes().<P>getProp(tp);
		}
	}

	public long getId() {
		return id;
	}

	public void sendToAll(String event, Sendable obj) {
		this.clients.keySet().forEach(c -> c.getSocketClient().sendEvent(event, obj));
	}

	public Map<Client, Boolean> getClients() {
		return clients;
	}

	public Map<Integer, SalonEquipe> getEquipes() {
		return equipes;
	}

	public boolean removeClient(Client c) {
		this.equipes.values().forEach(e -> e.removeClient(c));
		this.clients.remove(c);

		if (this.clients.isEmpty()) {
			return true;
		} else if (this.dirigeant.equals(c)) {
			this.setDirigeant(this.clients.keySet().iterator().next());
		}
		return false;
	}

	public void addClient(Client c) {
		if (this.clients.size() < Const.NBR_CLIENT_MAX) {
			this.clients.put(c, false);
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
		int hash = 7;
		hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
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
	public Map<TypePropriete, Propriete> getProprietes() {
		Map<TypePropriete, Propriete> ret = new HashMap();
		ret.putAll(super.getProprietes());
		ret.putAll(this.<PropTypeCombat>getProp(TypePropriete.TYPECOMBAT).getTypeCombatProprietes().getProprietes());
		return ret;
	}

	public boolean setPret(Client c, boolean pret) {
		boolean ret = this.clients.get(c) != pret
				&& getClientEquipe(c).isPresent();

		if (ret) {
			this.clients.merge(c, pret, (cl, val) -> pret);
			defLock();
		}

		return ret;
	}

	private void defLock() {
		this.lock = this.clients.values().stream().allMatch(b -> b);
	}

	public boolean isLock() {
		return lock;
	}

	public Optional<SalonEquipe> getClientEquipe(Client c) {
		return this.equipes.values().stream().filter(se -> !se.getPersosFromClient(c).isEmpty()).findAny();
	}

	public Optional<Personnage> ajouterPerso(Client c, long idperso, int idequipe) {
		if (this.clients.get(c) != false) {
			return Optional.empty();
		}

		Personnage p = c.getPersonnages().get(idperso);
		if (p == null) {
			return Optional.empty();
		}

		SalonEquipe se = this.equipes.get(idequipe);
		if (se == null || se.contains(p)) {
			return Optional.empty();
		}

		Optional<SalonEquipe> op = getEquipeOfPerso(p);
		op.ifPresent(sef -> sef.removeClient(c));

		boolean success = this.<PropTypeCombat>getProp(TypePropriete.NBR_PERSOS_CLASSE).getTypeCombatProprietes().ajouterPerso(p, se);

		if (success) {
			return Optional.of(p);
		} else {
			return Optional.empty();
		}
//		se.add(p);
//
//		if (!checkProprietes()) {
//			se.remove(p);
//			return false;
//		}
//		return true;
	}

	public Optional<Personnage> retraitPerso(Client c, long idperso) {
		if (this.clients.get(c) != false) {
			return Optional.empty();
		}

		Personnage p = c.getPersonnages().get(idperso);
		if (p == null) {
			return Optional.empty();
		}

		this.equipes.values().forEach((se) -> se.remove(p));
		return Optional.of(p);
	}

	public Optional<SalonEquipe> getEquipeOfPerso(Personnage p) {
		return this.equipes.values().stream().filter(se -> se.contains(p)).findAny();
	}

	public boolean containsPerso(Personnage p) {
		return this.equipes.values().stream().anyMatch(se -> se.contains(p));
	}

	public void setProprietes(NetSalonEventSetProprietes.RecSetProprietes data, NetSalon ns) {

		if (data.getIdmap() != null) {
			try {
				this.setProp(TypePropriete.MAP, data.getIdmap());
			} catch (IllegalArgumentException ex) {
				data.setIdmap(null);
			}
		}

		if (data.getVisibilite() != null) {
			try {
				this.setProp(TypePropriete.VISIBILITE, data.getVisibilite());
			} catch (IllegalArgumentException ex) {
				data.setVisibilite(null);
			}
		}

		if (data.getTypecombat() != null) {
			try {
				this.setProp(TypePropriete.TYPECOMBAT, data.getTypecombat());
			} catch (IllegalArgumentException ex) {
				data.setTypecombat(null);
			}
		}

		if (data.getNbr_perso_classe() != null) {
			try {
				this.setProp(TypePropriete.NBR_PERSOS_CLASSE, data.getNbr_perso_classe());
			} catch (IllegalArgumentException ex) {
				data.setNbr_perso_classe(this.<PropNbrPersoClasse>getProp(TypePropriete.NBR_PERSOS_CLASSE).getValeur());
			}
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

		this.<PropTypeCombat>getProp(TypePropriete.TYPECOMBAT).getTypeCombatProprietes().setProprietes(data);
	}

	public ModeleSalon getModele() {
		return modele;
	}

	@JsonGetter("clients")
	public Set<Client> getJSONClients() {
		return this.clients.keySet();
	}

	@JsonGetter("equipes")
	public Collection<SalonEquipe> getJSONEquipes() {
		return this.equipes.values();
	}

}
