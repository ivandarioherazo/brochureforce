/**
 * 
 */
package com.cloud.brochureforce.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cloud.brochureforce.client.cdi.annotation.BusinessLogic;
import com.cloud.brochureforce.dataaccess.PersistenceManager;
import com.cloud.brochureforce.dataaccess.Queries;
import com.cloud.brochureforce.dataaccess.entity.Visit;
import java.net.InetAddress;
import java.net.UnknownHostException;
import com.cloud.brochureforce.logic.IVisitsHandler;

/**
 * Business logic to implement the visits handler.
 * 
 * @author LIA Solutions SAS
 */
@BusinessLogic
public class VisitsHandlerImpl implements IVisitsHandler {
	private String currentVisit;

  @Override
	public void insertVisit() {
		PersistenceManager pMgr = PersistenceManager.getInstance();
		Visit newVisit = new Visit();
    try {
      newVisit.setUserIp(InetAddress.getLocalHost().getHostName());
    } catch (UnknownHostException ex) {
      newVisit.setUserIp("Unknown");
    }
		newVisit.setTimestamp(new Date(System.currentTimeMillis()));
		pMgr.insert(newVisit);
		this.currentVisit = newVisit.toString();
	}
	
	@SuppressWarnings("rawtypes")
  @Override
	public List<Visit> getPageVisits() {
		PersistenceManager pMgr = PersistenceManager.getInstance();
		List<Visit> visitsList = new ArrayList<>(); 
		List visits = pMgr.executeJpqlQuery(Queries.JPQL_VISITS);
		for (Object v : visits) {
			visitsList.add((Visit) v);
		}
		return visitsList;
	}
	
	/**
	 * @return the currentVisit
	 */
  @Override
	public String getCurrentVisit() {
		return currentVisit;
	}

	/**
	 * @param currentVisit
	 *            the currentVisit to set
	 */
  @Override
	public void setCurrentVisit(String currentVisit) {
		this.currentVisit = currentVisit;
	}	
}
