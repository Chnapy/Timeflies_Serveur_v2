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
import java.util.Collection;
import matchmaking.salon.MatchmakingSalon;
import matchmaking.salon.events.MMSalonEventEntreListeSalons.RecEntreListeSalons;
import netserv.Receptable;
import netserv.Sendable;
import salon.Salon;

/**
 * MMSalonEventEntreListeSalons.java
 *
 */
public class MMSalonEventEntreListeSalons extends MMSalonEventListener<RecEntreListeSalons> {

	private static final String SUFFIX = "entrelistesalons";

	public MMSalonEventEntreListeSalons(MatchmakingSalon nspCtn) {
		super(SUFFIX, nspCtn, RecEntreListeSalons.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecEntreListeSalons data, AckRequest ackSender, Client c) {

		SendEntreListeSalons liste = new SendEntreListeSalons();
		liste.setSuccess(true);
		liste.setSalons(this.nspCtn.getSalons().values());
		
		c.addStatut(StatutClient.LISTE_SALONS);
		client.sendEvent(getEvent(), liste);
		
		this.nspCtn.getClients().put(client.getSessionId(), c);

	}

	public static class RecEntreListeSalons extends Receptable {

	}

	public static class SendEntreListeSalons extends Sendable {

		private Collection<Salon> salons;

		public Collection<Salon> getSalons() {
			return salons;
		}

		public void setSalons(Collection<Salon> salons) {
			this.salons = salons;
		}

	}

}
