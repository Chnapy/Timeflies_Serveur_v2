/*
 * 
 * 
 * 
 */
package classe;

import classe.finale.TestEntite1;
import classe.finale.TestEnvoutement1;
import classe.finale.TestSortPassif1;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.util.Pair;
import netserv.Compressed;

/**
 * ClasseManager.java
 *
 */
public class ClasseManager {

	private static final Class<? extends ClasseEnvoutement>[] C_ENVOUTEMENTS = new Class[]{
		TestEnvoutement1.class
	};

	private static final Class<? extends ClasseSort>[] C_SORTS = new Class[]{
		TestSortPassif1.class
	};

	private static final Pair<Class<? extends ClasseEntite>, Long>[] C_ENTITES = new Pair[]{
		new Pair(TestEntite1.class, TestEntite1.ID)
	};

	private static final HashMap<Class<? extends ClasseEnvoutement>, ClasseEnvoutement> ENVOUTEMENTS = new HashMap();
	private static final HashMap<Class<? extends ClasseSort>, ClasseSort> SORTS = new HashMap();
	private static final HashMap<Long, ClasseEntite> ENTITES = new HashMap();

	public static void start() {
		try {
			chargerEnvoutements();
			chargerSorts();
			chargerEntites();
		} catch (InstantiationException | IllegalAccessException ex) {
			Logger.getLogger(ClasseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private static void chargerEnvoutements() throws InstantiationException, IllegalAccessException {
		for (Class<? extends ClasseEnvoutement> c : C_ENVOUTEMENTS) {
			ENVOUTEMENTS.put(c, c.newInstance());
		}
	}

	private static void chargerSorts() throws IllegalAccessException, InstantiationException {
		for (Class<? extends ClasseSort> c : C_SORTS) {
			SORTS.put(c, c.newInstance());
		}
	}

	private static void chargerEntites() throws IllegalAccessException, InstantiationException {
		for (Pair<Class<? extends ClasseEntite>, Long> p : C_ENTITES) {
			ENTITES.put(p.getValue(), p.getKey().newInstance());
		}
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

	public static <C extends ClasseEntite> C getEntiteFromId(long id) {
		return (C) ENTITES.get(id);
	}

	public static Map<Long, Compressed> getCompressedEnvoutements() {
		return ENVOUTEMENTS.values().stream()
				.collect(Collectors.toMap(
						ce -> ce.getId(),
						ce -> ce.getCompressed()
				));
	}

	public static Map<Long, Compressed> getCompressedSorts() {
		return SORTS.values().stream()
				.collect(Collectors.toMap(
						ce -> ce.getId(),
						ce -> ce.getCompressed()
				));
	}

	public static Map<Long, Compressed> getCompressedEntites() {
		return ENTITES.values().stream()
				.collect(Collectors.toMap(
						ce -> ce.getId(),
						ce -> ce.getCompressed()
				));
	}

	private ClasseManager() {
	}

}
