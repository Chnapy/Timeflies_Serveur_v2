/*
 * 
 * 
 * 
 */
package classe;

import classe.zone.Zone;
import java.util.List;

/**
 * ClasseSortActif.java
 * 
 */
public abstract class ClasseSortActif extends ClasseSort {
	
	private final Zone zonePortee;
	private final Zone zoneAction;
	
	private final int tempsAction;
	private final int cooldown;
	private final int fatigue;

	public ClasseSortActif(long id, List<Effet> effets, ClasseXP classeXP, Zone zonePortee, Zone zoneAction, int tempsAction, int cooldown, int fatigue) {
		super(id, effets, classeXP);
		this.zonePortee = zonePortee;
		this.zoneAction = zoneAction;
		this.tempsAction = tempsAction;
		this.cooldown = cooldown;
		this.fatigue = fatigue;
	}

	public Zone getZonePortee() {
		return zonePortee;
	}

	public Zone getZoneAction() {
		return zoneAction;
	}

	public int getTempsAction() {
		return tempsAction;
	}

	public int getCooldown() {
		return cooldown;
	}

	public int getFatigue() {
		return fatigue;
	}

}
