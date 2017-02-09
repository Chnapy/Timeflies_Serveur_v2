/*
 * 
 * 
 * 
 */
package map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import salon.ClasseMap;

/**
 * CombatMap.java
 *
 */
public class CombatMap extends HashMap<Position, Tuile> {
	
	private final Map<Integer, Set<Tuile>> placementTuiles;
	
	public CombatMap(ClasseMap classeMap) {
		placementTuiles = new HashMap();
		System.out.println(classeMap + " " + classeMap.getTuiles());
		classeMap.getTuiles().forEach(t -> {
			Tuile tu = new Tuile(t);
			this.put(tu.getPosition(), tu);
			t.getEquipe().ifPresent(e -> {
				placementTuiles.putIfAbsent(t.getEquipe().get(), new HashSet());
				placementTuiles.get(t.getEquipe().get()).add(tu);
			});
		});
	}
	
}
