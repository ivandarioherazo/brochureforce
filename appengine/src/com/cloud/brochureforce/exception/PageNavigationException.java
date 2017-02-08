/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.exception;

/**
 * This exception occurs if page navigation system fails at some point of the program.
 *
 * @author LIA Solutions SAS / Permalink Group.
 */
public class PageNavigationException extends RuntimeException {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
   * Constructs an instance of <code>PageNavigationException</code> with the
   * specified detail message.
   *
   * @param msg the detail message.
   */
  public PageNavigationException(String msg) {
    super(msg);
  }
}
