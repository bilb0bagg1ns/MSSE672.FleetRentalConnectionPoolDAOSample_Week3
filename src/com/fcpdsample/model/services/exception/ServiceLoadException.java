
package com.fcpdsample.model.services.exception;


public class ServiceLoadException extends Exception {
	    public ServiceLoadException(final String inMessage, final Throwable inNestedException)
    {
        super(inMessage, inNestedException);
    }
}
