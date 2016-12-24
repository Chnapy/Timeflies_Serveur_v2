/*
 * 
 * 
 * 
 */
package combat;

import classe.ClasseEntite;
import classe.TypeCPhysique;
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

	private final int niveau;
	private final Equipe equipe;

	private final Map<TypeCPhysique, Integer> cPhysique;
	private final Map<TypeCPhysique, Integer> cPhysiqueMax;

	private final List<CombatEnvoutement> envoutements;
	private final Position position;
	private Orientation orientation;

	public CombatEntite(ClasseEntite classe, int niveau, Equipe equipe, List<Pair<TypeCPhysique, Integer>> cPhysique, List<Pair<TypeCPhysique, Integer>> cPhysiqueMax) {
		super(classe);
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

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public int getNiveau() {
		return niveau;
	}

	public Equipe getEquipe() {
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
