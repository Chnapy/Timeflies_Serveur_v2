/*
 * 
 * 
 * 
 */
package connexion.modele;

import BDD.BDD;
import client.Personnage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import netserv.Modele;
import outils.crypt.crypto.SCryptUtil;

/**
 * ModeleConnexion.java
 *
 */
public class ModeleConnexion extends Modele {

	private static final String QUERY_GET_MDP_SALT = "SELECT id_joueur, pseudo, mail, mdp, salt_mdp "
			+ "FROM joueur NATURAL JOIN infoscompte "
			+ "WHERE (pseudo=? OR mail=?) AND supprime=false";

	private static final String QUERY_GET_PERSOS = "SELECT id_personnage, id_classeentite, nomdonne "
			+ "FROM personnage "
			+ "WHERE id_joueur=? AND supprime=false";

	private static final String QUERY_GET_SORTXP = "SELECT id_classesort, xp "
			+ "FROM sortxp "
			+ "WHERE id_personnage=?";

	private static final String QUERY_GET_STATS_COMBAT = "SELECT COUNT(tot.*) as total, "
			+ "COUNT(gag.*) as gagne "
			+ "FROM (SELECT DISTINCT id_combat FROM participantcombat NATURAL JOIN personnage WHERE id_joueur=?) AS tot, "
			+ "(SELECT DISTINCT id_combat FROM participantcombat NATURAL JOIN personnage WHERE id_joueur=? AND gagne=true) AS gag";

	public Optional<Map<String, Object>> checkConnexion(String pseudoOUmail, String mdp) {
		try (Connection connection = BDD.getConnection();
				PreparedStatement st = connection.prepareStatement(QUERY_GET_MDP_SALT)) {

			st.setString(1, pseudoOUmail);
			st.setString(2, pseudoOUmail);

			try (ResultSet rs = st.executeQuery()) {

				if (!rs.next()) {
					connection.rollback();
					return Optional.empty();
				}

				String BDmdp = rs.getString("mdp");
				String BDsalt = rs.getString("salt_mdp");
				String mdpCrypt = this.mdpCrypt(mdp, BDsalt);

				if (!BDmdp.equals(mdpCrypt)) {
					connection.rollback();
					return Optional.empty();
				}

				Map<String, Object> ret = new HashMap();

				ret.put("id_joueur", rs.getLong("id_joueur"));
				ret.put("pseudo", rs.getString("pseudo"));
				ret.put("mail", rs.getString("mail"));

				connection.commit();
				return Optional.of(ret);
			}

		} catch (SQLException ex) {
			Logger.getLogger(ModeleConnexion.class.getName()).log(Level.SEVERE, null, ex);
		}

		return Optional.empty();
	}

	private String mdpCrypt(String mdp, String str_salt) {
		byte[] salt = saltFromString(str_salt);
		return SCryptUtil.scrypt(mdp, salt);
	}

	private byte[] saltFromString(String str_salt) {
		String[] ss = str_salt.split("\\.");
		byte[] ret = new byte[ss.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = Byte.valueOf(ss[i]);
		}
		return ret;
	}

	public Set<Personnage> getAllPersonnagesClient(long idJoueur) {
		Set<Personnage> liste = new HashSet();
		try (Connection connection = BDD.getConnection();
				PreparedStatement st_perso = connection.prepareStatement(QUERY_GET_PERSOS);
				PreparedStatement st_sort = connection.prepareStatement(QUERY_GET_SORTXP)) {

			st_perso.setLong(1, idJoueur);

			try (ResultSet rsP = st_perso.executeQuery()) {
				while (rsP.next()) {

					long idPerso = rsP.getLong("id_personnage");
					long idClasseEntite = rsP.getLong("id_classeentite");
					String nom = rsP.getString("nomdonne");

					Map<Long, Integer> mapSXP = new HashMap();

					st_sort.setLong(1, idPerso);
					try (ResultSet rsS = st_sort.executeQuery()) {
						while (rsS.next()) {

							long idClasseSort = rsS.getLong("id_classesort");
							int xpSort = rsS.getInt("xp");
							mapSXP.put(idClasseSort, xpSort);
						}
					}

					Personnage p = new Personnage(idPerso, nom, idClasseEntite, mapSXP);

					liste.add(p);
				}
			}
			
			connection.commit();
		} catch (SQLException ex) {
			Logger.getLogger(ModeleConnexion.class.getName()).log(Level.SEVERE, null, ex);
		}
		return liste;
	}

	public HashMap<String, Integer> getStatsCombat(long idJoueur) {
		HashMap<String, Integer> map = new HashMap();
		try (Connection connection = BDD.getConnection();
				PreparedStatement st = connection.prepareStatement(QUERY_GET_STATS_COMBAT)) {

			st.setLong(1, idJoueur);
			st.setLong(2, idJoueur);

			try (ResultSet rs = st.executeQuery()) {
				rs.next();
				
				int total = rs.getInt("total");
				int gagne = rs.getInt("gagne");
				int perdu = total - gagne;
				
				map.put("total", total);
				map.put("gagne", gagne);
				map.put("perdu", perdu);
			}
			
			connection.commit();
		} catch (SQLException ex) {
			Logger.getLogger(ModeleConnexion.class.getName()).log(Level.SEVERE, null, ex);
		}
		return map;
	}

}
