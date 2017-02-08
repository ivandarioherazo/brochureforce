package com.cloud.brochureforce.dataaccess;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * This class is the core of the JPA engine. It initializes it and grants access
 * to persistence operations.
 *
 * @author LIA Solutions SAS.
 */
public class PersistenceManager {

  private enum TransactionType {
    INSERT, UPDATE, DELETE
  }

  // Loads the file which contains all JPQL queries used by the application.
  private class QueryLoader {

    private ResourceBundle rb;

    // It can't be instantiated.
    private QueryLoader() {
      this.initJpqlLoader();
    }

    private void initJpqlLoader() {
      this.rb = ResourceBundle.getBundle("com.cloud.brochureforce.dataaccess.Queries");
    }

    /**
     * Gets the JPQL query identified with the given key.
     *
     * @param key JPQL query ID.
     * @return The JPQL query.
     */
    public String getQuery(Queries key) {
      return this.rb.getString(key.toString());
    }
  }

  private class DMLTransactionImpl<T> implements Transaction<T> {
    private final TransactionType trType;
    
    public DMLTransactionImpl(TransactionType trType) {
      this.trType = trType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T run(EntityManager em, Object... params) {
      T entity2 = (T) params[0];
      PersistenceManager.this.executeDMLOperation(entity2, this.trType);
      return null;
    }
  }

  private final QueryLoader qLoader;

  private static PersistenceManager INSTANCE;

  // It must NOT be instantiated.
  private PersistenceManager() {
    this.qLoader = new QueryLoader();
  }

  /*
   * @param transaction The transaction to be run. This is how it's used:
   *
   * <pre>
   * TransactionHandler.<String>executeTransaction(new Transaction<String>() {
   *   public String run(EntityManager em, params) {
   *     // do something with the EntityManager...
   *     int digit = params[0]; // Digit "1".
   *     String helloWorld = params[1].toString(); // The string "Hello world".
   *
   *     // The transaction code goes here!
   *   }
   * }, 1, "Hello world");
   * </pre>
   *
   * @return The requested data (if any).
   */
  private static <T> T executeTransaction(Transaction<T> transaction, Object... parameters) {
    EntityTransaction tx = null;
    EntityManager emInstance = PersistenceInitializer.getEntityManager();
    try {
      tx = emInstance.getTransaction();
      tx.begin();
      T retVal = transaction.run(emInstance, parameters);
      emInstance.flush();
      tx.commit();
      return retVal;
    } catch (RuntimeException e) {
      if (tx != null && tx.isActive()) {
        tx.rollback();
      }
      System.out.print("TransactionHandler - error during transaction: " + transaction.getClass().getName()
        + e.getMessage());
      throw e;
    } finally {
      tx = null; // transaction has finished.
    }
  }

  /**
   * It invokes a database function using a native query.
   *
   * @param <T> The returned object type.
   * @param sqlFunctionId The SQL identifier (a native SELECT query which
   * invokes a stored function).
   * @param paramValues Parameters received by the function.
   * @return The value returned by the function.
   * @deprecated Cloud SQL didn't handle database functions by the time this
   * method was written. This method will remain deprecated, yet it won't be
   * removed, just in case that issue changes in future Cloud SQL versions.
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  public <T> T callDbFunction(Queries sqlFunctionId, Object... paramValues) {
    String sqlProc = this.qLoader.getQuery(sqlFunctionId);
    Query q = PersistenceInitializer.getEntityManager().createNativeQuery(sqlProc);
    for (int idx = 1; idx <= paramValues.length; idx++) {
      q.setParameter(idx, paramValues[idx - 1]);
    }
    T result;
    try {
      // It returns null if there's no result.
      result = (T) q.getSingleResult();
    } catch (NoResultException nre) {
      result = null;
    }
    return result;
  }

  /**
   * It invokes a stored procedure using a native query.
   *
   * @param sqlProcId The procedure to execute.
   * @param paramValues Parameters passed on to the procedure.
   */
  public void callStoredProcedure(Queries sqlProcId, Object... paramValues) {
    String sqlProc = this.qLoader.getQuery(sqlProcId);
    Query q = PersistenceInitializer.getEntityManager().createNativeQuery(sqlProc);
    for (int idx = 1; idx <= paramValues.length; idx++) {
      q.setParameter(idx, paramValues[idx - 1]);
    }
    try {
      q.executeUpdate();
    } catch (Exception e) {
      throw new PersistenceException(e.getMessage());
    }
  }

