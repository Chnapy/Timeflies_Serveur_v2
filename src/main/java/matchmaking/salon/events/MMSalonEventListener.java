/*
 * 
 * 
 * 
 */
package matchmaking.salon.events;

import client.Client;
import client.StatutClient;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import matchmaking.salon.MatchmakingSalon;
import matchmaking.salon.modele.ModeleMMSalon;
import netserv.EventListener;
import netserv.NetworkServeur;
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
		Client c = client.get(NetworkServeur.CLIENT_CLIENT);
		Salon s = client.get(NetworkServeur.CLIENT_SALON);
		return c != null && c.hasStatut(StatutClient.LOGGUE) && s == null;
	}

	@Override
	public void onEvent(SocketIOClient client, R data, AckRequest ackSender) {
		onEvent(client, data, ackSender, client.get(NetworkServeur.CLIENT_CLIENT));
	}

	public abstract void onEvent(SocketIOClient client, R data, AckRequest ackSender, Client c);

}