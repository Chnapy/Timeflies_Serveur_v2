/*
 * 
 * 
 * 
 */
package salon.proprietes;

import client.Personnage;
import java.util.ArrayList;
import main.Const;
import salon.Salon;
import salon.SalonEquipe;

/**
 * PropNbrPersosTotaux.java
 *
 */
public class PropNbrPersosTotaux extends ProprieteLimite<Integer, Integer> {

	private static final int MIN = 2, MAX = Const.NBR_SALON_PERSOS_TOTAL;

	public PropNbrPersosTotaux(Salon salon) {
		super(salon, TypePropriete.NBR_PERSOS_TOTAUX, MAX, MIN, MAX);
	}

	@Override
	protected Integer objectToValeur(Integer newValeur) throws IllegalArgumentException {
		return newValeur;
	}

	@Override
	public void afterSet() {
		ArrayList<Personnage> toRemove = new ArrayList();
		int i = 0;
		for (SalonEquipe se : this.salon.getEquipes().values()) {
			toRemove.clear();
			for (Personnage p : se) {
				i++;
				if (i > getValeur()) {
					toRemove.add(p);
				}
			}
			se.removeAll(toRemove);
		}
	}

}
