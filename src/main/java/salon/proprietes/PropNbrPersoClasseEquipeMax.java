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
 * PropNbrPersoClasseEquipeMax.java
 *
 */
public class PropNbrPersoClasseEquipeMax extends ProprieteLimite<Integer, Integer> {

	private static final Integer MIN = 0, MAX = null;

	public PropNbrPersoClasseEquipeMax(Salon salon) {
		super(salon, TypePropriete.NBR_PERSOS_CLASSE_EQUIPE, Const.NBR_SALON_PERSOS_EQUIPE_MAX, MIN, MAX);
	}

	@Override
	protected Integer objectToValeur(Integer newValeur) throws IllegalArgumentException {
		return newValeur;
	}

	@Override
	protected void afterSet() {
		HashMap<ClasseEntite, Integer> nbrClasses = new HashMap();
		ArrayList<Personnage> toRemove = new ArrayList();
		this.salon.getEquipes().values().forEach((se)
				-> {
			nbrClasses.clear();
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
