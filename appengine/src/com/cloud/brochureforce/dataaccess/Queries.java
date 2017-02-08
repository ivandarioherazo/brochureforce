package com.cloud.brochureforce.dataaccess;

/**
 * This enumeration gives an identifier to all JPQL or native queries of the application. 
 * 
 * @author LIA Solutions SAS.
 */
public enum Queries {
	JPQL_VISITS,
  
  /**
   * Get all the remaining active sessions from the database session system.
   */
  JPQL_DB_SESSIONS,
  
  /**
   * Validates the user credentials at login time.
   */
  CHECK_LOGIN_CREDENTIALS,
  
  /**
   * This procedure validates the current database session.
   */
  PROCEDURE_SESSION_VALIDATION_SYSTEM;
}
