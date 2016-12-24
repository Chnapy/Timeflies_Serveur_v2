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
	};

	private FonctionXP() {
	}

	public abstract int getNiveau(int xp, int influenceur);

}
