/*
 * 
 * 
 * 
 */
package salon.net.events;

import client.Client;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import netserv.Receptable;
import netserv.Sendable;
import salon.Salon;
import salon.net.NetSalon;
import salon.net.events.NetSalonEventSetProprietes.RecSetProprietes;

/**
 * NetSalonEventSetProprietes.java
 *
 */
public class NetSalonEventSetProprietes extends NetSalonEventListener<RecSetProprietes> {

	private static final String SUFFIX = "setproprietes";

	public NetSalonEventSetProprietes(NetSalon nspCtn) {
		super(SUFFIX, nspCtn, RecSetProprietes.class);
	}

	@Override
	public void onEvent(SocketIOClient client, RecSetProprietes data, AckRequest ackSender, Client c, Salon s) {

		SendSetProprietes ssp = new SendSetProprietes();

		if (!s.getDirigeant().equals(c)) {
			Logger.getGlobal().log(Level.WARNING, "Salon setproprietes : client non dirigeant : {0}", c);
			ssp.setSuccess(false);
			client.sendEvent(getEvent(), ssp);
			return;
		}

		s.setProprietes(data, this.nspCtn);
		
		ssp.set(data);
		ssp.setSuccess(true);

		s.getClients().keySet().forEach(cl
				-> cl.getSocketClient().sendEvent(getEvent(), ssp));

	}

	public static class RecSetProprietes extends Receptable {

		private Integer idmap;
		private Integer visibilite;
		private Integer typecombat;
		private Integer nbr_perso_classe;
		private Long iddirigeant;
		//Equipe
		private Integer nbr_equipe,
				nbr_eq_persos_equipe,
				nbr_eq_persos_classe,
				nbr_eq_persos_joueur;
		//CPS
		private Integer nbr_persos_total;

		public Integer getIdmap() {
			return idmap;
		}

		public void setIdmap(int idmap) {
			this.idmap = idmap;
		}

		public Integer getVisibilite() {
			return visibilite;
		}

		public void setVisibilite(Integer visibilite) {
			this.visibilite = visibilite;
		}

		public Integer getTypecombat() {
			return typecombat;
		}

		public void setTypecombat(Integer typecombat) {
			this.typecombat = typecombat;
		}

		public Long getIddirigeant() {
			return iddirigeant;
		}

		public void setIddirigeant(Long iddirigeant) {
			this.iddirigeant = iddirigeant;
		}

		public Integer getNbr_perso_classe() {
			return nbr_perso_classe;
		}

		public void setNbr_perso_classe(Integer nbr_perso_classe) {
			this.nbr_perso_classe = nbr_perso_classe;
		}

		public Integer getNbr_equipe() {
			return nbr_equipe;
		}

		public void setNbr_equipe(Integer nbr_equipe) {
			this.nbr_equipe = nbr_equipe;
		}

		public Integer getNbr_eq_persos_equipe() {
			return nbr_eq_persos_equipe;
		}

		public void setNbr_eq_persos_equipe(Integer nbr_persos_equipe) {
			this.nbr_eq_persos_equipe = nbr_persos_equipe;
		}

		public Integer getNbr_eq_persos_classe() {
			return nbr_eq_persos_classe;
		}

		public void setNbr_eq_persos_classe(Integer nbr_persos_classe) {
			this.nbr_eq_persos_classe = nbr_persos_classe;
		}

		public Integer getNbr_eq_persos_joueur() {
			return nbr_eq_persos_joueur;
		}

		public void setNbr_eq_persos_joueur(Integer nbr_persos_joueur) {
			this.nbr_eq_persos_joueur = nbr_persos_joueur;
		}

		public Integer getNbr_persos_total() {
			return nbr_persos_total;
		}

		public void setNbr_persos_total(Integer nbr_persos_total) {
			this.nbr_persos_total = nbr_persos_total;
		}

		@Override
		public String toString() {
			return "RecSetProprietes{" + super.toString() + ", idmap=" + idmap + ", visibilite=" + visibilite + ", typecombat=" + typecombat + ", nbr_perso_classe=" + nbr_perso_classe + ", iddirigeant=" + iddirigeant + ", nbr_equipe=" + nbr_equipe + ", nbr_persos_equipe=" + nbr_eq_persos_equipe + ", nbr_persos_classe=" + nbr_eq_persos_classe + ", nbr_persos_joueur=" + nbr_eq_persos_joueur + ", nbr_persos_total=" + nbr_persos_total + '}';
		}
	}

	public static class SendSetProprietes extends Sendable {

		private Integer idmap;
		private Integer visibilite;
		private Integer typecombat;
		private Integer nbr_perso_classe;
		private Long iddirigeant;
		//Equipe
		private Integer nbr_equipe,
				nbr_eq_persos_equipe,
				nbr_eq_persos_classe,
				nbr_eq_persos_joueur;
		//CPS
		private Integer nbr_persos_total;
		
		public void set(RecSetProprietes rsp) {
			idmap = rsp.getIdmap();
			visibilite = rsp.getVisibilite();
			typecombat = rsp.getTypecombat();
			nbr_perso_classe = rsp.getNbr_perso_classe();
			iddirigeant = rsp.getIddirigeant();
			nbr_equipe = rsp.getNbr_equipe();
			nbr_eq_persos_equipe = rsp.getNbr_eq_persos_equipe();
			nbr_eq_persos_classe = rsp.getNbr_eq_persos_classe();
			nbr_eq_persos_joueur = rsp.getNbr_eq_persos_joueur();
			nbr_persos_total = rsp.getNbr_persos_total();
		}

		public Integer getIdmap() {
			return idmap;
		}

		public Integer getVisibilite() {
			return visibilite;
		}

		public Integer getTypecombat() {
			return typecombat;
		}

		public Integer getNbr_perso_classe() {
			return nbr_perso_classe;
		}

		public Long getIddirigeant() {
			return iddirigeant;
		}

		public Integer getNbr_equipe() {
			return nbr_equipe;
		}

		public Integer getNbr_eq_persos_equipe() {
			return nbr_eq_persos_equipe;
		}

		public Integer getNbr_eq_persos_classe() {
			return nbr_eq_persos_classe;
		}

		public Integer getNbr_eq_persos_joueur() {
			return nbr_eq_persos_joueur;
		}

		public Integer getNbr_persos_total() {
			return nbr_persos_total;
		}

	}

}
