/*
 * 
 * 
 * 
 */
package combat;

import map.Position;

/**
 * Orientation.java
 *
 */
public enum Orientation {

	NORD, EST, OUEST, SUD;

	public static Orientation getDirection(Position from, Position to) {
		if (from.equals(to)) {
			System.err.println(to);
			throw new IllegalArgumentException("Les deux points sont egaux ! " + to);
		}

		int vecX = to.getX() - from.getX();
		int vecY = to.getY() - from.getY();

		if (vecX > 0) {	//Est
			if (vecY > 0) {	//Sud
				if (vecX < vecY) {
					return Orientation.SUD;
				} else {
					return Orientation.EST;
				}
			} else //Nord
			{
				if (vecX < vecY) {
					return Orientation.NORD;
				} else {
					return Orientation.EST;
				}
			}
		} else //Ouest
		{
			if (vecY > 0) {	//Sud
				if (vecX < vecY) {
					return Orientation.SUD;
				} else {
					return Orientation.OUEST;
				}
			} else //Nord
			{
				if (vecX > vecY) {
					return Orientation.NORD;
				} else {
					return Orientation.OUEST;
				}
			}
		}
	}

}
