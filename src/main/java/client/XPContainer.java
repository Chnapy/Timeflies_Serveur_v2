/*
 * 
 * 
 * 
 */
package client;

import classe.ClasseXP;

/**
 * XPContainer.java
 * 
 */
public class XPContainer {
	
	private int xp;
	private final ClasseXP classeXP;
	
	public XPContainer(int xp, ClasseXP classeXP) {
		this.xp = xp;
		this.classeXP = classeXP;
	}
	
	public int getNiveau() {
		return this.classeXP.getFonctionXP().getNiveau(xp, classeXP.getInfluenceur());
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}
	
	public void addXp(int add) {
		this.xp += add;
	}

}
