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
import salon.net.events.NetSalonEventRetraitPerso.RecRetraitPerso;

/**
 * NetSalonEventRetraitPerso.java
 *
 */
public class NetSalonEventRetraitPerso extends NetSalonEventListener<RecRetraitPerso> {

	private static final String SUFFIX = "retraitperso";

	public NetSalonEventRetraitPerso(NetSalon nspCtn) {
		super(SUFFIX, nspCtn, RecRetraitPerso.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecRetraitPerso data, AckRequest ackSender, Client c, Salon s) {
		System.out.println(data);
		
		boolean success = s.retraitPerso(c, data.getIdperso());
		
		SendRetraitPerso srp = new SendRetraitPerso();
		srp.setSuccess(success);
		
		client.sendEvent(getEvent(), srp);
	}

	public static class RecRetraitPerso extends Receptable {

		private long idperso;

		public long getIdperso() {
			return idperso;
		}

		public void setIdperso(long idperso) {
			this.idperso = idperso;
		}

		@Override
		public String toString() {
			return "RecRetraitPerso{" + super.toString() + ", idperso=" + idperso + '}';
		}

	}

	public static class SendRetraitPerso extends Sendable {

	}

}
