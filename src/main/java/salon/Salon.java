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
import salon.typecombatproprietes.SalonProprietes;

/**
 * Salon.java
 * 
 */
public class Salon {
	
	private ClasseMap map;
	private TypeVisibilite visibilite;
	private TypeCombat typeCombat;
	private SalonProprietes typeCombatProprietes;
	private int nbrPersoClasse;
	private final Map<Client, Boolean> clients;
	private final Set<SalonEquipe> equipes;
	
	public Salon() {
		this.map = null;
		this.visibilite = TypeVisibilite.PUBLIQUE;
		this.typeCombat = TypeCombat.EQUIPE;
		this.typeCombatProprietes = this.typeCombat.getNewSalonProprietes();
		this.nbrPersoClasse = 0;
		this.clients = new HashMap();
		this.equipes = new HashSet();
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

}
