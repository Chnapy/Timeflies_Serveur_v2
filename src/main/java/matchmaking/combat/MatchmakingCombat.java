/*
 * 
 * 
 * 
 */
package matchmaking.combat;

import com.corundumstudio.socketio.SocketIONamespace;
import com.fasterxml.jackson.databind.JsonNode;
import combat.Combat;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import matchmaking.Matchmaking;
import matchmaking.combat.modele.ModeleMMCombat;
import netserv.EventListener;
import netserv.NamespaceContainer;
import netserv.Sendable;
import outils.Temps;
import outils.json.MyJSONParser;
import salon.ClasseMap;
import salon.Salon;
import salon.proprietes.PropMap;
import salon.proprietes.TypePropriete;

/**
 * MatchmakingCombat.java
 *
 */
public class MatchmakingCombat extends NamespaceContainer<ModeleMMCombat> {

	public static final String PREFIX = "combat";

	private static final String SUFFIX_MAP_COMBAT = "map";

	private final Map<Long, Combat> combats;

	public MatchmakingCombat(SocketIONamespace nspMain, Matchmaking matchmaking) {
		super(nspMain, PREFIX, new ModeleMMCombat());
		this.combats = new HashMap();
		this.addAllEvents();
	}

	public void newCombat(Salon s, Temps datelancement) {
		Combat newCombat = new Combat(s);

		combats.put(newCombat.getId(), newCombat);

		String event = EventListener.getAllEvent(MatchmakingCombat.PREFIX, SUFFIX_MAP_COMBAT);
		
		ClasseMap cm = s.<PropMap>getProp(TypePropriete.MAP).getValeur();
		
		SendMap sm = new SendMap();
		sm.setIdcombat(newCombat.getId());
		try {
			sm.setClassemap(MyJSONParser.getJSON(new File(cm.getChemin())));
			sm.setSuccess(true);
		} catch (IOException ex) {
			Logger.getLogger(MatchmakingCombat.class.getName()).log(Level.SEVERE, null, ex);
		}
		newCombat.sendToAll(event, sm);
	}
	
	public static class SendMap extends Sendable {
		
		private long idcombat;
		private JsonNode classemap;

		public long getIdcombat() {
			return idcombat;
		}

		public void setIdcombat(long idcombat) {
			this.idcombat = idcombat;
		}

		public JsonNode getClassemap() {
			return classemap;
		}

		public void setClassemap(JsonNode classemap) {
			this.classemap = classemap;
		}
		
	}

}
