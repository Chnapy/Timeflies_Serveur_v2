/*
 * 
 * 
 * 
 */

package main;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * StartNStop
 * Interface
 */
public interface StartNStop {
	
	public default void start() throws Exception {
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "D\u00e9marrage de {0} ...", this.getClass().getName());
		this.onStart();
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "D\u00e9marrage de {0} terminé.", this.getClass().getName());
	}
	
	public void onStart() throws Exception;
	
	public default void stop() {
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, 
				"Arr\u00eat de {0} ...", this.getClass().getName());
		this.onStop();
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, 
				"Arr\u00eat de {0} terminé.", this.getClass().getName());
	}
	
	public void onStop();

}
