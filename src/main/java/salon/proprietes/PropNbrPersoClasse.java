/*
 * 
 * 
 * 
 */
package salon.proprietes;

import classe.ClasseEntite;
import client.Personnage;
import java.util.ArrayList;
import java.util.HashMap;
import main.Const;
import salon.Salon;

/**
 * PropNbrPersoClasse.java
 *
 */
public class PropNbrPersoClasse extends ProprieteLimite<Integer, Integer> {

	private static final int MIN = 0;

	public PropNbrPersoClasse(Salon salon) {
		super(salon, TypePropriete.NBR_PERSOS_CLASSE, Const.NBR_SALON_PERSOS_TOTAL, MIN, null);
	}

	@Override
	protected Integer objectToValeur(Integer newValeur) throws IllegalArgumentException {
		return newValeur;
	}

	@Override
	public void afterSet() {
		HashMap<ClasseEntite, Integer> nbrClasses = new HashMap();
		ArrayList<Personnage> toRemove = new ArrayList();
		this.salon.getEquipes().values().forEach((se)
				-> {
			toRemove.clear();
			se.forEach(p -> {
				nbrClasses.merge(p.getClasse(), 1, (t, u) -> {
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
