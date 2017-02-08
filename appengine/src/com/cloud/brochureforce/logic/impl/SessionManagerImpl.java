/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.logic.impl;

import com.cloud.brochureforce.client.cdi.annotation.BusinessLogic;
import com.cloud.brochureforce.dataaccess.PersistenceManager;
import com.cloud.brochureforce.dataaccess.Queries;
import com.cloud.brochureforce.dataaccess.entity.Client;
import com.cloud.brochureforce.dataaccess.entity.Company;
import com.cloud.brochureforce.dataaccess.entity.DatabaseUser;
import com.cloud.brochureforce.dataaccess.entity.DbSession;
import com.cloud.brochureforce.dataaccess.entity.SessionObject;
import com.cloud.brochureforce.dataaccess.entity.ProfileType;
import com.cloud.brochureforce.dataaccess.entity.SellerCompany;
import com.cloud.brochureforce.dataaccess.entity.pk.DbSessionPK;
import com.cloud.brochureforce.exception.InvalidSessionException;
import com.cloud.brochureforce.logic.ISessionManager;
import java.util.List;
import java.util.UUID;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class implements the DB session system functionality.
 *
 * @author LIA Solutions SAS.
 */
@BusinessLogic
public class SessionManagerImpl implements ISessionManager {

  /*
   * Runs the loaded session data against the database session system to prevent
   * session forgery.
   *
   * @param jsession_id The current session UUID.
   * @param loggedUser Logged user Java object.
   * @throws InvalidSessionException if the session is not registered on the
   * database session system.
   */
  private void dbSessionValidation(UUID jsessionId, DatabaseUser loggedUser) {
    try {
      PersistenceManager.getInstance().callStoredProcedure(
        Queries.PROCEDURE_SESSION_VALIDATION_SYSTEM,
        jsessionId.toString(), loggedUser.toString());
    } catch (Exception e) {
      throw new InvalidSessionException(e.getMessage());
    }
  }

  /*
   * Registers a session on the database session system.
   *
   * @param jsession_id The new session UUID.
   * @param loggedUser Logged user data.
   */
  private void registerDBSession(UUID jsession_id, DatabaseUser loggedUser) {
    DbSession newDbSession = new DbSession(jsession_id.toString(), loggedUser.toString());
    try {
      PersistenceManager.getInstance().<DbSession>insert(newDbSession);
    } catch (Exception e) {
      throw new InvalidSessionException(e.getMessage());
    }
  }

  @Override
  public synchronized void createSession(String profileType, String companyId, String userId, String password) {
    PersistenceManager pm = PersistenceManager.getInstance();
    try {
      pm.callStoredProcedure(Queries.CHECK_LOGIN_CREDENTIALS, companyId, userId, password);
    } catch (Exception e) {
      throw new InvalidSessionException(e.getMessage());
    }
    DatabaseUser loggedUser = null;
    UUID sessionId = UUID.randomUUID();
    
    if (profileType.equals(ProfileType.CLIENT.toString())) {
      loggedUser = pm.<Client>findByPrimaryKey(Client.class, profileType);
    } else if (profileType.equals(ProfileType.COMPANY.toString())) {
      loggedUser = pm.<Company>findByPrimaryKey(Company.class, profileType);
    } else if (profileType.equals(ProfileType.SELLER_COMPANY.toString())) {
      loggedUser = pm.<SellerCompany>findByPrimaryKey(SellerCompany.class, profileType);
    }
    this.registerDBSession(sessionId, loggedUser);
    this.getSession().setAttribute(SessionObject.SESSION_ATTRIBUTE_NAME,
      new SessionObject(sessionId, loggedUser));
  }

  @Override
  public synchronized void destroySession(UUID jsession_id, DatabaseUser loggedUser) {
    DbSession currentSession = PersistenceManager.getInstance().<DbSession>findByPrimaryKey(
      DbSession.class, new DbSessionPK(jsession_id.toString(), loggedUser.toString()));
    if (currentSession != null) {
      try {
        PersistenceManager.getInstance().<DbSession>delete(currentSession);
      } catch (Exception e) {
        throw new InvalidSessionException(e.getMessage());
      } finally {
        this.getSession().removeAttribute(SessionObject.SESSION_ATTRIBUTE_NAME);
      }
    }
  }

  @Override
  public synchronized UUID validateSessionId(String uuidStr) {
    UUID uuid = null;
    try {
      uuid = UUID.fromString(uuidStr);
    } catch (Exception e) {
      throw new InvalidSessionException(e.getMessage());
    }
    return uuid;
  }

  @Override
  public synchronized HttpSession getSession() {
    return (HttpSession) FacesContext.getCurrentInstance()
      .getExternalContext().getSession(false);
  }

  @Override
  public synchronized HttpServletRequest getRequest() {
    return (HttpServletRequest) FacesContext.getCurrentInstance()
      .getExternalContext().getRequest();
  }

  @Override
  public synchronized HttpServletResponse getResponse() {
    return (HttpServletResponse) FacesContext.getCurrentInstance()
      .getExternalContext().getResponse();
  }

  @Override
  public synchronized void closeSession() {
    this.getSession().invalidate();
  }

  @Override
  public synchronized SessionObject getLoggedUser() {
    SessionObject user = (SessionObject) getSession().getAttribute(SessionObject.SESSION_ATTRIBUTE_NAME);

    // For security reasons, we have to make sure that this is still a valid session.
    this.validateSessionId(user.getSessionId().toString());
    this.dbSessionValidation(user.getSessionId(), user.getUserData());

    return user;
  }

  @Override
  public synchronized String getLoggedUserProfileType() {
    SessionObject user = (SessionObject) getSession().getAttribute(SessionObject.SESSION_ATTRIBUTE_NAME);
    return user.getUserData().getProfileType().toString();
  }

  @Override
  public synchronized void cleanCache(HttpServletResponse response) {
    if (response != null) {
      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
      response.setHeader("Pragma", "no-cache");
      response.setDateHeader("Expires", 0);
    }
  }

  @SuppressWarnings("rawtypes")
  @Override
  public synchronized void shutdownDBSessionSystem() {
    List sessions = PersistenceManager.getInstance().executeJpqlQuery(Queries.JPQL_DB_SESSIONS);
    for (Object session : sessions) {
      try {
        PersistenceManager.getInstance().<DbSession>delete((DbSession) session);
      } catch (Exception e) {
        throw new InvalidSessionException(e.getMessage());
      }
    }
  }
}
