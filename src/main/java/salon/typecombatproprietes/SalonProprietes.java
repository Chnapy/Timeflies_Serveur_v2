/*
 * 
 * 
 * 
 */
package salon.typecombatproprietes;

import client.Personnage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Map;
import salon.Salon;
import salon.SalonEquipe;
import salon.net.events.NetSalonEventSetProprietes;
import salon.proprietes.Proprietable;

/**
 * SalonProprietes.java
 *
 */
public abstract class SalonProprietes extends Proprietable {

	@JsonIgnore
	protected final Salon salon;

	public SalonProprietes(Salon salon) {
		this.salon = salon;
	}

	public abstract void setProprietes(NetSalonEventSetProprietes.RecSetProprietes data);

	public boolean ajouterPerso(Personnage p, SalonEquipe se) {
		return se.add(p);
	}

	public abstract void checkEquipes(Map<Integer, SalonEquipe> equipes);

}
