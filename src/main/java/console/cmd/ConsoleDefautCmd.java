/*
 * 
 * 
 * 
 */
package console.cmd;

import console.ConsoleCmd;
import console.ConsoleDisplayer;
import java.util.Scanner;

/**
 * ConsoleDefautCmd.java
 * 
 */
public class ConsoleDefautCmd extends ConsoleCmd {

	public ConsoleDefautCmd() {
		super("");
	}

	@Override
	protected void lancer(Scanner sc, ConsoleDisplayer displayer) {
		displayer.printErr("Commande non reconnue");
	}

}
