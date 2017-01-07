/*
 * 
 * 
 * 
 */
package salon.proprietes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Proprietable
 * Interface
 */
public abstract class Proprietable {

	@JsonIgnore
	private final Map<TypePropriete, Propriete> proprietes;

	public Proprietable() {
		this.proprietes = new HashMap();
	}

	@JsonGetter("proprietes")
	public Map<TypePropriete, Propriete> getProprietes() {
		return proprietes;
	}

	@JsonIgnore
	protected abstract List<Propriete> getAllProp();

	protected void initProprietes() {
		getAllProp().forEach(p -> proprietes.put(p.getClef(), p));
	}

	public <P extends Propriete> P getProp(TypePropriete tp) {
		return (P) Optional.ofNullable(proprietes.get(tp)).orElseThrow(IllegalArgumentException::new);
	}

	public <O> void setProp(TypePropriete tp, O newValue) throws IllegalArgumentException {
		getProp(tp).setValeur(newValue);
	}

	public void alterProp(TypePropriete tp, Consumer action) {

	}

}
