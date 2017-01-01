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
 * ParEquipe.java
 *
 */
public class ParEquipe extends SalonProprietes {

	private int nbrEquipe, nbrPersosEquipe, nbrPersosClasse, nbrPersosJoueur;

	public ParEquipe() {
		this.nbrEquipe = 2;
		this.nbrPersosEquipe = Const.NBR_SALON_PERSOS_EQUIPE_MAX;
		this.nbrPersosClasse = -1;
		this.nbrPersosJoueur = -1;
	}

	public int getNbrEquipe() {
		return nbrEquipe;
	}

	public void setNbrEquipe(int nbrEquipe) {
		if (nbrEquipe > 1 && nbrEquipe <= Const.NBR_SALON_EQUIPE_MAX) {
			this.nbrEquipe = nbrEquipe;
		}
	}

	public int getNbrPersosEquipe() {
		return nbrPersosEquipe;
	}

	public void setNbrPersosEquipe(int nbrPersosEquipe) {
		if (nbrPersosEquipe > 0 && nbrPersosEquipe <= Const.NBR_SALON_PERSOS_EQUIPE_MAX) {
			this.nbrPersosEquipe = nbrPersosEquipe;
		}
	}

	public int getNbrPersosJoueur() {
		return nbrPersosJoueur;
	}

	public void setNbrPersosJoueur(int nbrPersosJoueur) {
		if (nbrPersosJoueur > 0) {
			this.nbrPersosJoueur = nbrPersosJoueur;
		}
	}

	public int getNbrPersosClasse() {
		return nbrPersosClasse;
	}

	public void setNbrPersosClasse(int nbrPersosClasse) {
		if (nbrPersosClasse > 0) {
			this.nbrPersosClasse = nbrPersosClasse;
		}
	}

	@Override
	public void setProprietes(NetSalonEventSetProprietes.RecSetProprietes data) {
		data.setNbr_persos_total(null);

		if (data.getNbr_equipe() != null) {
			this.setNbrEquipe(data.getNbr_equipe());
			data.setNbr_equipe(getNbrEquipe());
		}

		if (data.getNbr_eq_persos_classe() != null) {
			this.setNbrPersosClasse(data.getNbr_eq_persos_classe());
			data.setNbr_eq_persos_classe(getNbrPersosClasse());
		}

		if (data.getNbr_eq_persos_equipe() != null) {
			this.setNbrPersosEquipe(data.getNbr_eq_persos_equipe());
			data.setNbr_eq_persos_equipe(getNbrPersosEquipe());
		}

		if (data.getNbr_eq_persos_joueur() != null) {
			this.setNbrPersosJoueur(data.getNbr_eq_persos_joueur());
			data.setNbr_eq_persos_joueur(getNbrPersosJoueur());
		}
	}

	@Override
	public boolean checkProprietes(Salon s) {
		if (s.getEquipes().size() > this.nbrEquipe) {
			return false;
		}

		for (SalonEquipe se : s.getEquipes().values()) {
			if (se.size() > this.nbrPersosEquipe) {
				return false;
			}

			if (se.getNbrMaxMemeClasse() > this.nbrPersosClasse) {
				return false;
			}

			if (se.getNbrMaxMemeClient() > this.nbrPersosJoueur) {
				return false;
			}
		}

		return true;
	}
}
