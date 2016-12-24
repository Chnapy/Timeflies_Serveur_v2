/*
 * 
 * 
 * 
 */
package outils.log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;
import main.Const;

/**
 * MyLogger.java
 *
 */
public final class MyLogger {
	
	private final static String DATE_PATTERN_DIR = "ddMMYYYY";
	private final static String DATE_PATTERN_FILE = "ddMMYYYY_HHmmss";

	private static FileHandler fileTxt;
	private static SimpleFormatter formatterTxt;

	private static FileHandler fileXML;
	private static Formatter formatterXML;

	public static void init() throws IOException {

		// get the global logger to configure it
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
		Date d = new Date(System.currentTimeMillis());
		String date_dir = new SimpleDateFormat(DATE_PATTERN_DIR).format(d);
		String date_file = new SimpleDateFormat(DATE_PATTERN_FILE).format(d);
		
		String path_dir = Const.PATH_LOGS + date_dir + "/";
		String path_file = path_dir + "log_" + date_file;
		
		Files.createDirectories(Paths.get(path_dir));
		fileTxt = new FileHandler(path_file + ".txt");
		fileXML = new FileHandler(path_file + ".xml", 8096, 1, true);

		// create a TXT formatter
		formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		logger.addHandler(fileTxt);

		// create an XML formatter
		formatterXML = new XMLFormatter();
		fileXML.setFormatter(formatterXML);
		logger.addHandler(fileXML);

	}

	public static void stop() {
		fileTxt.close();
		fileXML.close();
	}

	private MyLogger() {
	}

}
