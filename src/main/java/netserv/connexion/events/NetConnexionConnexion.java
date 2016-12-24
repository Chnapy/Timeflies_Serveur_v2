/*
 * 
 * 
 * 
 */
package netserv.connexion.events;

import com.corundumstudio.socketio.SocketIOClient;
import netserv.ConnexionListener;
import netserv.connexion.NetworkConnexion;

/**
 * NetConnexionConnexion.java
 * 
 */
public class NetConnexionConnexion extends ConnexionListener<NetworkConnexion> {

	public NetConnexionConnexion(NetworkConnexion nspCtn) {
		super(nspCtn);
	}

	@Override
	public void onConnect(SocketIOClient arg0) {
		System.out.println("Connexion : " + arg0.getSessionId());
	}

}
