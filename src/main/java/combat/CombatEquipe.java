/*
 * 
 * 
 * 
 */
package combat;

import combat.entite.CombatPersonnage;
import combat.entite.CombatEntite;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CombatEquipe.java
 * 
 */
public class CombatEquipe extends HashSet<CombatEntite> {
	
	public Set<CombatEntite> getCombatEntites(Predicate<CombatEntite> predicate) {
		return this.stream().filter(predicate).collect(Collectors.toSet());
	}
	
	public Set<CombatPersonnage> getCombatPersonnages(Predicate<CombatPersonnage> predicate) {
		return this.streamCombatPersonnages()
				.filter(predicate)
				.collect(Collectors.toSet());
	}
	
	public Stream<CombatPersonnage> streamCombatPersonnages() {
		return this.stream()
				.filter((c) -> c instanceof CombatPersonnage)
				.map((c) -> (CombatPersonnage) c);
	}

}
