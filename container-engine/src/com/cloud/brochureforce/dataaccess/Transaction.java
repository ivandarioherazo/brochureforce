package com.cloud.brochureforce.dataaccess;

import javax.persistence.EntityManager;

/**
 * This interface encapsulates a database transaction.
 * 
 * @author LIA Solutions SAS.
 *
 * @param <T> Type of data that should be returned after the transaction is completed.
 */
interface Transaction<T> {
	
	/**
	 * Implementation of a database transaction.
	 * 
	 * @param em The current entity manager.
	 * @param parameters Array with the parameters this transaction may need to get completed.
	 * @return This method may return:<ol>
	 * <li>A single entity.
	 * <li>A collection of entities.
	 * <li>A piece of data (a string, number, boolean, etc).
	 * <li><code>null</code> when DDL or DML operations are executed.</ol>
	 */
	public T run(EntityManager em, Object...parameters);
}
