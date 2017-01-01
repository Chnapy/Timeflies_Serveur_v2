/*
 * 
 * 
 * 
 */
package salon.net;

import com.corundumstudio.socketio.SocketIONamespace;
import netserv.NamespaceContainer;
import salon.net.events.NetSalonEventAjouterPerso;
import salon.net.events.NetSalonEventQuitterSalon;
import salon.net.events.NetSalonEventRetraitPerso;
import salon.net.events.NetSalonEventSetProprietes;

/**
 * NetSalon.java
 * 
 */
public class NetSalon extends NamespaceContainer<ModeleSalon> {

	public static final String PREFIX = "salon";

	public NetSalon(SocketIONamespace nspMain) {
		super(nspMain, PREFIX, new ModeleSalon());
		this.addAllEvents(
				new NetSalonEventSetProprietes(this),
				new NetSalonEventQuitterSalon(this),
				new NetSalonEventAjouterPerso(this),
				new NetSalonEventRetraitPerso(this)
		);
	}

}
