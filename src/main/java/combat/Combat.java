/*
 * 
 * 
 * 
 */
package combat;

import client.Client;
import combat.entite.CombatEntite;
import combat.arretdeclencheur.CombatArretDeclencheur;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import map.CombatMap;
import netserv.Sendable;
import salon.Salon;
import salon.proprietes.PropMap;
import salon.proprietes.PropTypeCombat;
import salon.proprietes.TypePropriete;

/**
 * Combat.java
 *
 */
public class Combat {

	private final long id;
	private final CombatMap map;
	private final CombatArretDeclencheur arretDeclencheur;
	private final Set<Client> clients;
	private final CombatPileAction pileAction;
	private final Set<CombatEquipe> equipes;
	private final Set<CombatEntite> entites;
	
	private StatutCombat statut;

	public Combat(Salon s) {
		this.id = s.getId();
		this.map = new CombatMap(s.<PropMap>getProp(TypePropriete.MAP).getValeur());
		this.arretDeclencheur = s.<PropTypeCombat>getProp(TypePropriete.TYPECOMBAT).getValeur().getArretDeclencheur();
		this.clients = Collections.unmodifiableSet(s.getClients().keySet());
		this.entites = new HashSet();
		this.equipes = Collections.unmodifiableSet(s.getEquipes().values().stream().map((e) -> {
			CombatEquipe ce = new CombatEquipe(e.getId());
			e.forEach(p -> {
				CombatEntite cent = new CombatEntite(p, ce);
				this.entites.add(cent);
				ce.add(cent);
			});
			return ce;
		}).collect(Collectors.toSet()));
		this.pileAction = new CombatPileAction();
		this.statut = StatutCombat.EN_ATTENTE;
	}
	
	public void sendToAll(String event, Sendable send) {
		clients.forEach(c -> c.getSocketClient().sendEvent(event, send));
	}
	
	public long getId() {
		return id;
	}

	public Set<CombatEntite> getEntites() {
		return entites;
	}

	public Set<CombatEquipe> getEquipes() {
		return equipes;
	}

	public StatutCombat getStatut() {
		return statut;
	}

	public void setStatut(StatutCombat statut) {
		this.statut = statut;
	}

}
