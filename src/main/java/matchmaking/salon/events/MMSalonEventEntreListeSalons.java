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
import java.util.List;
import java.util.stream.Collectors;
import matchmaking.salon.MatchmakingSalon;
import matchmaking.salon.events.MMSalonEventEntreListeSalons.RecEntreListeSalons;
import netserv.Compressed;
import netserv.Receptable;
import netserv.Sendable;

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
	public void onEvent(SocketIOClient client, RecEntreListeSalons data, AckRequest ackSender) {

		SendEntreListeSalons liste = new SendEntreListeSalons();
		liste.setSuccess(true);
		liste.setSalons(this.nspCtn.getSalons().values().stream()
				.map(s -> s.getCompressed())
				.collect(Collectors.toList())
		);
		
		Client c = client.get("client");
		c.addStatut(StatutClient.LISTE_SALONS);
		client.sendEvent(getEvent(), liste);

	}

	public static class RecEntreListeSalons extends Receptable {

	}

	public static class SendEntreListeSalons extends Sendable {

		private List<Compressed> salons;

		public List<Compressed> getSalons() {
			return salons;
		}

		public void setSalons(List<Compressed> salons) {
			this.salons = salons;
		}

	}

}
