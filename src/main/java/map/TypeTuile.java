/*
 * 
 * 
 * 
 */
package map;

/**
 * TypeTuile
 * Enum
 */
public enum TypeTuile {

	/**
	 * Une entité peut accéder à cette case.
	 * Ne bloque pas la ligne de vue.
	 */
	SIMPLE(1, true, true),
	/**
	 * Une entité ne peut pas accéder à cette case.
	 * Ne bloque pas la ligne de vue.
	 */
	TROU(2, false, true),
	/**
	 * Une entité peut accéder à cette case.
	 * Bloque la ligne de vue.
	 */
	ECRAN(3, true, false),
	/**
	 * Une entité ne peut pas accéder à cette case.
	 * Bloque la ligne de vue.
	 */
	OBSTACLE(4, false, false);

	private final int id;
	private final boolean acces;
	private final boolean vue;

	private TypeTuile(int id, boolean acces, boolean vue) {
		this.id = id;
		this.acces = acces;
		this.vue = vue;
	}

	public int getId() {
		return id;
	}

	public boolean canAcces() {
		return acces;
	}

	public boolean canVue() {
		return vue;
	}
	
	public static TypeTuile getFromId(int id) {
		for(TypeTuile t : TypeTuile.values()) {
			if(t.getId() == id) {
				return t;
			}
		}
		return null;
	}

}
