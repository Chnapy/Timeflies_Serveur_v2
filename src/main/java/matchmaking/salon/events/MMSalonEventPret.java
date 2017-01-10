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
import matchmaking.Matchmaking;
import matchmaking.salon.MatchmakingSalon;
import netserv.Receptable;
import netserv.Sendable;
import salon.Salon;
import matchmaking.salon.events.MMSalonEventPret.RecPret;
import netserv.NetworkServeur;

/**
 * MMSalonEventPret.java
 *
 */
public class MMSalonEventPret extends MMSalonEventListener<RecPret> {

	private static final String SUFFIX = "pret";
	
	private final Matchmaking matchmaking;

	public MMSalonEventPret(MatchmakingSalon nspCtn, Matchmaking matchmaking) {
		super(SUFFIX, nspCtn, RecPret.class);
		this.matchmaking = matchmaking;
	}

	@Override
	public boolean isEventRecevable(SocketIOClient client, RecPret data) {
		Client c = client.get(NetworkServeur.CLIENT_CLIENT);
		Salon s = client.get(NetworkServeur.CLIENT_SALON);
		return c != null && c.hasStatut(StatutClient.LOGGUE) && s != null && !s.isLock();
	}

	@Override
	public void onEvent(SocketIOClient client, RecPret data, AckRequest ackSender, Client c) {
		System.out.println(data);
		
		Salon s = client.get(NetworkServeur.CLIENT_SALON);

		boolean success = s.setPret(c, data.isPret());

		SendPret sp = new SendPret();
		sp.setIdjoueur(c.getId());
		sp.setSuccess(success);

		if (success) {
			sp.setPret(data.isPret());
			s.sendToAll(getEvent(), sp);
			if(s.isLock()) {
				matchmaking.salonTousPret(s);
			}
		} else {
			client.sendEvent(getEvent(), sp);
		}
	}

	public static class RecPret extends Receptable {

		private boolean pret;

		public boolean isPret() {
			return pret;
		}

		public void setPret(boolean pret) {
			this.pret = pret;
		}

		@Override
		public String toString() {
			return "RecPret{" + super.toString() + ", pret=" + pret + '}';
		}

	}

	public static class SendPret extends Sendable {

		private long idjoueur;
		private Boolean pret;

		public long getIdjoueur() {
			return idjoueur;
		}

		public void setIdjoueur(long idjoueur) {
			this.idjoueur = idjoueur;
		}

		public Boolean isPret() {
			return pret;
		}

		public void setPret(Boolean pret) {
			this.pret = pret;
		}

	}

}
