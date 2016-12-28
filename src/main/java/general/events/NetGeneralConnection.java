/*
 * 
 * 
 * 
 */
package general.events;

import com.corundumstudio.socketio.SocketIOClient;
import general.NetGeneral;
import netserv.ConnexionListener;

/**
 * NetGeneralConnection.java
 *
 */
public class NetGeneralConnection extends ConnexionListener<NetGeneral> {

	public NetGeneralConnection(NetGeneral nspCtn) {
		super(nspCtn);
	}

	@Override
	public void onConnect(SocketIOClient sc) {
	}

}
