/*
 * 
 * 
 * 
 */
package salon.typecombatproprietes;

import main.Const;
import salon.Salon;
import salon.SalonEquipe;
import salon.net.events.NetSalonEventSetProprietes;

/**
 * ChacunPourSoi.java
 *
 */
public class ChacunPourSoi extends SalonProprietes {

	private int nbrPersosTotaux;

	public ChacunPourSoi() {
		this.nbrPersosTotaux = Const.NBR_SALON_PERSOS_TOTAL;
	}

	public int getNbrPersosTotaux() {
		return nbrPersosTotaux;
	}

	public void setNbrPersosTotaux(int nbrPersosTotaux) {
		if (nbrPersosTotaux > 1 && nbrPersosTotaux <= Const.NBR_SALON_PERSOS_TOTAL) {
			this.nbrPersosTotaux = nbrPersosTotaux;
		}
	}

	@Override
	public void setProprietes(NetSalonEventSetProprietes.RecSetProprietes data) {
		data.setNbr_equipe(null);
		data.setNbr_eq_persos_classe(null);
		data.setNbr_eq_persos_equipe(null);
		data.setNbr_eq_persos_joueur(null);

		if (data.getNbr_persos_total() != null) {
			this.setNbrPersosTotaux(data.getNbr_persos_total());
			data.setNbr_persos_total(this.getNbrPersosTotaux());
		}
	}

	@Override
	public boolean checkProprietes(Salon s) {
		int nbrTotal = 0;
		for(SalonEquipe se : s.getEquipes().values()) {
			nbrTotal += se.size();
		}
		if(nbrTotal > this.nbrPersosTotaux) {
			return false;
		}
		return true;
	}
}
