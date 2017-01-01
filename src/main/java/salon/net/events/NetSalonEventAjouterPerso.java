/*
 * 
 * 
 * 
 */
package salon.net.events;

import client.Client;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import netserv.Receptable;
import netserv.Sendable;
import salon.Salon;
import salon.net.NetSalon;
import salon.net.events.NetSalonEventAjouterPerso.RecAjouterPerso;

/**
 * NetSalonEventAjouterPerso.java
 *
 */
public class NetSalonEventAjouterPerso extends NetSalonEventListener<RecAjouterPerso> {

	private static final String SUFFIX = "ajoutperso";

	public NetSalonEventAjouterPerso(NetSalon nspCtn) {
		super(SUFFIX, nspCtn, RecAjouterPerso.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecAjouterPerso data, AckRequest ackSender, Client c, Salon s) {
		System.out.println(data);

		boolean success = s.ajouterPerso(c, data.getIdperso(), data.getEquipe());

		SendAjouterPerso sap = new SendAjouterPerso();
		sap.setSuccess(success);
		
		client.sendEvent(getEvent(), sap);
	}

	public static class RecAjouterPerso extends Receptable {

		private long idperso;
		private int equipe;

		public long getIdperso() {
			return idperso;
		}

		public void setIdperso(long idperso) {
			this.idperso = idperso;
		}

		public int getEquipe() {
			return equipe;
		}

		public void setEquipe(int equipe) {
			this.equipe = equipe;
		}

		@Override
		public String toString() {
			return "RecAjouterPerso{" + super.toString() + ", idperso=" + idperso + ", equipe=" + equipe + '}';
		}

	}

	public static class SendAjouterPerso extends Sendable {

	}

}
