package com.cloud.brochureforce.client.jsf;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.cloud.brochureforce.dataaccess.entity.Visit;
import com.cloud.brochureforce.logic.IVisitsHandler;

@ManagedBean(name="jsfbExample")
public class ExampleBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IVisitsHandler visitsHandler;
	
	@PostConstruct
	public void init() {
		System.out.println("ExampleBean - Injection works! visitsHandler = " + visitsHandler); // It works.
	}

	/**
	 * Method to test EL engine processing with parameters. 
	 * @param param
	 * @return
	 */
	public void insertVisit() {
		this.visitsHandler.insertVisit();
	}
	
	public List<Visit> getPageVisits() {
		return this.visitsHandler.getPageVisits();
	}
	

	/**
	 * @return the currentVisit
	 */
	public String getCurrentVisit() {
		return this.visitsHandler.getCurrentVisit();
	}

	/**
	 * @param currentVisit
	 *            the currentVisit to set
	 */
	public void setCurrentVisit(String currentVisit) {
		this.visitsHandler.setCurrentVisit(currentVisit);
	}
}
