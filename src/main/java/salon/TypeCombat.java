/*
 * 
 * 
 * 
 */
package salon;

import com.fasterxml.jackson.annotation.JsonValue;
import combat.arretdeclencheur.CombatArretDeclencheur;
import salon.typecombatproprietes.ChacunPourSoi;
import salon.typecombatproprietes.ParEquipe;
import salon.typecombatproprietes.SalonProprietes;

/**
 * TypeCombat
 * Enum
 */
public enum TypeCombat {

	CPS(1, CombatArretDeclencheur.CPS_ARRET_DECLENCHEUR) {
		@Override
		public ChacunPourSoi getNewSalonProprietes(Salon s) {
			return new ChacunPourSoi(s);
		}
	},
	EQUIPE(2, CombatArretDeclencheur.EQUIPE_ARRET_DECLENCHEUR) {
		@Override
		public ParEquipe getNewSalonProprietes(Salon s) {
			return new ParEquipe(s);
		}
	};

	private final int id;
	private final CombatArretDeclencheur arretDeclencheur;

	private TypeCombat(int id, CombatArretDeclencheur arretDeclencheur) {
		this.id = id;
		this.arretDeclencheur = arretDeclencheur;
	}

	@JsonValue
	public int getId() {
		return id;
	}

	public CombatArretDeclencheur getArretDeclencheur() {
		return arretDeclencheur;
	}

	public abstract <S extends SalonProprietes> S getNewSalonProprietes(Salon s);

	public static TypeCombat getFromId(int id) throws IllegalArgumentException {
		for (TypeCombat t : TypeCombat.values()) {
			if (t.getId() == id) {
				return t;
			}
		}
		throw new IllegalArgumentException("id : " + id);
	}

}
