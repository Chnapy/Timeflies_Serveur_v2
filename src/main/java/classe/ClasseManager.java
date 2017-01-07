/*
 * 
 * 
 * 
 */
package classe;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClasseManager.java
 *
 */
public class ClasseManager {

	private static final ModeleClasseManager MODELE = new ModeleClasseManager();

	private static final HashMap<Class<? extends ClasseEnvoutement>, ClasseEnvoutement> ENVOUTEMENTS = new HashMap();
	private static final HashMap<Class<? extends ClasseSort>, ClasseSort> SORTS = new HashMap();
	private static final HashMap<Long, ClasseEntite> ENTITES = new HashMap();

	public static void start() {
		chargerEnvoutements();
		chargerSorts();
		chargerEntites();
	}

	private static void chargerEnvoutements() {
		MODELE.getAllEnvoutements().forEach(e -> ENVOUTEMENTS.put(e.getClass(), e));
	}

	private static void chargerSorts() {
		MODELE.getAllSorts().forEach(s -> SORTS.put(s.getClass(), s));
	}

	private static void chargerEntites() {
		MODELE.getAllEntites().forEach(e -> ENTITES.put(e.getId(), e));
	}

	public static <C extends ClasseEnvoutement> C getEnvoutementFromClass(Class<C> c) {
		return (C) ENVOUTEMENTS.get(c);
	}

	public static <C extends ClasseSort> C getSortFromClass(Class<C> c) {
		return (C) SORTS.get(c);
	}

	public static ClasseSort getSortFromId(Long idClasseSort) {
		return SORTS.values().stream()
				.filter((s) -> s.getId() == idClasseSort)
				.findFirst().get();
	}

	public static <C extends ClasseEntite> C getEntiteFromId(long id) throws IllegalArgumentException {
		ClasseEntite ce = ENTITES.get(id);
		if (ce == null) {
			throw new IllegalArgumentException("ID inexistant dans la map : " + id);
		}
		return (C) ce;
	}

	public static Map<Long, ClasseEnvoutement> getEnvoutements() {
		return ENVOUTEMENTS.values().stream()
				.collect(Collectors.toMap(
						ce -> ce.getId(),
						ce -> ce
				));
	}

	public static Map<Long, ClasseSort> getSorts() {
		return SORTS.values().stream()
				.collect(Collectors.toMap(
						ce -> ce.getId(),
						ce -> ce
				));
	}

	public static Map<Long, ClasseEntite> getEntites() {
		return ENTITES;
	}

	private ClasseManager() {
	}

}
