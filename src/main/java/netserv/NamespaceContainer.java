/*
 * 
 * 
 * 
 */
package netserv;

import com.corundumstudio.socketio.SocketIONamespace;
import java.util.Arrays;
import java.util.List;

/**
 * NamespaceContainer.java
 * 
 */
public abstract class NamespaceContainer {
	
	protected final SocketIONamespace nsp;
	
	public NamespaceContainer(SocketIONamespace namespace, List<EventListener> events) {
		this(namespace);
		this.addAllEvents(events);
	}

	public NamespaceContainer(SocketIONamespace namespace) {
		this.nsp = namespace;
	}
	
	public final void addAllEvents(EventListener... events) {
		this.addAllEvents(Arrays.asList(events));
	}
	
	public final void addAllEvents(List<EventListener> events) {
		events.forEach((e) -> nsp.addEventListener(e.getEvent(), e.getDataClass(), e));
	}
	
	public final void addConnexionListener(ConnexionListener cl) {
		this.nsp.addConnectListener(cl);
	}
	
	public final void addDeconnexionListener(DeconnexionListener dl) {
		this.nsp.addDisconnectListener(dl);
	}
	
}
