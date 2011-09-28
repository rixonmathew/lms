package com.rixon.lms.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.rixon.lms.util.ResourceBundleKeys;

/**
 * This is a view Resolver class that provides the view based on the name. This is used in 
 * conjuction with the List 
 * @author 229921
 *
 */
public class ViewProvider {

	//Constants for the various Panels  in the application	
	
	private static Map<String,JPanel> viewMap = new HashMap<String, JPanel>();
	
	static{
	   //TODO add entries to the map
		viewMap.put(ResourceBundleKeys.SEARCH_ITEM,new SearchItemPanel());
	}
	
	//TODO implement an interface to allow reset of all user entered fields
	public final static JPanel getView(String viewName) {
		//TODO get based on viewName;
		return viewMap.get(ResourceBundleKeys.SEARCH_ITEM);
	}
	
}
