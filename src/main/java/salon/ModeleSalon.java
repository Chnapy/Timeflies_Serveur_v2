/*
 * 
 * 
 * 
 */
package salon;

import BDD.BDD;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Const;
import netserv.Modele;
import outils.json.MyJSONParser;

/**
 * ModeleSalon.java
 * 
 */
public class ModeleSalon extends Modele {
	
	private static final String QUERY_GET_MAP = "SELECT chemin FROM map WHERE id_map=?";
	
	public Optional<ClasseMap> getClasseMapFromId(int idmap) {
		
		String chemin;
		
		try (Connection connection = BDD.getConnection();
				PreparedStatement st = connection.prepareStatement(QUERY_GET_MAP)) {

			st.setInt(1, idmap);

			try (ResultSet rs = st.executeQuery()) {
				if(!rs.next()) {
					return Optional.empty();
				}

				chemin = rs.getString("chemin");
			}

			connection.commit();
		} catch (SQLException ex) {
			Logger.getLogger(ModeleSalon.class.getName()).log(Level.SEVERE, null, ex);
			return Optional.empty();
		}
		
		chemin = Const.PATH_MAPS + chemin;
		
		try {
			return getClasseFromFile(new File(chemin));
		} catch (IOException ex) {
			Logger.getLogger(ModeleSalon.class.getName()).log(Level.SEVERE, null, ex);
			return Optional.empty();
		}
	}
	
	private Optional<ClasseMap> getClasseFromFile(File file) throws IOException {
		ClasseMap cm = MyJSONParser.fileToObject(file, ClasseMap.class);
		
		System.out.println(cm);
		
		return Optional.of(cm);
	}

}
