/*
 * 
 * 
 * 
 */
package classe;

import BDD.BDD;
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
import netserv.Modele;

/**
 * ModeleClasseManager.java
 *
 */
public class ModeleClasseManager extends Modele {

	private static final String QUERY_GET_ENVOUTEMENTS = "SELECT * FROM classeenvoutement",
			
			QUERY_GET_SORTS = "SELECT * FROM classesort NATURAL JOIN typexp",
			QUERY_GET_SORTSCPHYSIQUE = "SELECT * FROM relcphysiqueclassesort NATURAL JOIN cphysique WHERE id_classesort=?",
			
			QUERY_GET_ENTITES = "SELECT * FROM classeentite NATURAL JOIN typexp WHERE supprime=false",
			QUERY_GET_ENTITESCPHYSIQUE = "SELECT * FROM relcphysiqueclasseentite NATURAL JOIN cphysique "
			+ "WHERE id_classeentite=?",
			QUERY_GET_ENTITESSORTS = "SELECT id_classesort FROM relclasseentiteclassesort WHERE id_classeentite=? AND supprime=false";

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
				ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				
				long idsort = rs.getLong("id_classesort");
				
				st_cp.setLong(1, idsort);
				
				Map<Integer, Integer> cp = new HashMap();
				
				try (ResultSet rs_cp = st_cp.executeQuery()) {
					while(rs_cp.next()) {
						cp.put(rs_cp.getInt("id_typecphysique"), rs_cp.getInt("valeur"));
					}
				}

				try {
					sorts.add(getSortFromBD(
							idsort,
							rs.getString("javaclass"),
							rs.getInt("xpparuse"),
							rs.getInt("pourcdegressif"),
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

		return sorts;
	}

	private ClasseSort getSortFromBD(long id, String javaclasse, int xpParUse, int pourcDegressifXP, Map<Integer, Integer> cp, int idFonctionXp, int influencexp) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Class<?> classe = Class.forName(javaclasse);

		Map<TypeCPhysique, Integer> cphysique = new HashMap();
		cp.forEach((tc, v) -> cphysique.put(TypeCPhysique.getFromId(tc), v));

		ClasseXP cxp = new ClasseXP(FonctionXP.getFromId(idFonctionXp), influencexp);

		return (ClasseSort) classe.getConstructors()[0].newInstance(id, cphysique, cxp, xpParUse, pourcDegressifXP);
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
					while(rs_cp.next()) {
						cp.put(rs_cp.getInt("id_typecphysique"), rs_cp.getInt("valeur"));
					}
				}
				
				st_s.setLong(1, identite);
				
				Set<Long> idsorts = new HashSet();
				
				try(ResultSet rs_s = st_s.executeQuery()) {
					while(rs_s.next()) {
						idsorts.add(rs_s.getLong("id_classesort"));
					}
				}

				try {
					entites.add(getEntiteFromBD(
							identite,
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
	
	private ClasseEntite getEntiteFromBD(long id, String javaclasse, Set<Long> idsorts, Map<Integer, Integer> cp, int idFonctionXp, int influencexp) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<?> classe = Class.forName(javaclasse);
		
		Set<ClasseSort> sorts = idsorts.stream()
				.map(ids -> ClasseManager.getSortFromId(ids))
				.collect(Collectors.toSet());

		Map<TypeCPhysique, Integer> cphysique = new HashMap();
		cp.forEach((tc, v) -> cphysique.put(TypeCPhysique.getFromId(tc), v));

		ClasseXP cxp = new ClasseXP(FonctionXP.getFromId(idFonctionXp), influencexp);
		
		return (ClasseEntite) classe.getConstructors()[0].newInstance(id, sorts, cxp, cphysique);
	}

}
