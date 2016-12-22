/*
 * 
 * 
 * 
 */
package main;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import outils.json.MyJSONParser;

/**
 * Const.java
 *
 */
public final class Const {

	//Chemin du fichier contenant les constantes
	private static final String CONST_PATH = "constantes.json";

	//Port serveur
	public static final int SERVEUR_PORT;

	//Path
	public static final String PATH_BDD_INFOS, PATH_LOGS, PATH_MAPS;

	//Longueur min et max des entrées utilisateur
	public static final int PSEUDO_LENGTH_MIN, PSEUDO_LENGTH_MAX,
			MDP_LENGTH_MIN, MDP_LENGTH_MAX,
			NOMPERSO_LENGTH_MIN, NOMPERSO_LENGTH_MAX;

	//Management perso
	public static final int NBR_MANAG_PERSOS_MAX;

	//Salon
	public static final int NBR_SALON_EQUIPE_MAX, NBR_SALON_PERSOS_EQUIPE_MAX;

	//Chronos
	public static final int CHRONO_SALON, CHRONO_COMBAT;

	static {
		JsonNode root = getJSON();
		SERVEUR_PORT = root.at("/serveur/port").intValue();

		PATH_BDD_INFOS = root.at("/path/bdd_infos").textValue();
		PATH_LOGS = root.at("/path/logs").textValue();
		PATH_MAPS = root.at("/path/maps").textValue();

		PSEUDO_LENGTH_MIN = root.at("/connexion/pseudo/min").intValue();
		PSEUDO_LENGTH_MAX = root.at("/connexion/pseudo/max").intValue();
		MDP_LENGTH_MIN = root.at("/connexion/mdp/min").intValue();
		MDP_LENGTH_MAX = root.at("/connexion/mdp/max").intValue();
		NOMPERSO_LENGTH_MIN = root.at("/managperso/nom_perso/min").intValue();
		NOMPERSO_LENGTH_MAX = root.at("/managperso/nom_perso/max").intValue();

		NBR_MANAG_PERSOS_MAX = root.at("/managperso/nbr_persos_max").intValue();

		NBR_SALON_EQUIPE_MAX = root.at("/salon/nbr_equipe_max").intValue();
		NBR_SALON_PERSOS_EQUIPE_MAX = root.at("/salon/nbr_persos_equipe").intValue();

		CHRONO_SALON = root.at("/salon/chrono_lancement").intValue();
		CHRONO_COMBAT = root.at("/combat/chrono_lancement").intValue();
	}

	private static JsonNode getJSON() {
		try {
			return MyJSONParser.getJSON(CONST_PATH);
		} catch (IOException ex) {
			Logger.getLogger(Const.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	private Const() {
	}

}
