/*
 * 
 * 
 * 
 */
package client;

import com.corundumstudio.socketio.SocketIOClient;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import netserv.Compressable;
import netserv.Compressed;

/**
 * Client.java
 *
 */
public class Client implements Compressable {

	private final long id;
	private final SocketIOClient socketClient;
	private Set<StatutClient> statut;
	private final DonneesClient donnees;
	private final Map<Long, Personnage> personnages;

	public Client(long id, SocketIOClient socketClient, String pseudo, String mail) {
		this(id, socketClient, new DonneesClient(pseudo, mail), Collections.EMPTY_SET);
	}

	public Client(long id, SocketIOClient socketClient, DonneesClient donnees, Set<Personnage> personnages) {
		this.id = id;
		this.socketClient = socketClient;
		this.statut = new HashSet();
		this.donnees = donnees;
		this.personnages = new HashMap();
		personnages.forEach((p) -> this.personnages.put(p.getIdPersonnage(), p));
	}

	public void setPersonnages(Set<Personnage> persos) {
		this.personnages.clear();
		persos.forEach((p) -> this.personnages.put(p.getIdPersonnage(), p));
	}

	public long getId() {
		return id;
	}

	public SocketIOClient getSocketClient() {
		return socketClient;
	}

	public DonneesClient getDonnees() {
		return donnees;
	}

	public Map<Long, Personnage> getPersonnages() {
		return personnages;
	}

	public boolean hasStatut(StatutClient... statuts) {
		return this.statut.containsAll(Arrays.asList(statuts));
	}

	public void addStatut(StatutClient... statuts) {
		this.statut.addAll(Arrays.asList(statuts));
	}

	public void removeStatut(StatutClient... statuts) {
		this.statut.removeAll(Arrays.asList(statuts));
	}
	
	public void clearStatut() {
		this.statut.clear();
	}

	public void setStatut(StatutClient... statuts) {
		clearStatut();
		addStatut(statuts);
	}

	@Override
	public Compressed getCompressed() {
		return new ClientCompressed(this);
	}

	public class ClientCompressed implements Compressed {

		private final long id;
		private final String pseudo;

		public ClientCompressed(Client c) {
			id = c.getId();
			pseudo = c.getDonnees().getInfosCompte().getPseudo();
		}

		public long getId() {
			return id;
		}

		public String getPseudo() {
			return pseudo;
		}
	}

}
