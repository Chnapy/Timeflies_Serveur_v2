/*
 * 
 * 
 * 
 */
package salon.typecombatproprietes;

import salon.Salon;
import salon.net.events.NetSalonEventSetProprietes;

/**
 * SalonProprietes.java
 * 
 */
public abstract class SalonProprietes {

	public abstract void setProprietes(NetSalonEventSetProprietes.RecSetProprietes data);

	public abstract boolean checkProprietes(Salon s);

}
