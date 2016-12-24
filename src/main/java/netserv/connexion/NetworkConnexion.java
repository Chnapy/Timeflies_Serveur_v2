/*
 * 
 * 
 * 
 */
package netserv.connexion;

import com.corundumstudio.socketio.SocketIONamespace;
import netserv.NamespaceContainer;
import netserv.connexion.events.NetConnexionConnexion;
import netserv.connexion.events.NetConnexionDeconnexion;
import netserv.connexion.events.NetConnexionEventConnexion;
import netserv.connexion.events.NetConnexionEventDeconnexion;

/**
 * NetworkConnexion.java
 * 
 */
public class NetworkConnexion extends NamespaceContainer {

	public static final String NOM = "connexion";

	public NetworkConnexion(SocketIONamespace namespace) {
		super(namespace);
		this.addConnexionListener(new NetConnexionConnexion(this));
		this.addDeconnexionListener(new NetConnexionDeconnexion(this));
		this.addAllEvents(
				new NetConnexionEventConnexion(this),
				new NetConnexionEventDeconnexion(this)
		);
//		this.nsp.addEventListener("connexion", Object.class, new DataListener<Object>() {
//			@Override
//			public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
//				System.out.println("TEST" + data.getClass());
//			}
//		});
	}

}
