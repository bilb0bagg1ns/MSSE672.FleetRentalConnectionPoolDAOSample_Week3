package com.fcpdsample.model.services.exception;

/**
 *
 * @author
 */
public class ConnectionPoolCreationException extends Exception {

  public ConnectionPoolCreationException(final String inMessage) {
    super(inMessage);
  }

  public ConnectionPoolCreationException(final String inMessage, final Throwable inNestedException) {
    super(inMessage, inNestedException);
  }

}
