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
public class NetConnexionEventConnexion extends EventListener<NetworkConnexion> {
	
	private static final String EVENT = "connexion";

	public NetConnexionEventConnexion(NetworkConnexion nspCtn) {
		super(EVENT, nspCtn);
	}

	@Override
	public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
		System.out.println("Message connexion: " + data);
	}

}
