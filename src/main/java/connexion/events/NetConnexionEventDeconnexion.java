/*
 * 
 * 
 * 
 */
package connexion.events;

import client.Client;
import client.StatutClient;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import connexion.NetConnexion;
import connexion.events.NetConnexionEventDeconnexion.RecDeconnexion;
import connexion.modele.ModeleConnexion;
import netserv.EventListener;
import netserv.NetworkServeur;
import netserv.Receptable;

/**
 * NetConnexionEventConnexion.java
 *
 */
public class NetConnexionEventDeconnexion extends EventListener<NetConnexion, ModeleConnexion, RecDeconnexion> {

	private static final String SUFFIX = "deconnexion";

	public NetConnexionEventDeconnexion(NetConnexion nspCtn) {
		super(SUFFIX, nspCtn, RecDeconnexion.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecDeconnexion data, AckRequest ackSender) {

		try {
			Client c = this.nspCtn.getClients().remove(client.getSessionId());
			c.setStatut(StatutClient.DECONNECTE);
			client.del(NetworkServeur.CLIENT_VERSION);
			client.del(NetworkServeur.CLIENT_CLIENT);
			client.del(NetworkServeur.CLIENT_SALON);
		} catch (NullPointerException ex) {
		}

		client.disconnect();
	}

	public static class RecDeconnexion extends Receptable {

	}

}
