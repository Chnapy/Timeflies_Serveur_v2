/*
 * 
 * 
 * 
 */
package classe.zone;

import java.util.Set;
import map.Position;

/**
 * CoucheZone.java
 * 
 */
public class CoucheZone {
	
	private final TypeZone typeZone;
	private final boolean positive;
	
	//Origine = (0,0)
	private final Set<Position> positions;
	private final int rayon;

	public CoucheZone(TypeZone typeZone, boolean positive, int rayon) {
		this.typeZone = typeZone;
		this.positive = positive;
		this.rayon = rayon;
		this.positions = getAllPositions();
	}
	
	private Set<Position> getAllPositions() {
		return typeZone.getAllPositions(rayon);
	}

	public boolean isPositive() {
		return positive;
	}

	public Set<Position> getPositions() {
		return positions;
	}

	public int getRayon() {
		return rayon;
	}

}
