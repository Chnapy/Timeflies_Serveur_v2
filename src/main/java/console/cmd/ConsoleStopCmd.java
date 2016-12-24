/*
 * 
 * 
 * 
 */
package console.cmd;

import console.ConsoleCmd;
import console.ConsoleDisplayer;
import java.util.Scanner;
import main.ServeurManager;

/**
 * ConsoleStopCmd.java
 * 
 */
public class ConsoleStopCmd extends ConsoleCmd {
	
	private static final String CMD = "stop";
	
	private final ServeurManager serveur;

	public ConsoleStopCmd(ServeurManager serveur) {
		super("stop");
		this.serveur = serveur;
	}

	@Override
	protected void lancer(Scanner sc, ConsoleDisplayer displayer) {
		this.serveur.stop();
	}

}
