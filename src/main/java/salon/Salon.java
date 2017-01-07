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

	private static int newID = 0;

	private final int id;
	
	@JsonIgnore
	private final ModeleSalon modele;
	
	private final Map<Client, Boolean> clients;
	private Client dirigeant;
	private final Map<Integer, SalonEquipe> equipes;

	public Salon(Client client) {
		this.id = getNewId();
		this.modele = new ModeleSalon();
		this.clients = new HashMap();
		this.clients.put(client, false);
		this.dirigeant = client;
		this.equipes = new HashMap();

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

	public int getId() {
		return id;
	}

	public void sendToAll(String event, Object... obj) {
		this.clients.keySet().forEach(c -> c.getSocketClient().sendEvent(event, obj));
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
	public Map<TypePropriete, Propriete> getProprietes() {
		Map<TypePropriete, Propriete> ret = new HashMap();
		ret.putAll(super.getProprietes());
		ret.putAll(this.<PropTypeCombat>getProp(TypePropriete.TYPECOMBAT).getTypeCombatProprietes().getProprietes());
		return ret;
	}

	private static int getNewId() {
		newID++;
		return newID;
	}

	public boolean setPret(Client c, boolean pret) {
		boolean ret = this.clients.get(c) != pret
				&& getClientEquipe(c).isPresent();

		if (ret) {
			this.clients.merge(c, pret, (cl, val) -> pret);
		}

		return ret;
	}

	public Optional<SalonEquipe> getClientEquipe(Client c) {
		return this.equipes.values().stream().filter(se -> !se.getPersosFromClient(c).isEmpty()).findAny();
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
		if (se == null || se.contains(p)) {
			return false;
		}

		Optional<SalonEquipe> op = getEquipeOfPerso(p);
		op.ifPresent(sef -> sef.removeClient(c));

		return this.<PropTypeCombat>getProp(TypePropriete.NBR_PERSOS_CLASSE).getTypeCombatProprietes().ajouterPerso(p, se);
//		se.add(p);
//
//		if (!checkProprietes()) {
//			se.remove(p);
//			return false;
//		}
//		return true;
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
