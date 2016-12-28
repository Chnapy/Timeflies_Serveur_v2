/*
 * 
 * 
 * 
 */

package classe;

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

	public int getId() {
		return id;
	}

}
