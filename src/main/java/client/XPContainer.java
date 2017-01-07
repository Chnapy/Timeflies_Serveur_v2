/*
 * 
 * 
 * 
 */
package client;

import classe.ClasseXP;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * XPContainer.java
 * 
 */
public class XPContainer {
	
	private int xp;
	
	@JsonIgnore
	private final ClasseXP classeXP;
	
	public XPContainer(int xp, ClasseXP classeXP) {
		this.xp = xp;
		this.classeXP = classeXP;
	}
	
	public int getNiveau() {
		return this.classeXP.getFonctionXP().getNiveau(xp, classeXP.getInfluenceur());
	}
	
	public int getXPActu() {
		return this.xp - this.classeXP.getXP(getNiveau());
	}
	
	public int getXPRestant() {
		return this.classeXP.getXP(getNiveau() + 1) - this.xp;
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
	
//	public class XPCompressed implements Compressed {
//		
//		private int niveau;
//		private int xpActu;
//		private int xpRestantTotal;
//
//		private XPCompressed(XPContainer cont) {
//			this.niveau = cont.getNiveau();
//			this.xpActu = cont.getXPActu();
//			this.xpRestantTotal = this.xpActu + cont.getXPRestant();
//		}
//
//		public int getNiveau() {
//			return niveau;
//		}
//
//		public void setNiveau(int niveau) {
//			this.niveau = niveau;
//		}
//
//		public int getXpActu() {
//			return xpActu;
//		}
//
//		public void setXpActu(int xpActu) {
//			this.xpActu = xpActu;
//		}
//
//		public int getXpRestantTotal() {
//			return xpRestantTotal;
//		}
//
//		public void setXpRestantTotal(int xpRestantTotal) {
//			this.xpRestantTotal = xpRestantTotal;
//		}
//	}

}
