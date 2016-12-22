/*
 * 
 * 
 * 
 */
package main;

import BDD.BDD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathExpressionException;
import log.MyLogger;
import org.xml.sax.SAXException;
import outils.xml.XPathEmptyValueException;

/**
 * ServeurManager.java
 *
 */
public class ServeurManager {
	
	private static final Level LOG_LEVEL = Level.ALL;

	private static void start() throws IOException, SAXException, XPathExpressionException, XPathEmptyValueException, SQLException {

		BDD.init();
		
	}

	public static void main(String[] args) {
		try {
			MyLogger.init();
			
			Logger.getGlobal().setLevel(LOG_LEVEL);

			ServeurManager.start();

		} catch (Exception ex) {
			Logger.getGlobal().log(Level.SEVERE, null, ex);
		}
	}
	
	private ServeurManager() {
	}

}
