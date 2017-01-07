/*
 * 
 * 
 * 
 */
package salon.proprietes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import salon.Salon;

/**
 * Propriete.java
 *
 * @param <T>	valeur gardée
 * @param <O>	objet transmis
 */
public abstract class Propriete<T, O> {

	@JsonIgnore
	private final TypePropriete clef;
	
	private T valeur;
	
	@JsonIgnore
	protected final Salon salon;

	public Propriete(Salon salon, TypePropriete clef, T valeur) {
		this.salon = salon;
		this.clef = clef;
		this.valeur = valeur;
	}

	protected boolean isValid(O newValeur) {
		return newValeur != null;
	}

	protected abstract T objectToValeur(O newValeur) throws IllegalArgumentException;

	protected void afterSet() {
	}

	public void setValeur(O newValeur) throws IllegalArgumentException {
		if (!isValid(newValeur)) {
			throw new IllegalArgumentException();
		}
		this.valeur = objectToValeur(newValeur);
		afterSet();
	}

	public void setToNullValeur() {
		this.valeur = null;
	}

	@JsonValue
	public T getValeur() {
		return valeur;
	}

	public TypePropriete getClef() {
		return clef;
	}

}
