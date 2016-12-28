/*
 * 
 * 
 * 
 */
package salon;

import map.Position;
import map.TypeTuile;

/**
 * ClasseTuile.java
 * 
 */
public class ClasseTuile {
	
	private final TypeTuile type;
	private final Position position;
	private final int equipe;
	
	public ClasseTuile(int type, Position position) {
		this(type, position, -1);
	}
	
	public ClasseTuile(int type, Position position, int equipe) {
		this.type = TypeTuile.getFromId(type);
		this.position = position;
		this.equipe = equipe;
	}

}
