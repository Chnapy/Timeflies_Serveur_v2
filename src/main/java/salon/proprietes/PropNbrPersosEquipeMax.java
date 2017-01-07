/*
 * 
 * 
 * 
 */
package salon.proprietes;

import client.Client;
import client.Personnage;
import java.util.ArrayList;
import java.util.HashMap;
import main.Const;
import salon.Salon;

/**
 * PropNbrPersosEquipeMax.java
 *
 */
public class PropNbrPersosEquipeMax extends ProprieteLimite<Integer, Integer> {

	private static final int MIN = 0, MAX = Const.NBR_SALON_PERSOS_EQUIPE_MAX;

	public PropNbrPersosEquipeMax(Salon salon) {
		super(salon, TypePropriete.NBR_PERSOS_EQUIPE, MAX, MIN, MAX);
	}

	@Override
	protected Integer objectToValeur(Integer newValeur) throws IllegalArgumentException {
		return newValeur;
	}

	@Override
	protected void afterSet() {
		ArrayList<Personnage> toRemove = new ArrayList();
		this.salon.getEquipes().values().forEach((se)
				-> {
			toRemove.clear();
			int i = 0;
			for(Personnage p : se) {
				i++;
				if(i > getValeur()) {
					toRemove.add(p);
				}
			}
			se.removeAll(toRemove);
		});
	}

}
