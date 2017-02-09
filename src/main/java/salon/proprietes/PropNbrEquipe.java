/*
 * 
 * 
 * 
 */
package salon.proprietes;

import java.util.Map;
import main.Const;
import salon.Salon;
import salon.SalonEquipe;

/**
 * PropNbrEquipe.java
 *
 */
public class PropNbrEquipe extends ProprieteLimite<Integer, Integer> {

	private static final int MIN = 2, MAX = Const.NBR_SALON_EQUIPE_MAX;

	public PropNbrEquipe(Salon salon) {
		super(salon, TypePropriete.NBR_EQUIPES_MAX, MIN, MIN, MAX);
	}

	@Override
	protected Integer objectToValeur(Integer newValeur) throws IllegalArgumentException {
		return newValeur;
	}

	@Override
	public void afterSet() {
		Map<Integer, SalonEquipe> equipes = salon.getEquipes();
		int size;
		while ((size = equipes.size()) < this.getValeur()) {
			equipes.put(size, new SalonEquipe(size));
		}
		for (int i = equipes.size(); i > this.getValeur(); i--) {
			equipes.remove(i);
		}
	}

}
