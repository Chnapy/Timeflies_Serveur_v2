/*
 * 
 * 
 * 
 */
package classe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
		this.declencheurs = Collections.unmodifiableList(declencheurs);
		this.effets = Collections.unmodifiableList(effets);
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

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash + Objects.hashCode(this.declencheurs);
		hash = 79 * hash + Objects.hashCode(this.effets);
		hash = 79 * hash + this.duree;
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
		final ClasseEnvoutement other = (ClasseEnvoutement) obj;
		if (this.duree != other.duree) {
			return false;
		}
		if (!Objects.equals(this.declencheurs, other.declencheurs)) {
			return false;
		}
		if (!Objects.equals(this.effets, other.effets)) {
			return false;
		}
		return true;
	}

}
