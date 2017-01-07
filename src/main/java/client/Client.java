/*
 * 
 * 
 * 
 */
package client;

import com.corundumstudio.socketio.SocketIOClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Client.java
 *
 */
public class Client {

	private final long id;
	
	@JsonIgnore
	private final SocketIOClient socketClient;
	
	@JsonIgnore
	private Set<StatutClient> statut;
	
	@JsonIgnore
	private final DonneesClient donnees;
	
	@JsonIgnore
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
	
	@JsonProperty("pseudo")
	public String getPseudo() {
		return this.donnees.getInfosCompte().getPseudo();
	}

}
