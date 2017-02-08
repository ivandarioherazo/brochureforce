/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.client.listener;

import com.cloud.brochureforce.logic.ISessionManager;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * This listener makes sure to destroy all active sessions in the database
 * session system when the application is shut down.
 *
 * @author LIA Solutions SAS.
 */
public class DBSessionSystemInitializationListener implements ServletContextListener {

  @Inject
  private ISessionManager sessionManager;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    sce.getServletContext().log("DBSessionSystemInitializationListener - SESSION SYSTEM INITIALIZED!");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    try {
      this.sessionManager.shutdownDBSessionSystem();
      sce.getServletContext().log("DBSessionSystemInitializationListener - ALL SESSIONS DESTROYED!");
    } catch (Exception e) {
      sce.getServletContext().log("DBSessionSystemInitializationListener - ERROR: " + e.getMessage());
    }
  }
}
