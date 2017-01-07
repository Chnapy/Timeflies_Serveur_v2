/*
 * 
 * 
 * 
 */
package classe;

import classe.zone.Zone;
import java.util.List;
import java.util.Map;

/**
 * ClasseSortActif.java
 * 
 */
public abstract class ClasseSortActif extends ClasseSort {
	
	private final Zone zonePortee;
	private final Zone zoneAction;

	public ClasseSortActif(long id, List<Effet> effets, Map<TypeCPhysique, Integer> cphysique, ClasseXP classeXP, int xpParUse, int pourcDegressifXP, Zone zonePortee, Zone zoneAction) {
		super(id, effets, cphysique, classeXP, xpParUse, pourcDegressifXP);
		this.zonePortee = zonePortee;
		this.zoneAction = zoneAction;
	}

	public Zone getZonePortee() {
		return zonePortee;
	}

	public Zone getZoneAction() {
		return zoneAction;
	}

}
