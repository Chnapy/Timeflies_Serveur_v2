/*
 * 
 * 
 * 
 */
package outils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import main.Const;

/**
 * Utils.java
 *
 */
public class Utils {

	public static final ExecutorService EXEC
			= Executors.newFixedThreadPool(Const.NBR_THREAD_MAX);

}
