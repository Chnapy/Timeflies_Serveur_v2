/*
 * 
 * 
 * 
 */
package classe;

/**
 * FonctionXP
 * Enum
 */
public enum FonctionXP {

	SQRT() {
		@Override
		public int getNiveau(int xp, int influenceur) {
			return (int) (influenceur * Math.sqrt(xp));
		}
		
		@Override
		public int getXP(float niveau, int influenceur) {
			return (int) Math.pow(niveau / influenceur, 2);
		}
	};

	private FonctionXP() {
	}

	public abstract int getNiveau(int xp, int influenceur);
	
	public abstract int getXP(float niveau, int influenceur);

}
