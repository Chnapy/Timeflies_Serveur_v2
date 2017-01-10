/*
 * 
 * 
 * 
 */
package classe.zone;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import map.Position;

/**
 * TypeZone.java
 *
 */
public enum TypeZone {

	CARRE(1) {
		@Override
		public Set<Position> getAllPositions(int rayon) {
			Set<Position> ret = new HashSet();

			for (int i = 0; i < rayon; i++) {
				for (int j = 0; j < rayon; j++) {
					ret.addAll(Arrays.asList(
							new Position(-j, i),
							new Position(j, i),
							new Position(-j, -i),
							new Position(j, -i)
					));
				}
			}

			return ret;
		}

	},
	CERCLE(2) {
		@Override
		public Set<Position> getAllPositions(int rayon) {
			Set<Position> ret = new HashSet();

//			for (int i = 0; i < rayon; i++) {
//				for (int j = 0; j < rayon; j++) {
//					ret.addAll(Arrays.asList(
//							new Position(-j, i),
//							new Position(j, i),
//							new Position(-j, -i),
//							new Position(j, -i)
//					));
//				}
//			}
			return ret;
		}

	},
	CROIX(3) {
		@Override
		public Set<Position> getAllPositions(int rayon) {
			Set<Position> ret = new HashSet();

			for (int i = 0; i < rayon; i++) {
				ret.addAll(Arrays.asList(
						new Position(-i, 0),
						new Position(i, 0),
						new Position(0, -i),
						new Position(0, i)
				));
			}

			return ret;
		}

	},
	DIAGONALES(4) {
		@Override
		public Set<Position> getAllPositions(int rayon) {
			Set<Position> ret = new HashSet();

//			for (int i = 0; i < rayon; i++) {
//				for (int j = 0; j < rayon; j++) {
//					ret.addAll(Arrays.asList(
//							new Position(-j, i),
//							new Position(j, i),
//							new Position(-j, -i),
//							new Position(j, -i)
//					));
//				}
//			}
			return ret;
		}

	};

	private final int id;

	private TypeZone(int id) {
		this.id = id;
	}

	@JsonValue
	public int getId() {
		return id;
	}

	public abstract Set<Position> getAllPositions(int rayon);

	public static TypeZone getFromId(int id) throws IllegalArgumentException {
		for (TypeZone tz : values()) {
			if (tz.getId() == id) {
				return tz;
			}
		}
		throw new IllegalArgumentException("id : " + id);
	}

}
