/*
 * 
 * 
 * 
 */
package client;

import classe.ClasseSort;
import classe.ClasseXP;
import java.util.Map;

/**
 * PersoXPContainer.java
 * 
 */
public class PersoXPContainer extends XPContainer {
	
	private final Map<ClasseSort, XPContainer> sortXP;

	public PersoXPContainer(Map<ClasseSort, XPContainer> sortXP, ClasseXP classeXP) {
		super(-1, classeXP);
		this.sortXP = sortXP;
		calculer();
	}
	
	public final void calculer() {
		int xp = 0;
		
		xp = this.sortXP.values().stream().map((xpc) -> xpc.getXp()).reduce(xp, Integer::sum);
		
		setXp(xp);
	}

}
