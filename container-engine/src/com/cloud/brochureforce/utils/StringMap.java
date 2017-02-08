package com.cloud.brochureforce.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Thin wrapper over <code>java.util.HashMap<String, String></code> class
 * to define string properties only.
 * 
 * @author LIA Solutions SAS.
 */
public class StringMap extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		StringBuilder properties = new StringBuilder();
		properties.append("\n****************************************");
		properties.append("\n*              PROPERTIES              *");
		properties.append("\n****************************************");
		Set<Map.Entry<String, String>> entries = this.entrySet();
		for(Map.Entry<String, String> entry : entries) {
			properties.append("\n" + entry.getKey() + " = " + entry.getValue());
		}
		properties.append("\n****************************************");
		return properties.toString();
	}
}
