/*
 * 
 * 
 * 
 */
package combat.entite;

import classe.ClasseEntite;
import classe.TypeCPhysique;
import client.Client;
import combat.CombatClasse;
import combat.CombatEnvoutement;
import combat.CombatEquipe;
import combat.Orientation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import map.Position;

/**
 * CombatEntite.java
 *
 */
public class CombatEntite extends CombatClasse<ClasseEntite> {
	
	private final Client client;

	private final int niveau;
	private final CombatEquipe equipe;

	private final Map<TypeCPhysique, Integer> cPhysique;
	private final Map<TypeCPhysique, Integer> cPhysiqueMax;

	private final List<CombatEnvoutement> envoutements;
	private final Position position;
	private Orientation orientation;

	public CombatEntite(Client client, ClasseEntite classe, int niveau, CombatEquipe equipe, List<Pair<TypeCPhysique, Integer>> cPhysique, List<Pair<TypeCPhysique, Integer>> cPhysiqueMax) {
		super(classe);
		this.client = client;
		this.niveau = niveau;
		this.equipe = equipe;
		this.cPhysique = new HashMap();
		cPhysique.forEach((p) -> this.cPhysique.put(p.getKey(), p.getValue()));
		this.cPhysiqueMax = new HashMap();
		cPhysiqueMax.forEach((p) -> this.cPhysiqueMax.put(p.getKey(), p.getValue()));
		this.envoutements = new ArrayList();
		this.position = new Position(-1, -1);
		this.orientation = Orientation.NORD;
	}

	public Client getClient() {
		return client;
	}

	public boolean isEnVie() {
		return this.cPhysique.get(TypeCPhysique.VITALITE) > 0;
	}
	
	public boolean isEntiteActive() {
		return this instanceof CombatEntiteActive;
	}
	
	public boolean isPersonnage() {
		return this instanceof CombatPersonnage;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public int getNiveau() {
		return niveau;
	}

	public CombatEquipe getEquipe() {
		return equipe;
	}

	public Map<TypeCPhysique, Integer> getcPhysique() {
		return cPhysique;
	}

	public Map<TypeCPhysique, Integer> getcPhysiqueMax() {
		return cPhysiqueMax;
	}

	public List<CombatEnvoutement> getEnvoutements() {
		return envoutements;
	}

	public Position getPosition() {
		return position;
	}

	public Orientation getOrientation() {
		return orientation;
	}

}
