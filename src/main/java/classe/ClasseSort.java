/*
 * 
 * 
 * 
 */
package classe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClasseSort.java
 *
 */
public abstract class ClasseSort extends Classe {

	@JsonIgnore
	private final List<Effet> effets;

	@JsonIgnore
	private final ClasseXP classeXP;

	protected final Map<TypeCPhysique, Integer> cPhysique;

	public ClasseSort(long id, List<Effet> effets, Map<TypeCPhysique, Integer> cphysique, ClasseXP classeXP, int xpParUse, int pourcDegressifXP) {
		super(id);
		this.effets = effets;
		this.classeXP = classeXP;
		this.cPhysique = cphysique;
	}

	public List<Effet> getEffets() {
		return effets;
	}

	public ClasseXP getClasseXP() {
		return classeXP;
	}

	@JsonProperty("actif")
	public boolean isActif() {
		return this instanceof ClasseSortActif;
	}

	public Map<TypeCPhysique, Integer> getcPhysique() {
		return cPhysique;
	}

}
