/*
 * 
 * 
 * 
 */
package client;

import com.corundumstudio.socketio.SocketIOClient;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Client.java
 * 
 */
public class Client {
	
	private final long id;
	private final SocketIOClient socketClient;
	private StatutClient statut;
	private final DonneesClient donnees;
	private final Map<Long, Personnage> personnages;
	
	public Client(long id, SocketIOClient socketClient, StatutClient statut, String pseudo, String mail) {
		this(id, socketClient, statut, new DonneesClient(pseudo, mail), Collections.EMPTY_SET);
	}

	public Client(long id, SocketIOClient socketClient, StatutClient statut, DonneesClient donnees, Set<Personnage> personnages) {
		this.id = id;
		this.socketClient = socketClient;
		this.statut = statut;
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

	public StatutClient getStatut() {
		return statut;
	}

	public void setStatut(StatutClient statut) {
		this.statut = statut;
	}

}
