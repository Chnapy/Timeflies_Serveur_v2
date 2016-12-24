/*
 * 
 * 
 * 
 */
package netserv;

/**
 * NetworkListener.java
 *
 * @param <N>
 */
public abstract class NetworkListener<N extends NamespaceContainer> {

	protected final N nspCtn;

	public NetworkListener(N nspCtn) {
		this.nspCtn = nspCtn;
	}

}
