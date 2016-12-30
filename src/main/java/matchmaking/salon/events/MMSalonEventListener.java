/*
 * 
 * 
 * 
 */
package matchmaking.salon.events;

import client.Client;
import client.StatutClient;
import com.corundumstudio.socketio.SocketIOClient;
import matchmaking.salon.MatchmakingSalon;
import matchmaking.salon.modele.ModeleMMSalon;
import netserv.EventListener;
import netserv.Receptable;
import salon.Salon;

/**
 * MMSalonEventListener.java
 * 
 * @param <R>
 */
public abstract class MMSalonEventListener<R extends Receptable> extends EventListener<MatchmakingSalon, ModeleMMSalon, R> {

	public MMSalonEventListener(String suffixe, MatchmakingSalon nspCtn, Class<R> classe) {
		super(suffixe, nspCtn, classe);
	}

	@Override
	public boolean isEventRecevable(SocketIOClient client, R data) {
		Client c = client.get("client");
		Salon s = client.get("salon");
		return c != null && c.hasStatut(StatutClient.LOGGUE) && s == null;
	}

}