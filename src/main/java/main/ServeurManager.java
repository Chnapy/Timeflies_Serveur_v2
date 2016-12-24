/*
 * 
 * 
 * 
 */
package main;

import BDD.BDD;
import console.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathExpressionException;
import netserv.NetworkServeur;
import outils.log.MyLogger;
import org.xml.sax.SAXException;
import outils.Utils;
import outils.xml.XPathEmptyValueException;

/**
 * ServeurManager.java
 *
 */
public class ServeurManager implements StartNStop {
	
	private static final Logger LOGGER = Logger.getGlobal();
	private static final Level LOG_LEVEL = Level.ALL;
	
	private static ServeurManager serveur;
	
	private final Console console;
	private final NetworkServeur netServ;
	
	private ServeurManager() {
		this.console = new Console(this);
		this.netServ = new NetworkServeur();
	}

	@Override
	public void onStart() throws IOException, SAXException, XPathExpressionException, XPathEmptyValueException, SQLException, Exception {
		
		BDD.init();
		
		this.console.start();
		this.netServ.start();
		
	}
	
	@Override
	public void onStop() {
//		throw new Error();
		
		this.netServ.stop();
		this.console.stop();
		BDD.stop();
		
		Utils.EXEC.shutdown();
		
		LOGGER.info("Arrêt du serveur terminé");
		
		MyLogger.stop();
	}

	public static void main(String[] args) {
		try {
			MyLogger.init();
			LOGGER.setLevel(LOG_LEVEL);
			
			serveur = new ServeurManager();
			serveur.start();
		} catch (Exception ex) {
			Logger.getGlobal().log(Level.SEVERE, null, ex);
		}
	}

}
