/*
 * 
 * 
 * 
 */
package classe;

import BDD.BDD;
import classe.zone.CoucheZone;
import classe.zone.TypeZone;
import classe.zone.Zone;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import main.Const;
import netserv.Modele;
import outils.json.MyJSONParser;
import salon.ClasseMap;

/**
 * ModeleClasseManager.java
 *
 */
public class ModeleClasseManager extends Modele {

	private static final String QUERY_GET_ENVOUTEMENTS = "SELECT * FROM classeenvoutement",
			QUERY_GET_SORTS = "SELECT * FROM classesort NATURAL JOIN typexp",
			QUERY_GET_SORTSCPHYSIQUE = "SELECT * FROM relcphysiqueclassesort NATURAL JOIN cphysique WHERE id_classesort=?",
			QUERY_GET_SORTSZONE = "SELECT * FROM relclassesortzone NATURAL JOIN zone WHERE id_classesort=?",
			QUERY_GET_SORTSENV = "SELECT * FROM relclassesortclasseenvoutement WHERE id_classesort=? AND supprime=false",
			QUERY_GET_ENTITES = "SELECT * FROM classeentite NATURAL JOIN typexp WHERE supprime=false",
			QUERY_GET_ENTITESCPHYSIQUE = "SELECT * FROM relcphysiqueclasseentite NATURAL JOIN cphysique "
			+ "WHERE id_classeentite=?",
			QUERY_GET_ENTITESSORTS = "SELECT id_classesort FROM relclasseentiteclassesort WHERE id_classeentite=? AND supprime=false";

	private static final String QUERY_GET_MAP = "SELECT chemin FROM map WHERE id_map=?";

	public Set<ClasseEnvoutement> getAllEnvoutements() {

		Set<ClasseEnvoutement> envoutements = new HashSet();

		try (Connection connection = BDD.getConnection();
				PreparedStatement st = connection.prepareStatement(QUERY_GET_ENVOUTEMENTS);
				ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				try {
					envoutements.add(getEnvoutementFromBD(
							rs.getLong("id_classeenvoutement"),
							rs.getString("javaclass"),
							rs.getInt("nbr_tours")
					));
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
					Logger.getLogger(ModeleClasseManager.class.getName()).log(Level.SEVERE, null, ex);
				}

			}

			connection.commit();
		} catch (SQLException ex) {
			Logger.getLogger(ModeleClasseManager.class.getName()).log(Level.SEVERE, null, ex);
		}

