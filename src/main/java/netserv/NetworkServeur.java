/*
 * 
 * 
 * 
 */
package netserv;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import connexion.NetConnexion;
import general.NetGeneral;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Const;
import main.StartNStop;
import matchmaking.salon.MatchmakingSalon;
import salon.net.NetSalon;

/**
 * NetworkServeur.java
 *
 */
public class NetworkServeur implements StartNStop {

	public static final String CLIENT_VERSION = "version";
	public static final String CLIENT_CLIENT = "client";
	public static final String CLIENT_SALON = "salon";

	private static final String NSP_NAME = "main";

	private final SocketIOServer io;
	private final SocketIONamespace nspMain;

	private final NetConnexion connexion;
	private final NetGeneral general;
	private final MatchmakingSalon mmsalon;
	private final NetSalon salon;

	public NetworkServeur() {
		this.io = new SocketIOServer(getIOConf());
		this.nspMain = this.io.addNamespace("/" + NSP_NAME);

		this.connexion = new NetConnexion(this.nspMain);
		this.general = new NetGeneral(this.nspMain);
		this.mmsalon = new MatchmakingSalon(this.nspMain);
		this.salon = new NetSalon(this.nspMain);
	}

	@Override
	public void onStart() {
		Logger.getGlobal().log(Level.INFO, "D\u00e9marrage network - ''localhost:{0}''", io.getConfiguration().getPort());

		this.io.start();
	}

	@Override
	public void onStop() {
		this.io.stop();
	}

	private Configuration getIOConf() {
		Configuration conf = new Configuration();
		conf.setHostname("localhost");
		conf.setPort(Const.SERVEUR_PORT);
		return conf;
	}

}
