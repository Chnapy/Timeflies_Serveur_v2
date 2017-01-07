/*
 * 
 * 
 * 
 */
package general.events;

import classe.ClasseEntite;
import classe.ClasseEnvoutement;
import classe.ClasseManager;
import classe.ClasseSort;
import client.Client;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import general.NetGeneral;
import general.events.NetGeneralEventGetAllClasseEntites.RecGetAllClasseEntites;
import java.util.Map;
import netserv.Receptable;
import netserv.Sendable;
import outils.json.MyJSONParser;

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
		try {
		SendGetAllClasseEntites sgace = new SendGetAllClasseEntites();
		sgace.setClasseEntites(
				ClasseManager.getEnvoutements(),
				ClasseManager.getSorts(),
				ClasseManager.getEntites()
		);
		sgace.setSuccess(true);
		
			System.out.println(MyJSONParser.objectToJSONString(sgace));

		client.sendEvent(getEvent(), sgace);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static class RecGetAllClasseEntites extends Receptable {

	}

	public static class SendGetAllClasseEntites extends Sendable {

		private Map<Long, ? extends ClasseEnvoutement> classeEnvoutements;
		private Map<Long, ? extends ClasseSort> classeSorts;
		private Map<Long, ? extends ClasseEntite> classeEntites;

		public void setClasseEntites(Map<Long, ClasseEnvoutement> classeEnvoutements,
				Map<Long, ClasseSort> classeSorts,
				Map<Long, ClasseEntite> classeEntites) {
			this.classeEnvoutements = classeEnvoutements;
			this.classeSorts = classeSorts;
			this.classeEntites = classeEntites;
		}

		public Map<Long, ? extends ClasseEnvoutement> getClasseEnvoutements() {
			return classeEnvoutements;
		}

		public Map<Long, ? extends ClasseSort> getClasseSorts() {
			return classeSorts;
		}

		public Map<Long, ? extends ClasseEntite> getClasseEntites() {
			return classeEntites;
		}

	}

}
