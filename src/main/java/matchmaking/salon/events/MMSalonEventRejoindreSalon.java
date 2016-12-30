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
import matchmaking.salon.events.MMSalonEventRejoindreSalon.RecRejoindreSalon;
import netserv.Compressed;
import netserv.Receptable;
import netserv.Sendable;
import salon.Salon;

/**
 * MMSalonEventRejoindreSalon.java
 *
 */
public class MMSalonEventRejoindreSalon extends MMSalonEventListener<RecRejoindreSalon> {

	private static final String SUFFIX = "rejoindresalon";

	public MMSalonEventRejoindreSalon(MatchmakingSalon nspCtn) {
		super(SUFFIX, nspCtn, RecRejoindreSalon.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecRejoindreSalon data, AckRequest ackSender) {

		Salon salon = this.nspCtn.getSalons().get(data.getIdsalon());
		Client c = client.get("client");
		
		SendRejoindreSalon srs = new SendRejoindreSalon();
		srs.setSuccess(salon != null);
		
		if (salon != null) {
			salon.getClients().put(c, false);
			client.set("salon", salon);
			c.addStatut(StatutClient.EN_SALON);
			srs.setSalon(salon.getCompressed());
		}
		
		client.sendEvent(getEvent(), srs);
	}

	public static class RecRejoindreSalon extends Receptable {

		private int idsalon;

		public int getIdsalon() {
			return idsalon;
		}

		public void setIdsalon(int idsalon) {
			this.idsalon = idsalon;
		}
	}

	public static class SendRejoindreSalon extends Sendable {

		private Compressed salon;

		public Compressed getSalon() {
			return salon;
		}

		public void setSalon(Compressed salon) {
			this.salon = salon;
		}
	}

}
