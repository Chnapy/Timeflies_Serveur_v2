/*
 * 
 * 
 * 
 */
package general.events;

import client.Client;
import client.Personnage;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import general.NetGeneral;
import general.events.NetGeneralEventGetAllPersos.RecGetAllPersos;
import java.util.Collection;
import netserv.Receptable;
import netserv.Sendable;

/**
 * NetGeneralEventGetAllPersos.java
 * 
 */
public class NetGeneralEventGetAllPersos extends GeneralEventListener<RecGetAllPersos> {

	private static final String EVENT = "getallpersos";

	public NetGeneralEventGetAllPersos(NetGeneral nspCtn) {
		super(EVENT, nspCtn, RecGetAllPersos.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecGetAllPersos data, AckRequest ackSender, Client c) {
		
		SendGetAllPersos sgap = new SendGetAllPersos();
		sgap.setPersos(c.getPersonnages().values());
		sgap.setSuccess(true);
		
		client.sendEvent(getEvent(), sgap);
	}
	
	public static class RecGetAllPersos extends Receptable {
		
	}
	
	public static class SendGetAllPersos extends Sendable {
		
		private Collection<Personnage> persos;

		public Collection<Personnage> getPersos() {
			return persos;
		}

		public void setPersos(Collection<Personnage> persos) {
			this.persos = persos;
		}
		
	}

}
