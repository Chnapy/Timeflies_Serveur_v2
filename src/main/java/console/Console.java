/*
 * 
 * 
 * 
 */
package console;

import console.cmd.ConsoleHelpCmd;
import console.cmd.ConsoleStopCmd;
import console.cmd.ConsoleThreadCmd;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import main.ServeurManager;
import main.StartNStop;
import outils.Utils;

/**
 * Console.java
 *
 */
public class Console implements StartNStop {

	private ConsoleCmd[] getAllCmd() {
		return new ConsoleCmd[]{
			new ConsoleHelpCmd(this),
			new ConsoleStopCmd(serveur),
			new ConsoleThreadCmd()
		};
	}
	
	private final Map<String, ConsoleCmd> COMMANDES = new HashMap();

	private final ServeurManager serveur;
	private final ConsoleRunnable cRun;
	private Future cRunFuture;
	private boolean started;

	public Console(ServeurManager serveur) {
		this.serveur = serveur;
		this.cRun = new ConsoleRunnable(COMMANDES);
		this.started = false;
		for (ConsoleCmd cmd : getAllCmd()) {
			COMMANDES.put(cmd.getCommande(), cmd);
		}
	}

	@Override
	public void onStart() {
		if (this.started) {
			throw new UnsupportedOperationException("Console active");
		}
		this.cRunFuture = Utils.EXEC.submit(this.cRun);

		this.started = true;
	}

	@Override
	public void onStop() {
		if (!this.started) {
			throw new UnsupportedOperationException("Console non active");
		}
		this.cRun.stop();
		this.cRunFuture.cancel(true);
		this.started = false;
	}
	
	public Map<String, ConsoleCmd> getCommandes() {
		return this.COMMANDES;
	}

}
