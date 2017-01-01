/*
 * 
 * 
 * 
 */
package general.events;

import classe.ClasseEntite;
import classe.ClasseManager;
import client.Client;
import client.Personnage;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import general.NetGeneral;
import general.events.NetGeneralEventCreerPerso.RecCreerPerso;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Const;
import netserv.Compressed;
import netserv.Receptable;
import netserv.Sendable;

/**
 * NetGeneralEventCreerPerso.java
 *
 */
public class NetGeneralEventCreerPerso extends GeneralEventListener<RecCreerPerso> {

	private static final String SUFFIX = "creerperso";

	public NetGeneralEventCreerPerso(NetGeneral nspCtn) {
		super(SUFFIX, nspCtn, RecCreerPerso.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecCreerPerso data, AckRequest ackSender, Client c) {

		String nom = data.getNom();
		int id = data.getIdclasseentite();

		SendCreerPerso send = new SendCreerPerso();
		send.setSuccess(true);

		if (nom.length() < Const.NOMPERSO_LENGTH_MIN
				|| nom.length() > Const.NOMPERSO_LENGTH_MAX) {
			Logger.getGlobal().log(Level.WARNING, "Cr\u00e9ation perso : nom perso non conforme : {0}", nom);
			setSendFailed(client, send);
			return;
		}

		try {
			ClasseEntite ce = ClasseManager.getEntiteFromId(id);
		} catch (IllegalArgumentException ex) {
			Logger.getGlobal().log(Level.WARNING, "Cr\u00e9ation perso : idclasse perso non conforme : {0}", id);
			setSendFailed(client, send);
			return;
		}

		if (c.getPersonnages().size() >= Const.NBR_MANAG_PERSOS_MAX) {
			Logger.getGlobal().log(Level.WARNING, "Cr\u00e9ation perso : nbr persos max d\u00e9j\u00e0 atteint : {0}/{1}", new Object[]{c.getPersonnages().size(), Const.NBR_MANAG_PERSOS_MAX});
			setSendFailed(client, send);
			return;
		}

		long idJoueur = c.getId();

		Personnage perso = this.modele.creerPerso(c, id, nom);
		send.setNewperso(perso.getCompressed());

		client.sendEvent(getEvent(), send);

		c.getPersonnages().put(perso.getIdPersonnage(), perso);
	}

	private void setSendFailed(SocketIOClient client, SendCreerPerso send) {
		send.setSuccess(false);
		//Définir le message d'erreur
		client.sendEvent(getEvent(), send);
	}

	public static class SendCreerPerso extends Sendable {

		private Compressed newperso;

		public Compressed getNewperso() {
			return newperso;
		}

		public void setNewperso(Compressed newperso) {
			this.newperso = newperso;
		}

	}

	public static class RecCreerPerso extends Receptable {

		private int idclasseentite;
		private String nom;

		public int getIdclasseentite() {
			return idclasseentite;
		}

		public void setIdclasseentite(int idclasseentite) {
			this.idclasseentite = idclasseentite;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		@Override
		public String toString() {
			return "RecCreerPerso{" + super.toString() + ", idclasseentite=" + idclasseentite + ", nom=" + nom + '}';
		}

	}

}
