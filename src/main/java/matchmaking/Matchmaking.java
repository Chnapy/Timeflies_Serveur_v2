/*
 * 
 * 
 * 
 */
package matchmaking;

import com.corundumstudio.socketio.SocketIONamespace;
import main.Const;
import matchmaking.combat.MatchmakingCombat;
import matchmaking.salon.MatchmakingSalon;
import netserv.EventListener;
import netserv.Sendable;
import outils.Temps;
import salon.Salon;

/**
 * Matchmaking.java
 * 
 */
public class Matchmaking {
	
	private static final String SUFFIX_LANCEMENT_COMBAT = "lancementcombat";
	
	private final MatchmakingSalon mSalon;
	private final MatchmakingCombat mCombat;
//
	public Matchmaking(SocketIONamespace nspMain) {
		this.mSalon = new MatchmakingSalon(nspMain, this);
		this.mCombat = new MatchmakingCombat(nspMain, this);
	}
	
	public void salonTousPret(Salon s) {
		String event = MatchmakingSalon.PREFIX + EventListener.SEPARATEUR + SUFFIX_LANCEMENT_COMBAT;
		Temps datelancement = new Temps().addMillis(Const.CHRONO_SALON);
		
		SendLancementCombat slc = new SendLancementCombat();
		slc.setSuccess(true);
		slc.setDatelancement(datelancement);
		
		s.sendToAll(event, slc);
		
		mCombat.newCombat(s, datelancement);
	}
	
	public static class SendLancementCombat extends Sendable {
		
		private Temps datelancement;

		public Temps getDatelancement() {
			return datelancement;
		}

		public void setDatelancement(Temps datelancement) {
			this.datelancement = datelancement;
		}
	}

}
