/*
 * 
 * 
 * 
 */
package classe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

/**
 * ClasseEnvoutement.java
 *
 */
public abstract class ClasseEnvoutement extends Classe {

	public static final int DUREE_INFINI = -1;

	@JsonIgnore
	private final List<Declencheur> declencheurs;
	
	@JsonIgnore
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
