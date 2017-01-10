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
import netserv.Sendable;
import salon.Salon;

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

		SendDeconnexion sd = new SendDeconnexion();

		try {
			Client c = this.nspCtn.getClients().remove(client.getSessionId());

			sd.setIdjoueur(c.getId());
			sd.setSuccess(true);

			try {
				Salon s = client.get(NetworkServeur.CLIENT_SALON);
				s.sendToAll(getEvent(), sd);
				s.removeClient(c);
			} catch (NullPointerException ex) {
				client.sendEvent(getEvent(), sd);
			}

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

	public static class SendDeconnexion extends Sendable {

		private long idjoueur;

		public long getIdjoueur() {
			return idjoueur;
		}

		public void setIdjoueur(long idjoueur) {
			this.idjoueur = idjoueur;
		}

	}

}
