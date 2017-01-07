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

	SQRT(1) {
		@Override
		public int getNiveau(int xp, int influenceur) {
			return (int) (influenceur * Math.sqrt(xp));
		}
		
		@Override
		public int getXP(float niveau, int influenceur) {
			return (int) Math.pow(niveau / influenceur, 2);
		}
	};
	
	private final int id;

	private FonctionXP(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public abstract int getNiveau(int xp, int influenceur);
	
	public abstract int getXP(float niveau, int influenceur);
	
	public static FonctionXP getFromId(int id) throws IllegalArgumentException {
		for(FonctionXP f : values()) {
			if(f.getId() == id) {
				return f;
			}
		}
		throw new IllegalArgumentException("id : " + id);
	}

}
