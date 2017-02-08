/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.client.listener;

import com.cloud.brochureforce.dataaccess.entity.DatabaseUser;
import com.cloud.brochureforce.dataaccess.entity.SessionObject;
import com.cloud.brochureforce.logic.ISessionManager;
import java.util.UUID;
import javax.inject.Inject;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * This session listener removes an invalidated session of the database session system.
 *
 * @author LIA Solutions SAS.
 */
public class DBSessionDestroyerListener implements HttpSessionListener {

  @Inject
  private ISessionManager sessionManager;

  @Override
  public void sessionCreated(HttpSessionEvent se) {
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    SessionObject activeUser = (SessionObject) se.getSession().getAttribute(SessionObject.SESSION_ATTRIBUTE_NAME);
    if (activeUser != null) {
      UUID sessionId = activeUser.getSessionId();
      DatabaseUser userData = activeUser.getUserData();
      this.sessionManager.destroySession(sessionId, userData);
      System.out.println("DBSessionDestroyerListener - SESSION WITH UUID " +
        activeUser.getSessionId().toString() + " HAS BEEN DESTROYED!");
    } else {
      System.out.println(
        "DBSessionDestroyerListener - TRACKING INVALIDATED SESSION." +
        se.getSession().getId());
    }
  }
}
