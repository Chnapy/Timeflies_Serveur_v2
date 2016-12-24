/*
 * 
 * 
 * 
 */
package console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * ConsoleCmd.java
 *
 */
public abstract class ConsoleCmd {

	private final String commande;
	private final List<String> valeurs;
	private final Map<String, ConsoleParam> params;

	public ConsoleCmd(String commande) {
		this.commande = commande;
		this.valeurs = new ArrayList();
		this.params = new HashMap();
	}

	public void lancer(Scanner sc, ConsoleDisplayer displayer, List<String> valeurs, List<ConsoleParam> params) {
		valeurs.forEach((v) -> {
			this.valeurs.add(v);
		});
		params.forEach((c) -> {
			this.params.put(c.getCommande(), c);
		});
		this.lancer(sc, displayer);
//		System.out.println(this);
		this.reset();
	}

	protected void reset() {
		this.valeurs.clear();
		this.params.clear();
	}

	protected abstract void lancer(Scanner sc, ConsoleDisplayer displayer);

	public String getCommande() {
		return this.commande;
	}

	@Override
	public String toString() {

		String str_params = "";

		for (ConsoleParam p : this.params.values()) {
			str_params += " [-" + p.getCommande() + " " + p.getValeur() + "]";
		}
		for (String s : this.valeurs) {
			str_params += " " + s;
		}

		return this.commande + str_params;
	}

}
