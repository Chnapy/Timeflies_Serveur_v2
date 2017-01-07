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
import matchmaking.salon.events.MMSalonEventCreerSalon.RecCreerSalon;
import netserv.Compressed;
import netserv.NetworkServeur;
import netserv.Receptable;
import netserv.Sendable;
import outils.json.MyJSONParser;
import salon.Salon;

/**
 * MMSalonEventCreerSalon.java
 *
 */
public class MMSalonEventCreerSalon extends MMSalonEventListener<RecCreerSalon> {

	private static final String SUFFIX = "creersalon";

	public MMSalonEventCreerSalon(MatchmakingSalon nspCtn) {
		super(SUFFIX, nspCtn, RecCreerSalon.class);
	}

	@Override
	public boolean isEventRecevable(SocketIOClient client, RecCreerSalon data) {
		return super.isEventRecevable(client, data) 
				&& ((Client) client.get(NetworkServeur.CLIENT_CLIENT)).hasStatut(StatutClient.LISTE_SALONS);
	}

	@Override
	public void onEvent(SocketIOClient client, RecCreerSalon data, AckRequest ackSender, Client c) {
try {
		Salon salon = new Salon(c);
		SendCreerSalon scs = new SendCreerSalon();
		scs.setSuccess(true);
		scs.setSalon(salon);
		
		System.out.println(MyJSONParser.objectToJSONString(salon));

		client.set(NetworkServeur.CLIENT_SALON, salon);
		c.addStatut(StatutClient.EN_SALON);
		client.sendEvent(getEvent(), scs);

		this.nspCtn.getClients().values().stream()
				.filter(cl -> cl.hasStatut(StatutClient.LISTE_SALONS))
				.forEach(cl -> cl.getSocketClient().sendEvent(getEvent(), scs));

		this.nspCtn.getSalons().put(salon.getId(), salon);
} catch(Exception e) {
	e.printStackTrace();
}
	}

	public static class RecCreerSalon extends Receptable {

	}

	public static class SendCreerSalon extends Sendable {

		private Salon salon;

		public Salon getSalon() {
			return salon;
		}

		public void setSalon(Salon salon) {
			this.salon = salon;
		}

	}

}
