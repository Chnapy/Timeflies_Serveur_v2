/*
 * 
 * 
 * 
 */
package connexion.events;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import connexion.NetConnexion;
import connexion.events.NetConnexionEventVersion.RecVersion;
import connexion.modele.ModeleConnexion;
import main.Const;
import netserv.EventListener;
import netserv.Receptable;
import netserv.Sendable;

/**
 * NetConnexionEventConnexion.java
 *
 */
public class NetConnexionEventVersion extends EventListener<NetConnexion, ModeleConnexion, RecVersion> {

	private static final String EVENT = "version";

	public NetConnexionEventVersion(NetConnexion nspCtn) {
		super(EVENT, nspCtn, RecVersion.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecVersion data, AckRequest ackSender) {

		SendVersion paquet = new SendVersion(data.getVersion().equals(Const.CLEF_CLIENT));
		
		client.set("version", paquet.isSuccess());
		
		client.sendEvent(getEvent(), paquet);

	}

	public static class RecVersion extends Receptable {

		private String version;

		public void setVersion(String version) {
			this.version = version;
		}

		public String getVersion() {
			return version;
		}

		@Override
		public String toString() {
			return "RecVersion{" + super.toString() + ", version=" + version + '}';
		}
	}

	public static class SendVersion extends Sendable {

		public SendVersion(boolean success) {
			super(success);
		}

	}

}
