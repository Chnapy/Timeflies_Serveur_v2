/*
 * 
 * 
 * 
 */
package connexion.events;

import com.corundumstudio.socketio.SocketIOClient;
import connexion.NetConnexion;
import netserv.DeconnexionListener;

/**
 * NetConnexionDeconnection.java
 * 
 */
public class NetConnexionDeconnection extends DeconnexionListener<NetConnexion> {

	public NetConnexionDeconnection(NetConnexion nspCtn) {
		super(nspCtn);
	}

	@Override
	public void onDisconnect(SocketIOClient arg0) {
		System.out.println("Deconnexion : " + arg0.getSessionId());
	}

}
