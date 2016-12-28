/*
 * 
 * 
 * 
 */
package connexion.events;

import com.corundumstudio.socketio.SocketIOClient;
import connexion.NetConnexion;
import netserv.ConnexionListener;

/**
 * NetConnexionConnection.java
 *
 */
public class NetConnexionConnection extends ConnexionListener<NetConnexion> {

	public NetConnexionConnection(NetConnexion nspCtn) {
		super(nspCtn);
	}

	@Override
	public void onConnect(SocketIOClient sc) {
		System.out.println("connexion " + sc.getSessionId());
	}

}
