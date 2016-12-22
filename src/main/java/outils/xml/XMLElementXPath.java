/*
 * 
 * 
 * 
 */
package outils.xml;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;

/**
 * XMLElementXPath.java
 *
 */
public class XMLElementXPath {

	private static final XPathFactory XPF = XPathFactory.newInstance();
	private static final XPath XPATH = XPF.newXPath();

	private final Element root;

	public XMLElementXPath(Element root) {
		this.root = root;
	}

	public <T> T get(String exp, Class<T> c) throws XPathExpressionException, XPathEmptyValueException {
		String v = XPATH.evaluate(exp, this.root);
		if (v == null || v.length() == 0) {
			throw new XPathEmptyValueException(exp, c);
		}
		
		T ret;
		if (c.equals(Integer.class)) {
			ret = c.cast(Integer.parseInt(v));
		} else {
			ret = c.cast(v);
		}
		
		return ret;
	}
}
