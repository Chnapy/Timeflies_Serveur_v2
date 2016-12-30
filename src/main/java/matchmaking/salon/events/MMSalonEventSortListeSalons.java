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
	public void onEvent(SocketIOClient client, RecSortListeSalons data, AckRequest ackSender) {

		SendSortListeSalons ssls = new SendSortListeSalons();
		ssls.setSuccess(true);

		Client c = client.get("client");
		c.removeStatut(StatutClient.EN_SALON);
		client.sendEvent(getEvent(), ssls);

	}

	public static class RecSortListeSalons extends Receptable {

	}

	public static class SendSortListeSalons extends Sendable {

	}

}
