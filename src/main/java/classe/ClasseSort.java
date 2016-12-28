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
 * ClasseSort.java
 * 
 */
public abstract class ClasseSort extends Classe implements Compressable {
	
	private final List<Effet> effets;
	private final ClasseXP classeXP;

	public ClasseSort(long id, List<Effet> effets, ClasseXP classeXP) {
		super(id);
		this.effets = effets;
		this.classeXP = classeXP;
	}

	public List<Effet> getEffets() {
		return effets;
	}

	public ClasseXP getClasseXP() {
		return classeXP;
	}
	
	public boolean isActif() {
		return this instanceof ClasseSortActif;
	}

	@Override
	public Compressed getCompressed() {
		return new ClasseSortCompressed(this);
	}
	
	public class ClasseSortCompressed implements Compressed {
		
		private long id;

		public ClasseSortCompressed(ClasseSort cs) {
			this.id = cs.getId();
		}

		public long getId() {
			return id;
		}
		
	}

}
