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
 * PropNbrPersoJoueurEquipeMax.java
 *
 */
public class PropNbrPersoJoueurEquipeMax extends ProprieteLimite<Integer, Integer> {

	private static final Integer MIN = 0, MAX = null;

	public PropNbrPersoJoueurEquipeMax(Salon salon) {
		super(salon, TypePropriete.NBR_PERSOS_JOUEUR_EQUIPE, Const.NBR_SALON_PERSOS_EQUIPE_MAX, MIN, MAX);
	}

	@Override
	protected Integer objectToValeur(Integer newValeur) throws IllegalArgumentException {
		return newValeur;
	}

	@Override
	public void afterSet() {
		HashMap<Client, Integer> nbrClient = new HashMap();
		ArrayList<Personnage> toRemove = new ArrayList();
		this.salon.getEquipes().values().forEach((se)
				-> {
			nbrClient.clear();
			toRemove.clear();
			se.forEach(p -> {
				nbrClient.merge(p.getClient(), 1, (t, u) -> {
					if (t >= getValeur()) {
						toRemove.add(p);
					}
					return t + 1;
				});
			});
			se.removeAll(toRemove);
		});
	}

}
