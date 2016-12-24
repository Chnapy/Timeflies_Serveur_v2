/*
 * 
 * 
 * 
 */
package console;

import console.cmd.ConsoleDefautCmd;
import console.cmd.ConsoleThreadCmd;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 * ConsoleRunnable.java
 *
 */
public class ConsoleRunnable implements Runnable {

	private final Scanner sc;
	private final ConsoleDisplayer displayer;
	private final ConsoleDefautCmd defautCmd;
	private final Map<String, ConsoleCmd> commandes;
	private boolean run;

	public ConsoleRunnable(Map<String, ConsoleCmd> commandes) {
		this.run = false;
		this.sc = new Scanner(System.in);
		this.displayer = new ConsoleDisplayer();
		this.defautCmd = new ConsoleDefautCmd();
		this.commandes = commandes;
	}

	@Override
	public void run() {
		this.run = true;

		while (this.run) {
			try {
				this.iteration();
			} catch (Throwable ex) {
				Logger.getLogger(ConsoleRunnable.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private void iteration() throws Throwable {
		String entree = this.getEntree();
		String[] split = entree.split(" ");
		String str_cmd = split[0];
		Pair<List<ConsoleParam>, List<String>> items = getCommandeItems(split);
		ConsoleCmd commande = this.commandes.getOrDefault(str_cmd, defautCmd);
		commande.lancer(sc, displayer, items.getValue(), items.getKey());
	}

	private String getEntree() {
		String entree = null;
		while ((entree == null || entree.trim().isEmpty()) && this.run) {
			try {
				entree = this.sc.nextLine();
			} catch (Exception e) {
				displayer.printErr("Entrée incorrecte");
			}
		}
		return entree;
	}

	public void stop() {
		this.run = false;
		this.commandes.get(ConsoleThreadCmd.CMD).lancer(sc, displayer);
		this.sc.close();
	}

	private static Pair<List<ConsoleParam>, List<String>> getCommandeItems(String[] arr) {
		List<ConsoleParam> params = new ArrayList();
		List<String> valeurs = new ArrayList();
		String cactu = null;
		String s;
		
		for (int i = 1; i < arr.length; i++) {
			s = arr[i].trim();

			if (s.isEmpty()) {
				continue;
			}

			if (s.startsWith("-")) {
				if (cactu != null) {
					params.add(new ConsoleParam(cactu));
				}
				cactu = s.substring(1);
			} else {
				if (cactu != null) {
					params.add(new ConsoleParam(cactu, s));
					cactu = null;
				} else {
					valeurs.add(s);
				}
			}

		}
		
		if (cactu != null) {
			params.add(new ConsoleParam(cactu));
		}

		return new Pair(params, valeurs);
	}

}
