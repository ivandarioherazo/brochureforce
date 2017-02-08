/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.dataaccess.entity;

import java.util.UUID;

/**
 * This class represents an active user session.
 *
 * @author LIA Solutions SAS.
 */
public class SessionObject {
  public static String SESSION_ATTRIBUTE_NAME = "com.cloud.brochureforce.loggedUser";

  private final UUID sessionId;
  private final DatabaseUser userData;
  
  public SessionObject(UUID sessionId, DatabaseUser userData) {
    this.sessionId = sessionId;
    this.userData = userData;
  }

  /**
   * Get the value of sessionId
   *
   * @return the value of sessionId
   */
  public UUID getSessionId() {
    return sessionId;
  }

  /**
   * Get the value of userData
   *
   * @return the value of userData
   */
  public DatabaseUser getUserData() {
    return userData;
  }
}
