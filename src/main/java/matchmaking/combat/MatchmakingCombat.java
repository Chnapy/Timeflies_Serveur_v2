/*
 * 
 * 
 * 
 */
package matchmaking.combat;

import com.corundumstudio.socketio.SocketIONamespace;
import combat.Combat;
import java.util.HashMap;
import java.util.Map;
import matchmaking.Matchmaking;
import matchmaking.combat.modele.ModeleMMCombat;
import netserv.NamespaceContainer;
import outils.Temps;
import salon.Salon;

/**
 * MatchmakingCombat.java
 *
 */
public class MatchmakingCombat extends NamespaceContainer<ModeleMMCombat> {

	public static final String PREFIX = "combat";

	private final Map<Long, Combat> combats;

	public MatchmakingCombat(SocketIONamespace nspMain, Matchmaking matchmaking) {
		super(nspMain, PREFIX, new ModeleMMCombat());
		this.combats = new HashMap();
		this.addAllEvents(
		
		);
	}

	public void newCombat(Salon s, Temps datelancement) {
		Combat newCombat = new Combat(s);
		
		combats.put(newCombat.getId(), newCombat);
	}

}
