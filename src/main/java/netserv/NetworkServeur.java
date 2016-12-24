/*
 * 
 * 
 * 
 */
package netserv;

import netserv.connexion.NetworkConnexion;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
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
	
	private final NetworkConnexion netCon;
	
	public NetworkServeur() {
		this.io = new SocketIOServer(getIOConf());
		
		this.netCon = new NetworkConnexion(this.io.addNamespace("/" + NetworkConnexion.NOM));
	}
	
	@Override
	public void onStart() {
		this.io.start();
		
		Logger.getGlobal().log(Level.INFO, "D\u00e9marrage network - ''localhost:{0}''", io.getConfiguration().getPort());
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
