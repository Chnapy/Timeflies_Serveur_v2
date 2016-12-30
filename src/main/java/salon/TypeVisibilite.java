/*
 * 
 * 
 * 
 */

package salon;

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

	public int getId() {
		return id;
	}
}
