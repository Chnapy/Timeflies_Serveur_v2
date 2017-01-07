/*
 * 
 * 
 * 
 */
package salon.proprietes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import salon.Salon;
import salon.TypeCombat;
import salon.typecombatproprietes.SalonProprietes;

/**
 * PropTypeCombat.java
 *
 */
public class PropTypeCombat extends ProprieteLimite<TypeCombat, Integer> {

	private static final int MIN = 1;

	@JsonProperty("proprietes")
	private SalonProprietes typeCombatProprietes;

	public PropTypeCombat(Salon salon) {
		super(salon, TypePropriete.TYPECOMBAT, TypeCombat.EQUIPE, MIN, null);
		this.afterSet();
	}

	@Override
	protected TypeCombat objectToValeur(Integer newValeur) throws IllegalArgumentException {
		return TypeCombat.getFromId(newValeur);
	}

	@Override
	protected void afterSet() {
		this.typeCombatProprietes = this.getValeur().getNewSalonProprietes(salon);
	}

	public SalonProprietes getTypeCombatProprietes() {
		return typeCombatProprietes;
	}

}
