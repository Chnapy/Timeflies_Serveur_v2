/*
 * 
 * 
 * 
 */
package classe.zone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import map.Position;

/**
 * Zone.java
 *
 */
public abstract class Zone {

	@JsonIgnore
	private final Set<CoucheZone> couches;

	//Origine = (0,0)
	private final Set<Position> positions;
	private final int rayon;

	public Zone(Set<CoucheZone> couches) {
		this.couches = couches;
		this.positions = this.getAllPositions();
		this.rayon = this.getMaxRayon();
	}

	private int getMaxRayon() {
		int r = 0;

		for (CoucheZone c : couches) {
			if (c.isPositive() && c.getRayon() > r) {
				r = c.getRayon();
			}
		}

		return r;
	}

	private Set<Position> getAllPositions() {
		Set<Position> ret = new HashSet();

		List<CoucheZone> temp = new ArrayList(couches);
		temp.sort((c1, c2) -> {
			return c1.isPositive() ? -1 : 1;
		});

		temp.forEach((c) -> {
			if (c.isPositive()) {
				ret.addAll(c.getPositions());
			} else {
				ret.removeAll(c.getPositions());
			}
		});
		
		return ret;
	}

}
