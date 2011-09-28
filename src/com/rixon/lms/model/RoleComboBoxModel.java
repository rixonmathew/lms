package com.rixon.lms.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.rixon.lms.domain.Role;

public class RoleComboBoxModel implements ComboBoxModel {

    private Map<String,Role> entries = new HashMap<String,Role>();
    private String selectedRole;
	
    public RoleComboBoxModel(Collection<Role> entries)
    {
    	for(Role role:entries) {
    		this.entries.put(role.getRole(), role);
    	}
    }
    
	@Override
	public Object getSelectedItem() {
		return selectedRole;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selectedRole = (String)anItem;
  	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object getElementAt(int index) {
		Object[] objects = entries.keySet().toArray();
		return objects[index];
	}

	@Override
	public int getSize() {
		return entries.keySet().size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
	}
}
