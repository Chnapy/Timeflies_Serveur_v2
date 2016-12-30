/*
 * 
 * 
 * 
 */
package general.modele;

import BDD.BDD;
import client.Personnage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.util.Pair;
import netserv.Modele;

/**
 * ModeleGeneral.java
 *
 */
public class ModeleGeneral extends Modele {

	private static final String QUERY_CREER_PERSO = "INSERT INTO personnage(id_classeentite, nomdonne, id_joueur) "
			+ "VALUES(?, ?, ?)";

	private static final String QUERY_GET_ID_SORT = "SELECT id_classesort "
			+ "FROM classesort NATURAL JOIN relclasseentiteclassesort "
			+ "WHERE id_classeentite=?";

	private static final String QUERY_INSERT_XPSORT = "INSERT INTO sortxp(id_personnage, id_classesort, xp) "
			+ "VALUES(?, ?, 0)";

	public Personnage creerPerso(long idJoueur, int idClasseEntite, String nom) {
		try {

			Pair<Long, Set<Long>> infosPerso = this.creerPersoToBD(idJoueur, idClasseEntite, nom);

			return getNewPerso(infosPerso.getKey(), idJoueur, idClasseEntite, nom, infosPerso.getValue());

		} catch (SQLException ex) {
			Logger.getLogger(ModeleGeneral.class.getName()).log(Level.SEVERE, null, ex);
			throw new Error();
		}
	}

	private Personnage getNewPerso(long idPerso, long idJoueur, int idClasseEntite, String nom, Set<Long> listeSorts) {
		System.out.println(listeSorts);
		Map<Long, Integer> sortXP = listeSorts.stream().collect(Collectors.toMap(
				l -> l, l -> 0
		));
		return new Personnage(idPerso, nom, idClasseEntite, sortXP);

	}

	private Pair<Long, Set<Long>> creerPersoToBD(long idJoueur, int idClasseEntite, String nom) throws SQLException {

		long idPerso;
		Set<Long> idSorts = new HashSet();

		try (Connection connection = BDD.getConnection();
				PreparedStatement st_insert_perso = connection.prepareStatement(QUERY_CREER_PERSO, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement st_get_sort = connection.prepareStatement(QUERY_GET_ID_SORT);
				PreparedStatement st_insert_xpsort = connection.prepareStatement(QUERY_INSERT_XPSORT)) {

			st_insert_perso.setInt(1, idClasseEntite);
			st_insert_perso.setString(2, nom);
			st_insert_perso.setLong(3, idJoueur);

			int affectedRows = st_insert_perso.executeUpdate();
			System.out.println("insert creaperso (lignes) : " + affectedRows);

			if (affectedRows == 0) {
				throw new SQLException("Insert: aucune ligne affectée");
			}

			try (ResultSet rs = st_insert_perso.getGeneratedKeys()) {
				if (rs.next()) {
					idPerso = rs.getLong(1);
				} else {
					throw new SQLException("creaperso: aucun ID trouvé");
				}
			}

			st_get_sort.setLong(1, idClasseEntite);

			try (ResultSet rs = st_get_sort.executeQuery()) {
				while (rs.next()) {

					long idClasseSort = rs.getLong("id_classesort");
					
					System.out.println("ID:"+idClasseSort);
					idSorts.add(idClasseSort);

					st_insert_xpsort.setLong(1, idPerso);
					st_insert_xpsort.setLong(2, idClasseSort);

					st_insert_xpsort.addBatch();

				}
				st_insert_xpsort.executeBatch();
			}

			connection.commit();
		}

		return new Pair(idPerso, idSorts);
	}

}
