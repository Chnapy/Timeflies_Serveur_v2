/*
 * 
 * 
 * 
 */
package netserv;

import client.Client;
import com.corundumstudio.socketio.SocketIONamespace;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * NamespaceContainer.java
 *
 * @param <M>
 */
public abstract class NamespaceContainer<M extends Modele> {

	private final String prefix;
	protected final SocketIONamespace nspMain;
	protected final M modele;
	private final HashMap<UUID, Client> clients;

	public NamespaceContainer(SocketIONamespace nspMain, String prefix, M modele) {
		this.prefix = prefix;
		this.nspMain = nspMain;
		this.modele = modele;
		this.clients = new HashMap();
	}

	public final void addAllEvents(EventListener... events) {
		this.addAllEvents(Arrays.asList(events));
	}

	public final void addAllEvents(List<EventListener> events) {
		events.forEach((e) -> {
			e.setPrefixe(prefix);
			System.out.println(e.getEvent());
			nspMain.addEventListener(e.getEvent(), e.getDataClass(), e);
		});
	}

	public final void addConnexionListener(ConnexionListener cl) {
		this.nspMain.addConnectListener(cl);
	}

	public final void addDeconnexionListener(DeconnexionListener dl) {
		this.nspMain.addDisconnectListener(dl);
	}

	public M getModele() {
		return modele;
	}

	public SocketIONamespace getNspMain() {
		return nspMain;
	}

	public HashMap<UUID, Client> getClients() {
		return clients;
	}
	
	public Client getClientFromId(long id) throws IllegalArgumentException {
		for(Client c : this.clients.values()) {
			if(c.getId() == id) {
				return c;
			}
		}
		throw new IllegalArgumentException("id : " + id);
	}

}
