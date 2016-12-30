/*
 * 
 * 
 * 
 */
package netserv;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EventListener.java
 *
 * @param <N>
 * @param <M>
 * @param <C>
 */
public abstract class EventListener<N extends NamespaceContainer, M extends Modele, C extends Receptable> extends NetworkListener<N> implements DataListener<C> {

	private final String suffixe;
	private String prefixe;
	private final Class<C> classe;
	protected final M modele;

	public EventListener(String suffixe, N nspCtn, Class<C> classe) {
		super(nspCtn);
		this.suffixe = suffixe;
		this.classe = classe;
		this.modele = (M) nspCtn.getModele();
	}

	public void setPrefixe(String prefixe) {
		this.prefixe = prefixe;
	}

	public String getEvent() {
		return prefixe + "_" + suffixe;
	}

	public Class<?> getDataClass() {
		return this.classe;
	}

	@Override
	public void onData(SocketIOClient client, C data, AckRequest ackSender) throws Exception {
		Logger.getGlobal().log(Level.INFO, "Message {0} : {1}", new Object[]{getEvent(), data});
		if (this.isEventRecevable(client, data)) {
			try {
				this.onEvent(client, data, ackSender);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			Logger.getGlobal().log(Level.WARNING, "Message {0} refusé !", getEvent());
		}
	}

	public abstract void onEvent(SocketIOClient client, C data, AckRequest ackSender);

	protected boolean isEventRecevable(SocketIOClient client, C data) {
		return true;
	}
;

}
