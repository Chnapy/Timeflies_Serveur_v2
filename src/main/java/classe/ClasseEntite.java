/*
 * 
 * 
 * 
 */
package classe;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.util.Pair;

/**
 * ClasseEntite.java
 *
 */
public abstract class ClasseEntite extends Classe {

	private final Set<ClasseSort> sorts;

	@JsonIgnore
	private final ClasseXP classeXP;

	private final Map<TypeCPhysique, Integer> cPhysique;

	public ClasseEntite(long id, Set<ClasseSort> sorts, ClasseXP classeXP,
			Map<TypeCPhysique, Integer> cPhysique) {
		super(id);
		this.sorts = sorts;
		this.classeXP = classeXP;
		this.cPhysique = cPhysique;
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

	@JsonGetter("sorts")
	public Set<Long> getJSONSorts() {
		return sorts.stream()
				.map((s) -> s.getId())
				.collect(Collectors.toSet());
	}

}
