/*
 * 
 * 
 * 
 */
package general;

import com.corundumstudio.socketio.SocketIONamespace;
import general.events.NetGeneralConnection;
import general.events.NetGeneralEventGetAllClasseEntites;
import general.events.NetGeneralEventGetAllPersos;
import general.modele.ModeleGeneral;
import netserv.NamespaceContainer;

/**
 * NetGeneral.java
 * 
 */
public class NetGeneral extends NamespaceContainer<ModeleGeneral> {

	public static final String PREFIX = "general";

	public NetGeneral(SocketIONamespace nspMain) {
		super(nspMain, PREFIX, new ModeleGeneral());
		this.addConnexionListener(new NetGeneralConnection(this));
		this.addAllEvents(
				new NetGeneralEventGetAllPersos(this),
				new NetGeneralEventGetAllClasseEntites(this)
		);
	}

}