		return envoutements;
	}

	private ClasseEnvoutement getEnvoutementFromBD(long id, String javaclasse, int nbrTours) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Class<?> classe = Class.forName(javaclasse);
		return (ClasseEnvoutement) classe.getConstructors()[0].newInstance(id, nbrTours);
	}

	public Set<ClasseSort> getAllSorts() {

		Set<ClasseSort> sorts = new HashSet();

		try (Connection connection = BDD.getConnection();
				PreparedStatement st = connection.prepareStatement(QUERY_GET_SORTS);
				PreparedStatement st_cp = connection.prepareStatement(QUERY_GET_SORTSCPHYSIQUE);
				PreparedStatement st_z = connection.prepareStatement(QUERY_GET_SORTSZONE);
				PreparedStatement st_e = connection.prepareStatement(QUERY_GET_SORTSENV);
				ResultSet rs = st.executeQuery()) {
			while (rs.next()) {

				long idsort = rs.getLong("id_classesort");

				st_cp.setLong(1, idsort);
				st_z.setLong(1, idsort);
				st_e.setLong(1, idsort);

				Map<Integer, Integer> cp = new HashMap();

				try (ResultSet rs_cp = st_cp.executeQuery()) {
					while (rs_cp.next()) {
						cp.put(rs_cp.getInt("id_typecphysique"), rs_cp.getInt("valeur"));
					}
				}

				Set<CoucheZone> czonePortee = new HashSet();
				Set<CoucheZone> czoneAction = new HashSet();

				try (ResultSet rs_z = st_z.executeQuery()) {
					while (rs_z.next()) {
						CoucheZone cz = new CoucheZone(TypeZone.getFromId(rs_z.getInt("id_typezone")),
								rs_z.getBoolean("positif"), rs_z.getInt("valeur"));
						if (rs_z.getBoolean("portee")) {
							czonePortee.add(cz);
						} else {
							czoneAction.add(cz);
						}
					}
				}

				Set<Long> idenvoutements = new HashSet();

				try (ResultSet rs_e = st_e.executeQuery()) {
					while (rs_e.next()) {
						idenvoutements.add(rs_e.getLong("id_classeenvoutement"));
					}
				}

				try {
					sorts.add(getSortFromBD(
							idsort,
							rs.getBoolean("actif"),
							rs.getString("javaclass"),
							rs.getInt("xpparuse"),
							rs.getInt("pourcdegressif"),
							cp,
							rs.getInt("id_fonctionxp"),
							rs.getInt("influencexp"),
							czonePortee,
							czoneAction,
							idenvoutements
					));
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
					Logger.getLogger(ModeleClasseManager.class.getName()).log(Level.SEVERE, null, ex);
				}

			}

			connection.commit();
		} catch (SQLException ex) {
			Logger.getLogger(ModeleClasseManager.class.getName()).log(Level.SEVERE, null, ex);
		}

		return sorts;
	}

	private ClasseSort getSortFromBD(long id, boolean actif, String javaclasse, int xpParUse, int pourcDegressifXP, Map<Integer, Integer> cp, int idFonctionXp, int influencexp, Set<CoucheZone> czonePortee, Set<CoucheZone> czoneAction, Set<Long> idenvoutements) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Class<?> classe = Class.forName(javaclasse);

		Map<TypeCPhysique, Integer> cphysique = new HashMap();
		cp.forEach((tc, v) -> cphysique.put(TypeCPhysique.getFromId(tc), v));

		ClasseXP cxp = new ClasseXP(FonctionXP.getFromId(idFonctionXp), influencexp);

		Zone zonePortee = new Zone(czonePortee), zoneAction = new Zone(czoneAction);

		Set<ClasseEnvoutement> envoutements = idenvoutements.stream()
				.map(ide -> ClasseManager.getEnvoutementFromId(ide))
				.collect(Collectors.toSet());

		return (ClasseSort) classe.getConstructors()[0].newInstance(id, actif,
				cphysique, cxp, xpParUse, pourcDegressifXP, zonePortee, zoneAction, envoutements);
	}

	public Set<ClasseEntite> getAllEntites() {

		Set<ClasseEntite> entites = new HashSet();

		try (Connection connection = BDD.getConnection();
				PreparedStatement st = connection.prepareStatement(QUERY_GET_ENTITES);
				PreparedStatement st_cp = connection.prepareStatement(QUERY_GET_ENTITESCPHYSIQUE);
				PreparedStatement st_s = connection.prepareStatement(QUERY_GET_ENTITESSORTS);
				ResultSet rs = st.executeQuery()) {
			while (rs.next()) {

				long identite = rs.getLong("id_classeentite");

				st_cp.setLong(1, identite);

				Map<Integer, Integer> cp = new HashMap();

				try (ResultSet rs_cp = st_cp.executeQuery()) {
					while (rs_cp.next()) {
						cp.put(rs_cp.getInt("id_typecphysique"), rs_cp.getInt("valeur"));
					}
				}

				st_s.setLong(1, identite);

				Set<Long> idsorts = new HashSet();

				try (ResultSet rs_s = st_s.executeQuery()) {
					while (rs_s.next()) {
						idsorts.add(rs_s.getLong("id_classesort"));
					}
				}

				try {
					entites.add(getEntiteFromBD(
							identite,
							rs.getBoolean("actif"),
							rs.getString("javaclass"),
							idsorts,
							cp,
							rs.getInt("id_fonctionxp"),
							rs.getInt("influencexp")
					));
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
					Logger.getLogger(ModeleClasseManager.class.getName()).log(Level.SEVERE, null, ex);
				}

			}

			connection.commit();
		} catch (SQLException ex) {
			Logger.getLogger(ModeleClasseManager.class.getName()).log(Level.SEVERE, null, ex);
		}

		return entites;
	}

	private ClasseEntite getEntiteFromBD(long id, boolean actif, String javaclasse, Set<Long> idsorts, Map<Integer, Integer> cp, int idFonctionXp, int influencexp) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Class<?> classe = Class.forName(javaclasse);

		Set<ClasseSort> sorts = idsorts.stream()
				.map(ids -> ClasseManager.getSortFromId(ids))
				.collect(Collectors.toSet());

		Map<TypeCPhysique, Integer> cphysique = new HashMap();
		cp.forEach((tc, v) -> cphysique.put(TypeCPhysique.getFromId(tc), v));

		ClasseXP cxp = new ClasseXP(FonctionXP.getFromId(idFonctionXp), influencexp);

		return (ClasseEntite) classe.getConstructors()[0].newInstance(id, actif, sorts, cxp, cphysique);
	}

	public ClasseMap getClasseMap(int idmap) throws IllegalArgumentException {

		String chemin;

		try (Connection connection = BDD.getConnection();
				PreparedStatement st = connection.prepareStatement(QUERY_GET_MAP)) {

			st.setInt(1, idmap);

			try (ResultSet rs = st.executeQuery()) {
				if (!rs.next()) {
					throw new IllegalArgumentException("idmap : " + idmap);
				}

				chemin = rs.getString("chemin");
			}

			connection.commit();
		} catch (SQLException ex) {
			Logger.getLogger(ModeleClasseManager.class.getName()).log(Level.SEVERE, null, ex);
			throw new Error(ex);
		}

		chemin = Const.PATH_MAPS + chemin;

		try {
			return getClasseFromFile(chemin, idmap);
		} catch (IOException ex) {
			Logger.getLogger(ModeleClasseManager.class.getName()).log(Level.SEVERE, null, ex);
			throw new Error(ex);
		}

	}
	
	private ClasseMap getClasseFromFile(String path, int idmap) throws IOException {
		ClasseMap cm = MyJSONParser.fileToObject(new File(path), ClasseMap.class);
		cm.setChemin(path);
		cm.setIdmap(idmap);
		
		System.out.println(cm);
		
		return cm;
	}

}
