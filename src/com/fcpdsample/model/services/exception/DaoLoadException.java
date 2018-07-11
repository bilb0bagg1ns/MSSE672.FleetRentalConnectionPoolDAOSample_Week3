/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcpdsample.model.services.exception;

public class DaoLoadException extends Exception {

  public DaoLoadException(final String inMessage, final Throwable inNestedException) {
    super(inMessage, inNestedException);
  }
}
