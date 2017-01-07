/*
 * 
 * 
 * 
 */

package salon;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * TypeVisibilite
 * Enum
 */
public enum TypeVisibilite {
	
	PRIVE(1), PUBLIQUE(2);
	
	private final int id;
	
	private TypeVisibilite(int id) {
		this.id = id;
	}

	@JsonValue
	public int getId() {
		return id;
	}
	
	public static TypeVisibilite getFromId(int id) throws IllegalArgumentException {
		for(TypeVisibilite tv : values()) {
			if(tv.getId() == id) {
				return tv;
			}
		}
		throw new IllegalArgumentException("id : " + id);
	}
}
