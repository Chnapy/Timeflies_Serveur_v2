/*
 * 
 * 
 * 
 */
package connexion.events;

import client.StatutClient;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import connexion.NetConnexion;
import connexion.events.NetConnexionEventDeconnexion.RecDeconnexion;
import connexion.modele.ModeleConnexion;
import netserv.EventListener;
import netserv.Receptable;

/**
 * NetConnexionEventConnexion.java
 *
 */
public class NetConnexionEventDeconnexion extends EventListener<NetConnexion, ModeleConnexion, RecDeconnexion> {

	private static final String EVENT = "deconnexion";

	public NetConnexionEventDeconnexion(NetConnexion nspCtn) {
		super(EVENT, nspCtn, RecDeconnexion.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecDeconnexion data, AckRequest ackSender) {

		try {
			this.nspCtn.getClients().remove(client.getSessionId()).setStatut(StatutClient.DECONNECTE);
		} catch (NullPointerException ex) {
		}

//		client.disconnect();
	}

	public static class RecDeconnexion extends Receptable {

	}

}
