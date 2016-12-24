/*
 * 
 * 
 * 
 */
package classe;

/**
 * Classe.java
 * 
 */
public abstract class Classe {
	
	private final long id;

	public Classe(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}

}
