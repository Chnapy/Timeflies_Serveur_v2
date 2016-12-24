/*
 * 
 * 
 * 
 */
package map;

import java.util.HashMap;
import java.util.List;

/**
 * CombatMap.java
 *
 */
public class CombatMap extends HashMap<Position, Tuile> {

	public CombatMap(List<Tuile> tuiles) {
		tuiles.forEach((t) -> this.put(t.getPosition(), t));
	}

}
