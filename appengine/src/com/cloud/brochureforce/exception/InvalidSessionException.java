/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.exception;

import javax.persistence.PersistenceException;

/**
 * Thrown if session doesn't exist on the DB session system.
 *
 * @author LIA Solutions SAS / Permalink Group.
 */
public class InvalidSessionException extends PersistenceException {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
   * Creates a new instance of <code>DBSessionSystemException</code>
   * without detail message.
   * 
   * @param msg Session state error message.
   */
  public InvalidSessionException(String msg) {
    super(msg);
  }
}
