/*
 * 
 * 
 * 
 */
package classe;

import classe.zone.Zone;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ClasseSort.java
 *
 */
public abstract class ClasseSort extends Classe {

	private final boolean actif;
	
	@JsonIgnore
	private final List<Effet> effets;

	@JsonIgnore
	private final ClasseXP classeXP;

	protected final Map<TypeCPhysique, Integer> cPhysique;
	
	private final Set<ClasseEnvoutement> envoutements;
	
	private final Zone zonePortee;
	private final Zone zoneAction;

	public ClasseSort(long id, boolean actif, List<Effet> effets, Map<TypeCPhysique, Integer> cphysique, ClasseXP classeXP, int xpParUse, int pourcDegressifXP, Zone zonePortee, Zone zoneAction, Set<ClasseEnvoutement> envoutements) {
		super(id);
		this.actif = actif;
		this.effets = Collections.unmodifiableList(effets);
		this.classeXP = classeXP;
		this.cPhysique = Collections.unmodifiableMap(cphysique);
		this.zonePortee = zonePortee;
		this.zoneAction = zoneAction;
		this.envoutements = Collections.unmodifiableSet(envoutements);
	}

	public List<Effet> getEffets() {
		return effets;
	}

	public ClasseXP getClasseXP() {
		return classeXP;
	}

	@JsonProperty("actif")
	public boolean isActif() {
		return this.actif;
	}

	public Map<TypeCPhysique, Integer> getcPhysique() {
		return cPhysique;
	}

	public Zone getZonePortee() {
		return zonePortee;
	}

	public Zone getZoneAction() {
		return zoneAction;
	}

	public Set<ClasseEnvoutement> getEnvoutements() {
		return envoutements;
	}

	@JsonGetter("envoutements")
	public Set<Long> getJSONEnvoutements() {
		return envoutements.stream()
				.map((e) -> e.getId())
				.collect(Collectors.toSet());
	}

}
