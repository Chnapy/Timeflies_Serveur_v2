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

/**
 * NetworkServeur.java
 *
 */
public class NetworkServeur implements StartNStop {
	
	private final SocketIOServer io;
	
	private final NetConnexion connexion;
	private final NetGeneral general;
	private final SocketIONamespace nspMain;
	
	public NetworkServeur() {
		this.io = new SocketIOServer(getIOConf());
		this.nspMain = this.io.addNamespace("/main");
		
		this.connexion = new NetConnexion(this.nspMain);
		this.general = new NetGeneral(this.nspMain);
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
