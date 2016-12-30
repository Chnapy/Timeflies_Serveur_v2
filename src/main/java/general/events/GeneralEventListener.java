/*
 * 
 * 
 * 
 */
package general.events;

import client.Client;
import client.StatutClient;
import com.corundumstudio.socketio.SocketIOClient;
import general.NetGeneral;
import general.modele.ModeleGeneral;
import netserv.EventListener;
import netserv.Receptable;

/**
 * GeneralEventListener.java
 *
 * @param <R>
 */
public abstract class GeneralEventListener<R extends Receptable> extends EventListener<NetGeneral, ModeleGeneral, R> {

	public GeneralEventListener(String suffixe, NetGeneral nspCtn, Class<R> classe) {
		super(suffixe, nspCtn, classe);
	}

	@Override
	public boolean isEventRecevable(SocketIOClient client, R data) {
		Client c = client.get("client");
		return c != null && c.hasStatut(StatutClient.LOGGUE);
	}

}
