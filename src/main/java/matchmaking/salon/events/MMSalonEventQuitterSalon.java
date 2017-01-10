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
import netserv.NetworkServeur;
import netserv.Receptable;
import netserv.Sendable;
import salon.Salon;
import matchmaking.salon.events.MMSalonEventQuitterSalon.RecQuitterSalon;

/**
 * MMSalonEventQuitterSalon.java
 *
 */
public class MMSalonEventQuitterSalon extends MMSalonEventListener<RecQuitterSalon> {

	private static final String SUFFIX = "quittersalon";
	private static final String SUFFIX_SUPSALON = "supprimersalon";

	public MMSalonEventQuitterSalon(MatchmakingSalon nspCtn) {
		super(SUFFIX, nspCtn, RecQuitterSalon.class);
	}

	@Override
	public boolean isEventRecevable(SocketIOClient client, RecQuitterSalon data) {
		Client c = client.get(NetworkServeur.CLIENT_CLIENT);
		Salon s = client.get(NetworkServeur.CLIENT_SALON);
		return c != null && c.hasStatut(StatutClient.LOGGUE) && s != null && !s.isLock();
	}

	@Override
	public void onEvent(SocketIOClient client, RecQuitterSalon data, AckRequest ackSender, Client c) {
		System.out.println(data);
		
		Salon s = client.get(NetworkServeur.CLIENT_SALON);

		SendQuitterSalon sqs = new SendQuitterSalon();
		sqs.setSuccess(true);
		sqs.setIdjoueur(c.getId());

		s.sendToAll(getEvent(), sqs);

		boolean salonAsupprimer = s.removeClient(c);
		c.removeStatut(StatutClient.EN_SALON);
		client.del(NetworkServeur.CLIENT_SALON);
		
		if(salonAsupprimer) {
			supprimerSalon(s);
		}
	}
	
	private void supprimerSalon(Salon s) {
		
		s.getClients().clear();
		
		this.nspCtn.getSalons().remove(s.getId());
		
		SendSupprimerSalon sss = new SendSupprimerSalon();
		sss.setSuccess(true);
		sss.setIdsalon(s.getId());
		
		this.sendEventToHasStatuts(sss, getEvent(SUFFIX_SUPSALON), StatutClient.LISTE_SALONS);
	}

	public static class RecQuitterSalon extends Receptable {

	}
	
	public static class SendSupprimerSalon extends Sendable {
		
		private long idsalon;

		public long getIdsalon() {
			return idsalon;
		}

		public void setIdsalon(long idsalon) {
			this.idsalon = idsalon;
		}
		
	}

	public static class SendQuitterSalon extends Sendable {

		private long idjoueur;

		public long getIdjoueur() {
			return idjoueur;
		}

		public void setIdjoueur(long idjoueur) {
			this.idjoueur = idjoueur;
		}

	}

}
