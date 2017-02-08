package com.cloud.brochureforce.logic;

import java.util.List;

import com.cloud.brochureforce.client.cdi.annotation.BusinessLogic;
import com.cloud.brochureforce.dataaccess.entity.Visit;

@BusinessLogic
public interface IVisitsHandler {
	public void insertVisit();
	public List<Visit> getPageVisits();
	
	// Propiedades
	public String getCurrentVisit();
	public void setCurrentVisit(String currentVisit);
}
