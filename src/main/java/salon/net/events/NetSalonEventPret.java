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
import salon.net.events.NetSalonEventPret.RecPret;

/**
 * NetSalonEventPret.java
 *
 */
public class NetSalonEventPret extends NetSalonEventListener<RecPret> {

	private static final String SUFFIX = "pret";

	public NetSalonEventPret(NetSalon nspCtn) {
		super(SUFFIX, nspCtn, RecPret.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecPret data, AckRequest ackSender, Client c, Salon s) {
		System.out.println(data);
		
		boolean success = s.setPret(c, data.isPret());

		SendPret sp = new SendPret();
		sp.setSuccess(success);

		client.sendEvent(getEvent(), sp);
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

	}

}
