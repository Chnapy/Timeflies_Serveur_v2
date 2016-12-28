/*
 * 
 * 
 * 
 */
package client;

/**
 * DonneesClient.java
 * 
 */
public class DonneesClient {
	
	private final InfosCompte infosCompte;
	private int nbrCombatJoues;
	private int nbrVictoires;
	private int nbrDefaites;
	
	public DonneesClient(String pseudo, String mail) {
		this(pseudo, mail, -1, -1, -1);
	}
	
	public DonneesClient(String pseudo, String mail, 
			int nbrCombatJoues, int nbrVictoires, int nbrDefaites) {
		this.infosCompte = new InfosCompte(pseudo, mail);
		this.nbrCombatJoues = nbrCombatJoues;
		this.nbrVictoires = nbrVictoires;
		this.nbrDefaites = nbrDefaites;
	}

	public InfosCompte getInfosCompte() {
		return infosCompte;
	}

	public int getNbrCombatJoues() {
		return nbrCombatJoues;
	}

	public void setNbrCombatJoues(int nbrCombatJoues) {
		this.nbrCombatJoues = nbrCombatJoues;
	}

	public int getNbrVictoires() {
		return nbrVictoires;
	}

	public void setNbrVictoires(int nbrVictoires) {
		this.nbrVictoires = nbrVictoires;
	}

	public int getNbrDefaites() {
		return nbrDefaites;
	}

	public void setNbrDefaites(int nbrDefaites) {
		this.nbrDefaites = nbrDefaites;
	}

}
