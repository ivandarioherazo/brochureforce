package com.cloud.brochureforce.dataaccess;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This class is part of the core of the JPA engine. It initializes the
 * persistence context objects and provides methods to handle database
 * transactions with JPA 2.0.
 *
 * @author LIA Solutions SAS.
 */
final class PersistenceInitializer {

  private static boolean isInitialized;
  private static EntityManagerFactory emfInstance;
  private static EntityManager emInstance;

  /*
	 * Initializes the persistence context. It's invoked by the persistence manager on start-up
	 * and it should be invoked only once. Subsequent invocations have no effect.
   */
  @SuppressWarnings("rawtypes")
  static void initialize(Map properties) {
    if (!isInitialized) {
      if (properties == null) {
        emfInstance = Persistence.createEntityManagerFactory("BrochureForcePU");
      } else {
        emfInstance = Persistence.createEntityManagerFactory("BrochureForcePU", properties);
      }
      emInstance = emfInstance.createEntityManager();
      isInitialized = true;
    }
  }

  /**
   * Gets the singleton entity manager instance to query the database.
   *
   * @return The entity manager instance.
   */
  static EntityManager getEntityManager() {
    return emInstance;
  }

  /*
	 * Releases all resources taken by the persistence context objects. It's
	 * invoked by the persistence manager when the application is shut off.
   */
  static void shutdown() {
    // Singleton design pattern allows to implement the finalize method to .
    try {
      emInstance.close();
    } catch (Exception e) {
      // Exit silently...
    }
    try {
      emfInstance.close();
    } catch (Exception e) {
      // Exit silently...
    }
  }
}
