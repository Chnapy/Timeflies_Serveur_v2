/*
 * 
 * 
 * 
 */
package netserv;

import com.corundumstudio.socketio.listener.DisconnectListener;

/**
 * DeconnexionListener.java
 * 
 * @param <N>
 */
public abstract class DeconnexionListener<N extends NamespaceContainer> extends NetworkListener<N> implements DisconnectListener {

	public DeconnexionListener(N nspCtn) {
		super(nspCtn);
	}

}