package matchmaking.salon;

/*
 * 
 * 
 * 
 */
import com.corundumstudio.socketio.SocketIONamespace;
import java.util.HashMap;
import java.util.Map;
import matchmaking.salon.events.MMSalonEventCreerSalon;
import matchmaking.salon.events.MMSalonEventEntreListeSalons;
import matchmaking.salon.events.MMSalonEventRejoindreSalon;
import matchmaking.salon.events.MMSalonEventSortListeSalons;
import matchmaking.salon.modele.ModeleMMSalon;
import netserv.NamespaceContainer;
import salon.Salon;

/**
 * MatchmakingSalon.java
 *
 */
public class MatchmakingSalon extends NamespaceContainer<ModeleMMSalon> {

	public static final String PREFIX = "mmsalon";

	private final Map<Integer, Salon> salons;

	public MatchmakingSalon(SocketIONamespace nspMain) {
		super(nspMain, PREFIX, new ModeleMMSalon());
		this.salons = new HashMap();
		this.addAllEvents(new MMSalonEventEntreListeSalons(this),
				new MMSalonEventCreerSalon(this),
				new MMSalonEventRejoindreSalon(this),
				new MMSalonEventSortListeSalons(this)
		);
	}

	public Map<Integer, Salon> getSalons() {
		return salons;
	}

}
