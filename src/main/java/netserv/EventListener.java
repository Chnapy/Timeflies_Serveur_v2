/*
 * 
 * 
 * 
 */
package netserv;

import com.corundumstudio.socketio.listener.DataListener;
import java.util.LinkedHashMap;

/**
 * EventListener.java
 *
 * @param <N>
 */
public abstract class EventListener<N extends NamespaceContainer> extends NetworkListener<N> implements DataListener<Object> {

	private final String event;
	private final Class<?> classe;

	public EventListener(String event, N nspCtn) {
		this(event, nspCtn, Object.class);
	}

	public EventListener(String event, N nspCtn, Class<?> classe) {
		super(nspCtn);
		this.event = event;
		this.classe = classe;
	}

	public String getEvent() {
		return event;
	}

	public Class<?> getDataClass() {
		return this.classe;
	}

}
