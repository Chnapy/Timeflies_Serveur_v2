/*
 * 
 * 
 * 
 */
package salon.proprietes;

import salon.Salon;
import salon.TypeVisibilite;

/**
 * PropVisibilite.java
 * 
 */
public class PropVisibilite extends ProprieteLimite<TypeVisibilite, Integer> {

	private static final int MIN = 1;

	public PropVisibilite(Salon salon) {
		super(salon, TypePropriete.VISIBILITE, TypeVisibilite.PUBLIQUE, MIN, null);
	}

	@Override
	protected TypeVisibilite objectToValeur(Integer newValeur) throws IllegalArgumentException {
		return TypeVisibilite.getFromId(newValeur);
	}

	@Override
	public void afterSet() {
		//TODO
	}

}
