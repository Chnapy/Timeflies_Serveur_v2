/*
 * 
 * 
 * 
 */
package outils.xml;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * MyXMLParser.java
 *
 */
public final class MyXMLParser {

	private static final DocumentBuilder DBUILDER = getDBuilder();

	public static XMLElementXPath getXML(String path) throws SAXException, IOException {
		return getXML(new File(path));
	}

	public static XMLElementXPath getXML(File file) throws SAXException, IOException {
		Document doc = DBUILDER.parse(file);
		Element root = doc.getDocumentElement();
		root.normalize();
		return new XMLElementXPath(root);
	}

	private static DocumentBuilder getDBuilder() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			return dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException ex) {
			Logger.getLogger(MyXMLParser.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	private MyXMLParser(){
	}

}
