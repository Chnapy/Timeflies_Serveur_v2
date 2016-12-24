/*
 * 
 * 
 * 
 */
package console.cmd;

import console.Console;
import console.ConsoleCmd;
import console.ConsoleDisplayer;
import java.util.Scanner;

/**
 * ConsoleHelpCmd.java
 * 
 */
public class ConsoleHelpCmd extends ConsoleCmd {
	
	private static final String COMMANDE = "help";
	
	private final Console console;

	public ConsoleHelpCmd(Console console) {
		super(COMMANDE);
		this.console = console;
	}

	@Override
	protected void lancer(Scanner sc, ConsoleDisplayer displayer) {
		String str = "";
		for(ConsoleCmd c : this.console.getCommandes().values()) {
			str += c + "\n";
		}
		displayer.printInfo(str);
	}

}
