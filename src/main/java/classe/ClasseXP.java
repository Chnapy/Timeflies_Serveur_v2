/*
 * 
 * 
 * 
 */
package classe;

/**
 * ClasseXP.java
 *
 */
public class ClasseXP {

	private final FonctionXP fonctionXP;
	private final int influenceur;

	public ClasseXP(FonctionXP fonctionXP, int influenceur) {
		this.fonctionXP = fonctionXP;
		this.influenceur = influenceur;
	}

	public int getNiveau(int xp) {
		return fonctionXP.getNiveau(xp, influenceur);
	}

	public int getXP(int niveau) {
		return fonctionXP.getXP(niveau, influenceur);
	}

	public FonctionXP getFonctionXP() {
		return fonctionXP;
	}

	public int getInfluenceur() {
		return influenceur;
	}
}
