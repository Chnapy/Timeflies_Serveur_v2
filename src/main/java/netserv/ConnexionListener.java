/*
 * 
 * 
 * 
 */
package netserv;

import com.corundumstudio.socketio.listener.ConnectListener;

/**
 * ConnexionListener.java
 *
 * @param <N>
 */
public abstract class ConnexionListener<N extends NamespaceContainer> extends NetworkListener<N> implements ConnectListener {

	public ConnexionListener(N nspCtn) {
		super(nspCtn);
	}

}
