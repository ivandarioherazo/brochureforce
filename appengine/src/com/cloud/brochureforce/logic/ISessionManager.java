/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.logic;

import com.cloud.brochureforce.client.cdi.annotation.BusinessLogic;
import java.util.UUID;
import com.cloud.brochureforce.dataaccess.entity.DatabaseUser;
import com.cloud.brochureforce.dataaccess.entity.SessionObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This interface declares the methods to enable the DB session system functionality.
 *
 * @author LIA Solutions SAS.
 */
@BusinessLogic
public interface ISessionManager {
  
  /**
   * Creates a new session by using the typed-in credentials.
   * 
   * @param profileType One of the values of the {@link com.cloud.brochureforce.dataaccess.entity.ProfileType
   * ProfileType} enumeration.
   * @param companyId Provider company ID.
   * @param userId The Id of the validated profile.
   * @param password The profile password.
   */
  public void createSession(String profileType, String companyId, String userId, String password);

  /**
   * Destroys the active session.
   *
   * @param sessionId An UUID object to uniquely identify this session.
   * @param loggedUser Object which represents the logged user.
   */
  public void destroySession(UUID sessionId, DatabaseUser loggedUser);

  /**
   * It validates that a valid UUID has been generated for the current session.
   *
   * @param uuidStr String representation of an UUID.
   * @return A validated UUID object.
   */
  public UUID validateSessionId(String uuidStr);

  /**
   * Current session object.
   *
   * @return Active HTTP session.
   */
  public HttpSession getSession();

  /**
   * Current HTTP request.
   *
   * @return Active HTTP request.
   */
  public HttpServletRequest getRequest();

  /**
   * Current HTTP request.
   *
   * @return Active HTTP request.
   */
  public HttpServletResponse getResponse();

  /**
   * It destroys the current active session.
   */
  public void closeSession();

  /**
   * Current logged user object. It can be a company's or a salesperson's
   * profile.
   *
   * @return An instance of {@link SessionObject SessionObject} class, which
   * represents an active session.
   */
  public SessionObject getLoggedUser();
  
  /**
   * Returns the profile type of the logged user.
   * Used to define the dashboard options to display.
   * 
   * @return String representation of one of the {@link ProfileType ProfileType} enum.
   */
  public String getLoggedUserProfileType();

  /**
   * Sets the response headers to avoid the browser to use the cache.
   *
   * @param response HTTP response object.
   */
  public void cleanCache(HttpServletResponse response);

  /**
   * Destroys all active sessions in the database system.
   */
  public void shutdownDBSessionSystem();
  
  //////////////////////////////////////////////////////////////////////////////
  // LOGIN PROPERTIES
  //////////////////////////////////////////////////////////////////////////////
  // Company ID.
//  public String getCompanyId();
//  public void setCompanyId(String companyId);
//  
//  // User ID.
//  public String getUserId();
//  public void setUserId(String userId);
//  
//  // Password.
//  public String getPassword();
//  public void setPassword(String password);
  //////////////////////////////////////////////////////////////////////////////
}
