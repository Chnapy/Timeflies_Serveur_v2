/*
 * 
 * 
 * 
 */
package salon;

import classe.ClasseEntite;
import client.Client;
import client.Personnage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * SalonEquipe.java
 *
 */
public class SalonEquipe extends HashSet<Personnage>  {

	private final int id;

	public SalonEquipe(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void removeClient(Client c) {
		List<Personnage> toRemove = new ArrayList();
		this.forEach(p -> {
			if(p.getClient().equals(c)) {
				toRemove.add(p);
			}
		});
		this.removeAll(toRemove);
	}
	
	public Set<Personnage> getPersosFromClient(Client c) {
		return this.stream().filter(p -> p.getClient().equals(c)).collect(Collectors.toSet());
	}

	public int getNbrMaxMemeClasse() {
		HashMap<ClasseEntite, Integer> nbrClasses = new HashMap();
		this.forEach(p -> nbrClasses.merge(p.getClasse(), 1, (t, u) -> t + 1));
		return Collections.max(nbrClasses.values());
	}

	public int getNbrMaxMemeClient() {
		HashMap<Client, Integer> nbrClasses = new HashMap();
		this.forEach(p -> nbrClasses.merge(p.getClient(), 1, (t, u) -> t + 1));
		return Collections.max(nbrClasses.values());
	}

//	@Override
//	public Compressed getCompressed() {
//		return new SalonEquipeCompressed(this);
//	}
//
//	public class SalonEquipeCompressed implements Compressed {
//
//		private final int id;
//		private final Set<Compressed> persos;
//
//		public SalonEquipeCompressed(SalonEquipe se) {
//			id = se.getId();
//			persos = se.stream().map(p -> p.getCompressed()).collect(Collectors.toSet());
//		}
//
//		public int getId() {
//			return id;
//		}
//
//		public Set<Compressed> getPersos() {
//			return persos;
//		}
//	}

}
