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
import salon.proprietes.PropNbrEquipe;
import salon.proprietes.PropNbrPersoClasseEquipeMax;
import salon.proprietes.PropNbrPersoJoueurEquipeMax;
import salon.proprietes.PropNbrPersosEquipeMax;
import salon.proprietes.Propriete;
import salon.proprietes.TypePropriete;

/**
 * ParEquipe.java
 *
 */
public final class ParEquipe extends SalonProprietes {

	public ParEquipe(Salon s) {
		super(s);
		this.initProprietes();
	}

	@Override
	public List<Propriete> getAllProp() {
		return Arrays.asList(new PropNbrEquipe(salon),
				new PropNbrPersosEquipeMax(salon),
				new PropNbrPersoClasseEquipeMax(salon),
				new PropNbrPersoJoueurEquipeMax(salon)
		);
	}

	@Override
	public void setProprietes(NetSalonEventSetProprietes.RecSetProprietes data) {
		data.setNbr_persos_total(null);

		if (data.getNbr_equipe() != null) {
			try {
				this.setProp(TypePropriete.NBR_EQUIPES_MAX, data.getNbr_equipe());
			} catch (IllegalArgumentException ex) {
				data.setNbr_equipe(this.<PropNbrEquipe>getProp(TypePropriete.NBR_EQUIPES_MAX).getValeur());
			}
		}

		if (data.getNbr_eq_persos_classe() != null) {
			try {
				this.setProp(TypePropriete.NBR_PERSOS_CLASSE_EQUIPE, data.getNbr_eq_persos_classe());
			} catch (IllegalArgumentException ex) {
				data.setNbr_eq_persos_classe(this.<PropNbrPersoClasseEquipeMax>getProp(TypePropriete.NBR_PERSOS_CLASSE_EQUIPE).getValeur());
			}
		}

		if (data.getNbr_eq_persos_equipe() != null) {
			try {
				this.setProp(TypePropriete.NBR_PERSOS_EQUIPE, data.getNbr_eq_persos_equipe());
			} catch (IllegalArgumentException ex) {
				data.setNbr_eq_persos_equipe(this.<PropNbrPersosEquipeMax>getProp(TypePropriete.NBR_PERSOS_EQUIPE).getValeur());
			}
		}

		if (data.getNbr_eq_persos_joueur() != null) {
			try {
				this.setProp(TypePropriete.NBR_PERSOS_JOUEUR_EQUIPE, data.getNbr_eq_persos_joueur());
			} catch (IllegalArgumentException ex) {
				data.setNbr_eq_persos_joueur(this.<PropNbrPersoJoueurEquipeMax>getProp(TypePropriete.NBR_PERSOS_JOUEUR_EQUIPE).getValeur());
			}
		}
	}
}
