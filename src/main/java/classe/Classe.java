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

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
		final Classe other = (Classe) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

}
