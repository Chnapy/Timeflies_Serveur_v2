/*
 * 
 * 
 * 
 */
package matchmaking.salon.events;

import client.Client;
import client.StatutClient;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import matchmaking.salon.MatchmakingSalon;
import matchmaking.salon.events.MMSalonEventSortListeSalons.RecSortListeSalons;
import netserv.NetworkServeur;
import netserv.Receptable;
import netserv.Sendable;

/**
 * MMSalonEventSortListeSalons.java
 *
 */
public class MMSalonEventSortListeSalons extends MMSalonEventListener<RecSortListeSalons> {

	private static final String SUFFIX = "sortlistesalons";

	public MMSalonEventSortListeSalons(MatchmakingSalon nspCtn) {
		super(SUFFIX, nspCtn, RecSortListeSalons.class);
	}

	@Override
	public boolean isEventRecevable(SocketIOClient client, RecSortListeSalons data) {
		return super.isEventRecevable(client, data) 
				&& ((Client) client.get(NetworkServeur.CLIENT_CLIENT)).hasStatut(StatutClient.LISTE_SALONS);
	}

	@Override
	public void onEvent(SocketIOClient client, RecSortListeSalons data, AckRequest ackSender, Client c) {

		SendSortListeSalons ssls = new SendSortListeSalons();
		ssls.setSuccess(true);

		c.removeStatut(StatutClient.EN_SALON);
		client.sendEvent(getEvent(), ssls);

		this.nspCtn.getClients().remove(client.getSessionId());
	}

	public static class RecSortListeSalons extends Receptable {

	}

	public static class SendSortListeSalons extends Sendable {

	}

}
