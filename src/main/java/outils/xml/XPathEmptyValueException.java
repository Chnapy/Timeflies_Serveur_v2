/*
 * 
 * 
 * 
 */
package outils.xml;

/**
 * NewClass.java
 *
 */
public class XPathEmptyValueException extends Exception {

	public XPathEmptyValueException(String exp, Class c) {
		super("Value empty ["
				+ "Expression: " + exp
				+ ", ClassExpected: " + c.getName() + "]"
		);
	}

}
