/*
 * 
 * 
 * 
 */

package classe;

import combat.entite.CombatEntite;
import java.util.Set;
import map.Tuile;

/**
 * Effet
 * Interface
 */
public interface Effet {
	
	public static final Effet DEBUT_COMBAT = (Set<Tuile> tuile, CombatEntite cible, CombatEntite lanceur) -> {
	};
	
	

	public abstract void affecter(Set<Tuile> tuile, CombatEntite cible, CombatEntite lanceur);
	
}
