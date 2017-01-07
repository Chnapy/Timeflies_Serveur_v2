/*
 * 
 * 
 * 
 */
package map;

/**
 * Position.java
 * 
 */
public class Position {
	
	private int x;
	private int y;
	
	public Position() {
		x = y = -1;
	}
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Position other = (Position) obj;
		if (this.x != other.x) {
			return false;
		}
		if (this.y != other.y) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 19 * hash + this.x;
		hash = 19 * hash + this.y;
		return hash;
	}

	@Override
	public String toString() {
		return "Position{" + "x=" + x + ", y=" + y + '}';
	}

}
