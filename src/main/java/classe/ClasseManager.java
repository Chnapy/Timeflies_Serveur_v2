/*
 * 
 * 
 * 
 */
package classe;

import java.util.HashMap;
import java.util.Map;

/**
 * ClasseManager.java
 *
 */
public class ClasseManager {

	private static final ModeleClasseManager MODELE = new ModeleClasseManager();

	private static final HashMap<Long, ClasseEnvoutement> ENVOUTEMENTS = new HashMap();
	private static final HashMap<Long, ClasseSort> SORTS = new HashMap();
	private static final HashMap<Long, ClasseEntite> ENTITES = new HashMap();

	public static void start() {
		chargerEnvoutements();
		chargerSorts();
		chargerEntites();
	}

	private static void chargerEnvoutements() {
		MODELE.getAllEnvoutements().forEach(e -> ENVOUTEMENTS.put(e.getId(), e));
	}

	private static void chargerSorts() {
		MODELE.getAllSorts().forEach(s -> SORTS.put(s.getId(), s));
	}

	private static void chargerEntites() {
		MODELE.getAllEntites().forEach(e -> ENTITES.put(e.getId(), e));
	}

	public static ClasseEnvoutement getEnvoutementFromId(Long idClasseEnvoutement) {
		ClasseEnvoutement ce = ENVOUTEMENTS.get(idClasseEnvoutement);
		if (ce == null) {
			throw new IllegalArgumentException("id : " + idClasseEnvoutement);
		}
		return ce;
	}

	public static ClasseSort getSortFromId(Long idClasseSort) {
		ClasseSort cs = SORTS.get(idClasseSort);
		if (cs == null) {
			throw new IllegalArgumentException("id : " + idClasseSort);
		}
		return cs;
	}

	public static <C extends ClasseEntite> C getEntiteFromId(long id) throws IllegalArgumentException {
		ClasseEntite ce = ENTITES.get(id);
		if (ce == null) {
			throw new IllegalArgumentException("ID inexistant dans la map : " + id);
		}
		return (C) ce;
	}

	public static Map<Long, ClasseEnvoutement> getEnvoutements() {
		return ENVOUTEMENTS;
	}

	public static Map<Long, ClasseSort> getSorts() {
		return SORTS;
	}

	public static Map<Long, ClasseEntite> getEntites() {
		return ENTITES;
	}

	private ClasseManager() {
	}

}
