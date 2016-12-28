/*
 * 
 * 
 * 
 */
package combat;

import combat.entite.CombatEntite;
import combat.arretdeclencheur.CombatArretDeclencheur;
import client.Client;
import java.util.HashSet;
import java.util.Set;
import map.CombatMap;

/**
 * Combat.java
 *
 */
public class Combat {

	private final CombatMap map;
	private final CombatArretDeclencheur arretDeclencheur;
	private final Set<Client> clients;
	private final CombatPileAction pileAction;
	private final Set<CombatEquipe> equipes;
	private final Set<CombatEntite> entites;

	public Combat(CombatMap map, CombatArretDeclencheur arretDeclencheur,
			Set<CombatEquipe> equipes) {
		this.map = map;
		this.arretDeclencheur = arretDeclencheur;
		this.equipes = equipes;
		this.pileAction = new CombatPileAction();
		this.entites = new HashSet();
		equipes.forEach((e) -> this.entites.addAll(e));
		this.clients = new HashSet();
		this.entites.forEach((ce) -> this.clients.add(ce.getClient()));
	}

	public Set<CombatEntite> getEntites() {
		return entites;
	}

	public Set<CombatEquipe> getEquipes() {
		return equipes;
	}

}
