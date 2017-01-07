/*
 * 
 * 
 * 
 */
package classe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.util.Pair;
import netserv.Compressable;
import netserv.Compressed;

/**
 * ClasseEntite.java
 *
 */
public abstract class ClasseEntite extends Classe implements Compressable {

	@JsonIgnore
	private final Set<ClasseSort> sorts;
	
	@JsonIgnore
	private final ClasseXP classeXP;
	
	@JsonIgnore
	private final Map<TypeCPhysique, Integer> cPhysique;

	public ClasseEntite(long id, Set<ClasseSort> sorts, ClasseXP classeXP,
			List<Pair<TypeCPhysique, Integer>> cPhysique) {
		super(id);
		this.sorts = sorts;
		this.classeXP = classeXP;
		this.cPhysique = new HashMap();
		cPhysique.forEach((p) -> this.cPhysique.put(p.getKey(), p.getValue()));
	}

	public Set<ClasseSort> getSorts() {
		return sorts;
	}

	public ClasseXP getClasseXP() {
		return classeXP;
	}

	public Map<TypeCPhysique, Integer> getcPhysique() {
		return cPhysique;
	}

	@Override
	public Compressed getCompressed() {
		return new ClasseEntiteCompressed(this);
	}

	public class ClasseEntiteCompressed implements Compressed {

		private long id;
		private Set<Long> sorts;
		private Map<Integer, Integer> cPhysique;

		public ClasseEntiteCompressed(ClasseEntite c) {
			this.id = c.getId();
			this.sorts = c.getSorts().stream()
					.map((s) -> s.getId())
					.collect(Collectors.toSet());
			this.cPhysique = new HashMap();
			c.getcPhysique().forEach((ty, val) -> this.cPhysique.put(ty.getId(), val));
		}

		public long getId() {
			return id;
		}

		public Set<Long> getSorts() {
			return sorts;
		}

		public Map<Integer, Integer> getcPhysique() {
			return cPhysique;
		}
	}

}
