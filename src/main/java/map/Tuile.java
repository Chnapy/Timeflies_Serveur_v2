/*
 * 
 * 
 * 
 */
package map;

import salon.ClasseTuile;

/**
 * Tuile.java
 * 
 */
public class Tuile {
	
	private TypeTuile type;
	private final Position position;
	
	public Tuile(ClasseTuile classeTuile) {
		this(classeTuile.getType(), classeTuile.getPosition());
	}
	
	public Tuile(TypeTuile type, Position position) {
		this.type = type;
		this.position = position;
	}

	public TypeTuile getType() {
		return type;
	}

	public void setType(TypeTuile type) {
		this.type = type;
	}

	public Position getPosition() {
		return position;
	}

}
