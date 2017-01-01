/*
 * 
 * 
 * 
 */
package general.events;

import classe.ClasseManager;
import client.Client;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import general.NetGeneral;
import general.events.NetGeneralEventGetAllClasseEntites.RecGetAllClasseEntites;
import java.util.Map;
import netserv.Compressed;
import netserv.Receptable;
import netserv.Sendable;

/**
 * NetGeneralEventGetAllClasseEntites.java
 * 
 */
public class NetGeneralEventGetAllClasseEntites extends GeneralEventListener<RecGetAllClasseEntites> {
	
	private static final String SUFFIX = "getallclasses";

	public NetGeneralEventGetAllClasseEntites(NetGeneral nspCtn) {
		super(SUFFIX, nspCtn, RecGetAllClasseEntites.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecGetAllClasseEntites data, AckRequest ackSender, Client c) {
		SendGetAllClasseEntites sgace = new SendGetAllClasseEntites();
		sgace.setClasseEntites(
				ClasseManager.getCompressedEnvoutements(),
				ClasseManager.getCompressedSorts(),
				ClasseManager.getCompressedEntites()
		);
		
		client.sendEvent(getEvent(), sgace);
	}
	
	public static class RecGetAllClasseEntites extends Receptable {
		
	}
	
	public static class SendGetAllClasseEntites extends Sendable {
		
		private Map<Long, Compressed> classeEnvoutements;
		private Map<Long, Compressed> classeSorts;
		private Map<Long, Compressed> classeEntites;

		public void setClasseEntites(Map<Long, Compressed> classeEnvoutements, 
				Map<Long, Compressed> classeSorts, 
				Map<Long, Compressed> classeEntites) {
			this.classeEnvoutements = classeEnvoutements;
			this.classeSorts = classeSorts;
			this.classeEntites = classeEntites;
		}

		public Map<Long, Compressed> getClasseEnvoutements() {
			return classeEnvoutements;
		}

		public Map<Long, Compressed> getClasseSorts() {
			return classeSorts;
		}

		public Map<Long, Compressed> getClasseEntites() {
			return classeEntites;
		}
		
	}

}
