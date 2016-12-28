/*
 * 
 * 
 * 
 */
package connexion;

import com.corundumstudio.socketio.SocketIONamespace;
import connexion.events.NetConnexionConnection;
import connexion.events.NetConnexionDeconnection;
import connexion.events.NetConnexionEventConnexion;
import connexion.events.NetConnexionEventDeconnexion;
import connexion.events.NetConnexionEventVersion;
import connexion.modele.ModeleConnexion;
import netserv.NamespaceContainer;

/**
 * NetConnexion.java
 * 
 */
public class NetConnexion extends NamespaceContainer<ModeleConnexion> {

	public static final String PREFIX = "connexion";

	public NetConnexion(SocketIONamespace nspMain) {
		super(nspMain, PREFIX, new ModeleConnexion());
		this.addConnexionListener(new NetConnexionConnection(this));
		this.addDeconnexionListener(new NetConnexionDeconnection(this));
		this.addAllEvents(
				new NetConnexionEventVersion(this),
				new NetConnexionEventConnexion(this),
				new NetConnexionEventDeconnexion(this)
		);
	}

}
