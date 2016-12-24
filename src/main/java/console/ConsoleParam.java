/*
 * 
 * 
 * 
 */
package console;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ConsoleParam.java
 *
 */
public class ConsoleParam {

	private final String commande;
	private final String valeur;

	public ConsoleParam(String commande, String valeur) {
		this.commande = commande;
		this.valeur = valeur;
	}

	public ConsoleParam(String commande) {
		this(commande, null);
	}

	public String getCommande() {
		return this.commande;
	}

	public String getValeur() {
		return valeur;
	}

	@Override
	public String toString() {

		String str_main = "[-" + this.commande;

		if (this.valeur != null) {
			str_main += " " + this.valeur;
		}

		return str_main + "]";
	}

}
