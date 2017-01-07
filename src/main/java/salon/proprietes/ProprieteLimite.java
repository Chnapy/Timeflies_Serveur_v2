/*
 * 
 * 
 * 
 */
package salon.proprietes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import salon.Salon;

/**
 * ProprieteLimite.java
 *
 * @param <T>
 * @param <O>
 */
public abstract class ProprieteLimite<T, O extends Number> extends Propriete<T, O> {

	@JsonIgnore
	private Optional<O> min;
	
	@JsonIgnore
	private Optional<O> max;
	
	@JsonIgnore
	private final Set<O> whitelist;

	public ProprieteLimite(Salon salon, TypePropriete clef, T valeur, O min, O max, Set<O> whitelist) {
		this(salon, clef, valeur, min, max);
		this.whitelist.addAll(whitelist);
	}

	public ProprieteLimite(Salon salon, TypePropriete clef, T valeur, O min, O max) {
		super(salon, clef, valeur);
		this.min = Optional.ofNullable(min);
		this.max = Optional.ofNullable(max);
		this.whitelist = new HashSet();
	}

	public Optional<O> getMin() {
		return min;
	}

	public Optional<O> getMax() {
		return max;
	}

	public void setMin(O min) {
		this.min = Optional.ofNullable(min);
		this.min.ifPresent((m) -> {
			if (getValeur() instanceof Number
					&& ((Number) getValeur()).doubleValue() < m.doubleValue()) {
				setValeur(m);
			}
		});
	}

	public void setMax(O max) {
		this.max = Optional.ofNullable(max);
		this.max.ifPresent((m) -> {
			if (getValeur() instanceof Number
					&& ((Number) getValeur()).doubleValue() > m.doubleValue()) {
				setValeur(m);
			}
		});
	}
	
	public void setWhitelist(Collection<O> val) {
		whitelist.clear();
		addToWhitelist(val);
		removeFromWhitelist(Collections.EMPTY_LIST);
	}
	
	public void addToWhitelist(Collection<O> val) {
		whitelist.addAll(val);
	}
	
	public void removeFromWhitelist(Collection<O> val) {
		whitelist.removeAll(val);
		if(getValeur() instanceof Number
					&& !isValid((O) getValeur())) {
//			setToNullValeur();
			setValeur(val.stream().findFirst().get());
		}
	}

	@Override
	protected boolean isValid(O newValeur) {
		return super.isValid(newValeur)
				&& !(min.isPresent() && newValeur.doubleValue() < min.get().doubleValue()
				|| max.isPresent() && newValeur.doubleValue() > max.get().doubleValue()
				|| whitelist.contains(newValeur));
	}

}
