/*
 * 
 * 
 * 
 */
package salon.net.events;

import client.Client;
import client.StatutClient;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import netserv.EventListener;
import netserv.NetworkServeur;
import netserv.Receptable;
import salon.Salon;
import salon.net.ModeleSalon;
import salon.net.NetSalon;

/**
 * NetSalonEventListener.java
 *
 * @param <R>
 */
public abstract class NetSalonEventListener<R extends Receptable> extends EventListener<NetSalon, ModeleSalon, R> {

	public NetSalonEventListener(String suffixe, NetSalon nspCtn, Class<R> classe) {
		super(suffixe, nspCtn, classe);
	}

	@Override
	public boolean isEventRecevable(SocketIOClient client, R data) {
		Client c = client.get(NetworkServeur.CLIENT_CLIENT);
		Salon s = client.get(NetworkServeur.CLIENT_SALON);
		return c != null && c.hasStatut(StatutClient.EN_SALON) && s != null;
	}

	@Override
	public void onEvent(SocketIOClient client, R data, AckRequest ackSender) {
		onEvent(client, data, ackSender,
				client.get(NetworkServeur.CLIENT_CLIENT),
				client.get(NetworkServeur.CLIENT_SALON));
	}

	public abstract void onEvent(SocketIOClient sc, R data, AckRequest ackSender, Client c, Salon s);

}
