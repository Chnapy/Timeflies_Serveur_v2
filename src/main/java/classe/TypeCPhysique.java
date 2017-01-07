/*
 * 
 * 
 * 
 */

package classe;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * TypeCPhysique
 * Enum
 */
public enum TypeCPhysique {
	
	VITALITE(1),
	TEMPSACTION(2),
	TEMPSSUP(3),
	VITESSE(4),
	FATIGUE(5),
	INITIATIVE(6),
	NIVEAU(7),
	COOLDOWN(8);
	
	private final int id;
	
	private TypeCPhysique(int id) {
		this.id = id;
	}

	@JsonValue
	public int getId() {
		return id;
	}
	
	public static TypeCPhysique getFromId(int id) throws IllegalArgumentException {
		for(TypeCPhysique tc : values()) {
			if(tc.getId() == id) {
				return tc;
			}
		}
		throw new IllegalArgumentException("id : " + id);
	}

}
