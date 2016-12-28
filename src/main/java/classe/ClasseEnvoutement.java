/*
 * 
 * 
 * 
 */
package classe;

import java.util.List;
import netserv.Compressable;
import netserv.Compressed;

/**
 * ClasseEnvoutement.java
 *
 */
public abstract class ClasseEnvoutement extends Classe implements Compressable {

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

	@Override
	public Compressed getCompressed() {
		return new ClasseEnvoutementCompressed(this);
	}

	public class ClasseEnvoutementCompressed implements Compressed {

		private long id;
		private int duree;

		public ClasseEnvoutementCompressed(ClasseEnvoutement ce) {
			this.id = ce.getId();
			this.duree = ce.getDuree();
		}

		public long getId() {
			return id;
		}

		public int getDuree() {
			return duree;
		}

	}

}
