package org.llrp.ltk.net;

/**
	 * LLRPConnectionAttemptFailedException is thrown whenever a connection with the reader could not be established
 */

public class LLRPConnectionAttemptFailedException extends Exception{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1335104701752692135L;

	public LLRPConnectionAttemptFailedException(final String message) {
	        super(message);
	    }
}
