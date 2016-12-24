/*
 * 
 * 
 * 
 */
package console;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ConsoleDisplayer.java
 *
 */
public class ConsoleDisplayer {

	private static final Logger LOGGER = Logger.getLogger(ConsoleDisplayer.class.getName());

	static {
		for (Handler d : LOGGER.getHandlers()) {
			if (d instanceof FileHandler) {
				LOGGER.removeHandler(d);
			}
		}
		LOGGER.setLevel(Level.ALL);
	}

	public ConsoleDisplayer() {
	}

	public void setLoggerLevel(Level lvl) {
		LOGGER.setLevel(lvl);
	}

	public void print(Level lvl, String str) {
		LOGGER.log(lvl, str);
	}

	public void printFine(String str) {
		this.print(Level.FINE, str);
	}

	public void printInfo(String str) {
		this.print(Level.INFO, str);
	}

	public void printWarn(String str) {
		this.print(Level.WARNING, str);
	}

	public void printErr(String str) {
		this.print(Level.SEVERE, str);
	}

}
