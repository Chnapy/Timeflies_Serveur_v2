/*
 * 
 * 
 * 
 */
package console.cmd;

import console.ConsoleCmd;
import console.ConsoleDisplayer;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;
import outils.Utils;

/**
 * ConsoleThreadCmd.java
 *
 */
public class ConsoleThreadCmd extends ConsoleCmd {

	public static final String CMD = "thread";

	public ConsoleThreadCmd() {
		super(CMD);
	}

	@Override
	protected void lancer(Scanner sc, ConsoleDisplayer displayer) throws ClassCastException {
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) Utils.EXEC;
		displayer.printInfo(
				getThreadEnvoyes(tpe) + "\n"
				+ getThreadTermines(tpe) + "\n"
				+ getThreadEnCours(tpe)
		);
	}

	private String getThreadEnvoyes(ThreadPoolExecutor tpe) {
		return "Threads envoyés: " + tpe.getTaskCount();
	}

	private String getThreadTermines(ThreadPoolExecutor tpe) {
		return "Threads terminés: " + tpe.getCompletedTaskCount();
	}

	private String getThreadEnCours(ThreadPoolExecutor tpe) {
		return "Threads en cours (approx.): "
				+ (tpe.getTaskCount() - tpe.getCompletedTaskCount());
	}

}
