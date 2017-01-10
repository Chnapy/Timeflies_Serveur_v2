/*
 * 
 * 
 * 
 */
package classe;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ClasseEntite.java
 *
 */
public abstract class ClasseEntite extends Classe {

	private final Set<ClasseSort> sorts;

	@JsonIgnore
	private final ClasseXP classeXP;

	private final Map<TypeCPhysique, Integer> cPhysique;
	
	private final boolean actif;

	public ClasseEntite(long id, boolean actif, Set<ClasseSort> sorts, ClasseXP classeXP,
			Map<TypeCPhysique, Integer> cPhysique) {
		super(id);
		this.actif = actif;
		this.sorts = Collections.unmodifiableSet(sorts);
		this.classeXP = classeXP;
		this.cPhysique = Collections.unmodifiableMap(cPhysique);
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

	public boolean isActif() {
		return actif;
	}

	@JsonGetter("sorts")
	public Set<Long> getJSONSorts() {
		return sorts.stream()
				.map((s) -> s.getId())
				.collect(Collectors.toSet());
	}

}
