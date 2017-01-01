/*
 * 
 * 
 * 
 */
package connexion.events;

import client.Client;
import client.Personnage;
import client.StatutClient;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import connexion.NetConnexion;
import connexion.events.NetConnexionEventConnexion.RecConnexion;
import connexion.modele.ModeleConnexion;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import netserv.EventListener;
import netserv.NetworkServeur;
import netserv.Receptable;
import netserv.Sendable;

/**
 * NetConnexionEventConnexion.java
 *
 */
public class NetConnexionEventConnexion extends EventListener<NetConnexion, ModeleConnexion, RecConnexion> {

	private static final String EVENT = "connexion";

	public NetConnexionEventConnexion(NetConnexion nspCtn) {
		super(EVENT, nspCtn, RecConnexion.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecConnexion data, AckRequest ackSender) {

		if (client.get(NetworkServeur.CLIENT_VERSION) == null
				|| !client.<Boolean>get(NetworkServeur.CLIENT_VERSION)) {
			Logger.getGlobal().log(Level.WARNING, "Connexion : version client non conforme");
			SendConnexion retour = new SendConnexion();
			retour.setSuccess(false);
			client.sendEvent(getEvent(), retour);
			return;
		}

		String pseudo = data.getPseudo();
		String mdp = data.getMdp();

		Optional<Map<String, Object>> result = this.modele.checkConnexion(pseudo, mdp);

		boolean success = result.isPresent();

		SendConnexion retour = new SendConnexion();
		retour.setSuccess(success);

		if (success) {
			Map<String, Object> map = result.get();
			retour.setIdJoueur((long) map.get("id_joueur"));
			retour.setPseudo((String) map.get("pseudo"));
			retour.setMail((String) map.get("mail"));
		}

		client.sendEvent(getEvent(), retour);

		if (success && !this.nspCtn.getClients().containsKey(client.getSessionId())) {
			Client c = new Client(retour.getIdJoueur(), client,
					retour.getPseudo(), retour.getMail());
			c.addStatut(StatutClient.LOGGUE, StatutClient.CONNECTE, StatutClient.CLIENT_OK);
			this.nspCtn.getClients().put(client.getSessionId(), c);

			client.set(NetworkServeur.CLIENT_CLIENT, c);

			Set<Personnage> persos = this.modele.getAllPersonnagesClient(c);
			c.setPersonnages(persos);
			Map<String, Integer> statsCombat = this.modele.getStatsCombat(c.getId());
			c.getDonnees().setNbrCombatJoues(statsCombat.get("total"));
			c.getDonnees().setNbrVictoires(statsCombat.get("gagne"));
			c.getDonnees().setNbrDefaites(statsCombat.get("perdu"));
		}
	}

	public static class RecConnexion extends Receptable {

		private String pseudo;
		private String mdp;

		public String getPseudo() {
			return pseudo;
		}

		public void setPseudo(String pseudo) {
			this.pseudo = pseudo;
		}

		public String getMdp() {
			return mdp;
		}

		public void setMdp(String mdp) {
			this.mdp = mdp;
		}

		@Override
		public String toString() {
			return "RecConnexion{" + super.toString() + ", pseudo=" + pseudo + ", mdp=" + mdp + '}';
		}
	}

	public static class SendConnexion extends Sendable {

		private long idJoueur;
		private String pseudo;
		private String mail;

		public long getIdJoueur() {
			return idJoueur;
		}

		public void setIdJoueur(long idJoueur) {
			this.idJoueur = idJoueur;
		}

		public String getPseudo() {
			return pseudo;
		}

		public void setPseudo(String pseudo) {
			this.pseudo = pseudo;
		}

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

	}
}
