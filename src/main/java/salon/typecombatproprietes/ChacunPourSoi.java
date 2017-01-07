/*
 * 
 * 
 * 
 */
package salon.typecombatproprietes;

import java.util.Arrays;
import java.util.List;
import salon.Salon;
import salon.net.events.NetSalonEventSetProprietes;
import salon.proprietes.PropNbrPersosTotaux;
import salon.proprietes.Propriete;
import salon.proprietes.TypePropriete;

/**
 * ChacunPourSoi.java
 *
 */
public class ChacunPourSoi extends SalonProprietes {

	public ChacunPourSoi(Salon s) {
		super(s);
		this.initProprietes();
	}

	@Override
	public List<Propriete> getAllProp() {
		return Arrays.asList(
				new PropNbrPersosTotaux(salon)
		);
	}

	@Override
	public void setProprietes(NetSalonEventSetProprietes.RecSetProprietes data) {
		data.setNbr_equipe(null);
		data.setNbr_eq_persos_classe(null);
		data.setNbr_eq_persos_equipe(null);
		data.setNbr_eq_persos_joueur(null);

		if (data.getNbr_persos_total() != null) {
			try {
				this.setProp(TypePropriete.NBR_PERSOS_TOTAUX, data.getNbr_persos_total());
			} catch (IllegalArgumentException ex) {
				data.setNbr_persos_total(this.<PropNbrPersosTotaux>getProp(TypePropriete.NBR_PERSOS_TOTAUX).getValeur());
			}
		}
	}
}
