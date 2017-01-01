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
import netserv.NetworkServeur;
import netserv.Receptable;
import salon.Salon;
import salon.net.NetSalon;
import salon.net.events.NetSalonEventQuitterSalon.RecQuitterSalon;

/**
 * NetSalonEventQuitterSalon.java
 * 
 */
public class NetSalonEventQuitterSalon extends NetSalonEventListener<RecQuitterSalon> {

	private static final String SUFFIX = "quittersalon";

	public NetSalonEventQuitterSalon(NetSalon nspCtn) {
		super(SUFFIX, nspCtn, RecQuitterSalon.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecQuitterSalon data, AckRequest ackSender, Client c, Salon s) {
		System.out.println(data);
		
		s.removeClient(c);
		c.removeStatut(StatutClient.EN_SALON);
		client.del(NetworkServeur.CLIENT_SALON);
	}
	
	public static class RecQuitterSalon extends Receptable {
		
	}

}
