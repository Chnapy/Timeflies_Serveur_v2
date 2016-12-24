/*
 * 
 * 
 * 
 */
package classe;

import java.util.List;

/**
 * ClasseEnvoutement.java
 *
 */
public abstract class ClasseEnvoutement extends Classe {

	public static final int DUREE_INFINI = -1;

	private final List<Declencheur> declencheurs;
	private final List<Effet> effets;
	private final int duree;

	public ClasseEnvoutement(long id, List<Declencheur> declencheurs, List<Effet> effets, int duree) {
		super(id);
		this.declencheurs = declencheurs;
		this.effets = effets;
		this.duree = duree;
	}

	public List<Declencheur> getDeclencheurs() {
		return declencheurs;
	}

	public List<Effet> getEffets() {
		return effets;
	}

	public int getDuree() {
		return duree;
	}

}
