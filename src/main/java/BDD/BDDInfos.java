/*
 * 
 * 
 * 
 */
package BDD;

import java.io.IOException;
import javax.xml.xpath.XPathExpressionException;
import main.Const;
import org.xml.sax.SAXException;
import outils.xml.MyXMLParser;
import outils.xml.XMLElementXPath;
import outils.xml.XPathEmptyValueException;

/**
 * BDDInfos.java
 * 
 */
class BDDInfos {
	
	public final String hote;
	public final int port;
	public final String base;
	
	public final String user;
	public final String mdp;

	public BDDInfos() throws IOException, SAXException, XPathExpressionException, XPathEmptyValueException {
		XMLElementXPath root = MyXMLParser.getXML(Const.PATH_BDD_INFOS);
		hote = root.get("/root/adresse/hote", String.class);
		port = root.get("/root/adresse/port", Integer.class);
		base = root.get("/root/adresse/base", String.class);
		user = root.get("/root/logs/user", String.class);
		mdp = root.get("/root/logs/mdp", String.class);
	}

}
