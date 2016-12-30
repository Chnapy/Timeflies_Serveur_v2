/*
 * 
 * 
 * 
 */
package salon;

import client.Personnage;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import netserv.Compressable;
import netserv.Compressed;

/**
 * SalonEquipe.java
 *
 */
public class SalonEquipe extends HashSet<Personnage> implements Compressable {

	@Override
	public Compressed getCompressed() {
		return new SalonEquipeCompressed(this);
	}

	public class SalonEquipeCompressed implements Compressed {

		private final Set<Compressed> persos;

		public SalonEquipeCompressed(SalonEquipe se) {
			persos = se.stream().map(p -> p.getCompressed()).collect(Collectors.toSet());
		}

		public Set<Compressed> getPersos() {
			return persos;
		}
	}

}
