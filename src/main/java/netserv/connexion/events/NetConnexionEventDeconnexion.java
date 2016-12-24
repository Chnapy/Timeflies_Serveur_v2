/*
 * 
 * 
 * 
 */
package netserv.connexion.events;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import java.util.LinkedHashMap;
import netserv.EventListener;
import netserv.connexion.NetworkConnexion;

/**
 * NetConnexionEventConnexion.java
 * 
 */
public class NetConnexionEventDeconnexion extends EventListener<NetworkConnexion> {
	
	private static final String EVENT = "deconnexion";

	public NetConnexionEventDeconnexion(NetworkConnexion nspCtn) {
		super(EVENT, nspCtn);
	}

	@Override
	public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
		System.out.println("Message deconnexion: " + data);
		client.disconnect();
	}

}
