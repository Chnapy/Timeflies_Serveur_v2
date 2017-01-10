/*
 * 
 * 
 * 
 */
package salon.net;

import com.corundumstudio.socketio.SocketIONamespace;
import netserv.NamespaceContainer;
import salon.net.events.NetSalonEventAjouterPerso;
import salon.net.events.NetSalonEventRetraitPerso;
import salon.net.events.NetSalonEventSetProprietes;

/**
 * NetSalon.java
 * 
 */
public class NetSalon extends NamespaceContainer<ModeleNetSalon> {

	public static final String PREFIX = "salon";

	public NetSalon(SocketIONamespace nspMain) {
		super(nspMain, PREFIX, new ModeleNetSalon());
		this.addAllEvents(new NetSalonEventSetProprietes(this),
				new NetSalonEventAjouterPerso(this),
				new NetSalonEventRetraitPerso(this)
		);
	}

}
