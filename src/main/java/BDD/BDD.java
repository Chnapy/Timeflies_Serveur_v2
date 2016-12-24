/*
 * 
 * 
 * 
 */
package BDD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathExpressionException;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;
import org.xml.sax.SAXException;
import outils.xml.XPathEmptyValueException;

/**
 * BDD.java
 *
 */
public class BDD {
	
	private static boolean INITIALISED = false;

	private static final Jdbc3PoolingDataSource SOURCE = new Jdbc3PoolingDataSource();

	public static void init() throws IOException, SAXException, XPathExpressionException, XPathEmptyValueException {
		if(INITIALISED) {
			throw new UnsupportedOperationException("BDD déjà initialisée");
		}
		BDDInfos bddInfos = new BDDInfos();
		SOURCE.setDataSourceName("A Data Source");
		SOURCE.setServerName(bddInfos.hote);
		SOURCE.setPortNumber(bddInfos.port);
		SOURCE.setDatabaseName(bddInfos.base);
		SOURCE.setUser(bddInfos.user);
		SOURCE.setPassword(bddInfos.mdp);
		INITIALISED = true;
	}

	public static Connection getConnection() {
		try {
			Connection con = SOURCE.getConnection();
			con.setAutoCommit(false);
//			throw new SQLException();
			return con;
		} catch (SQLException ex) {
			Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static void stop() {
		SOURCE.close();
	}
	
	private BDD() {
	}

}
