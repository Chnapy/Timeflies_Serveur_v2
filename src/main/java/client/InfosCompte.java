/*
 * 
 * 
 * 
 */
package client;

/**
 * InfosCompte.java
 * 
 */
public class InfosCompte {
	
	private final String pseudo;
	private final String mail;

	public InfosCompte(String pseudo, String mail) {
		this.pseudo = pseudo;
		this.mail = mail;
	}

	public String getPseudo() {
		return pseudo;
	}

	public String getMail() {
		return mail;
	}

}