  /**
   * Executes a JPQL query. This methos is intended to get multiple records from
   * the database.
   *
   * @param jpqlId The JPQL query ID.
   * @param paramValues The parameters passed on to the query.
   * @return A collection of entities returned by the query.
   */
  @SuppressWarnings("rawtypes")
  public List executeJpqlQuery(Queries jpqlId, Object... paramValues) {
    Query q = PersistenceInitializer.getEntityManager().createQuery(this.qLoader.getQuery(jpqlId));
    if (paramValues != null && paramValues.length > 0) {
      for (int idx = 1; idx <= paramValues.length; idx++) {
        q.setParameter(idx, paramValues[idx - 1]);
      }
    }
    return q.getResultList();
  }

  private synchronized <T> void executeDMLOperation(T entity, TransactionType type) {
    EntityManager em = PersistenceInitializer.getEntityManager();
    try {
      switch (type) {
        case INSERT:
          em.persist(entity);
          break;
        case UPDATE:
          em.merge(entity);
          break;
        case DELETE:
          em.remove(entity);
          break;
      }
    } catch (Exception e) {
      throw new PersistenceException(e.getMessage());
    }
  }

  /**
   * Get the singleton instance of this class.
   *
   * @return The persistence manager instance.
   */
  public static PersistenceManager getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new PersistenceManager();
    }
    return INSTANCE;
  }

  /**
   * Gets the singleton entity manager instance to query the database.
   *
   * @return The entity manager instance.
   */
  public static EntityManager getEntityManager() {
    return PersistenceInitializer.getEntityManager();
  }

  /**
   * Initializes the persistence context. It's invoked on application start-up.
   *
   * @param properties Persistence context properties needed to initialize the
   * JPA factory.
   */
  @SuppressWarnings("rawtypes")
  public static void initialize(Map properties) {
    PersistenceInitializer.initialize(properties);
  }

  /**
   * Releases all resources taken by the persistence context objects. It's
   * invoked when the application is shut off.
   */
  public static void shutdown() {
    PersistenceInitializer.shutdown();
  }

  /**
   * This method is intended to fetch a single entity from the database.
   *
   * @param <T> The entity type that will be returned by this method.
   * @param entityClass The expected entity type to retrieve.
   * @param primaryKey The primary key which will be used to look up the entity.
   * @return The entity object or <code>null</code> if no entity is found with
   * the specified primary key object.
   */
  public <T> T findByPrimaryKey(Class<T> entityClass, Object primaryKey) {
    return PersistenceInitializer.getEntityManager().find(entityClass, primaryKey);
  }

  /**
   * Inserts an entity on the database.
   *
   * @param <E> Entity type to persist on the database.
   * @param entity The entity to be inserted.
   */
  public <E> void insert(E entity) {
    /*
    executeTransaction(new Transaction<Void>() {

      @SuppressWarnings("unchecked")
      @Override
      public Void run(EntityManager em, Object... params) {
        E entity2 = (E) params[0];
        PersistenceManager.this.executeDMLOperation(entity2, TransactionType.INSERT);
        return null;
      }
    }, entity);
    */
    executeTransaction(new DMLTransactionImpl<>(TransactionType.INSERT), entity);
  }

  /**
   * Updates an entity previously registered on the database.
   *
   * @param <E> Entity type to update on the database.
   * @param entity The entity to be updated.
   */
  public <E> void update(E entity) {
    /*
    executeTransaction(new Transaction<Void>() {

      @SuppressWarnings("unchecked")
      @Override
      public Void run(EntityManager em, Object... params) {
        E entity2 = (E) params[0];
        PersistenceManager.this.executeDMLOperation(entity2, TransactionType.UPDATE);
        return null;
      }
    }, entity);
    */
    executeTransaction(new DMLTransactionImpl<>(TransactionType.UPDATE), entity);
  }

  /**
   * Deletes an entity previously registered on the database.
   *
   * @param <E> Entity type to delede from the database.
   * @param entity The entity to be deleted.
   */
  public <E> void delete(E entity) {
    /*
    executeTransaction(new Transaction<Void>() {

      @SuppressWarnings("unchecked")
      @Override
      public Void run(EntityManager em, Object... params) {
        E entity2 = (E) params[0];
        PersistenceManager.this.executeDMLOperation(entity2, TransactionType.DELETE);
        return null;
      }
    }, entity);
    */
    executeTransaction(new DMLTransactionImpl<>(TransactionType.DELETE), entity);
  }
}
