/*
 * 
 * 
 * 
 */
package classe.zone;

import java.util.Objects;
import java.util.Set;
import map.Position;

/**
 * CoucheZone.java
 * 
 */
public abstract class CoucheZone {
	
	private final boolean positive;
	
	//Origine = (0,0)
	private final Set<Position> positions;
	private final int rayon;

	public CoucheZone(boolean positive, int rayon) {
		this.positive = positive;
		this.rayon = rayon;
		this.positions = getAllPositions(rayon);
	}
	
	protected abstract Set<Position> getAllPositions(int rayon);

	public boolean isPositive() {
		return positive;
	}

	public Set<Position> getPositions() {
		return positions;
	}

	public int getRayon() {
		return rayon;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 73 * hash + (this.positive ? 1 : 0);
		hash = 73 * hash + Objects.hashCode(this.positions);
		hash = 73 * hash + this.rayon;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final CoucheZone other = (CoucheZone) obj;
		if (this.positive != other.positive) {
			return false;
		}
		if (this.rayon != other.rayon) {
			return false;
		}
		if (!Objects.equals(this.positions, other.positions)) {
			return false;
		}
		return true;
	}

}
