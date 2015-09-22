/**
 *
 */
package de.meggsimum.w3w;

/**
 * Generic exception class to encapsulate What3Words wrapper errors
 * 
 * @author Christian Mayer, meggsimum
 */
public class What3WordsException extends Exception {

	private static final long serialVersionUID = -813672636078407940L;

	public What3WordsException(Exception e) {
		super(e);
	}

	public What3WordsException(String string) {
		super(string);
	}

	public What3WordsException(String message, Throwable cause) {
		super(message, cause);
	}
}
