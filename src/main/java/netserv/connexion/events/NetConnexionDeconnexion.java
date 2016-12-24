/*
 * 
 * 
 * 
 */
package netserv.connexion.events;

import com.corundumstudio.socketio.SocketIOClient;
import netserv.DeconnexionListener;
import netserv.connexion.NetworkConnexion;

/**
 * NetConnexionDeconnexion.java
 * 
 */
public class NetConnexionDeconnexion extends DeconnexionListener<NetworkConnexion> {

	public NetConnexionDeconnexion(NetworkConnexion nspCtn) {
		super(nspCtn);
	}

	@Override
	public void onDisconnect(SocketIOClient arg0) {
		System.out.println("Deconnexion : " + arg0.getSessionId());
	}

}
