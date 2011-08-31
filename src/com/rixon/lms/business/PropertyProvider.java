package com.rixon.lms.business;

import com.rixon.lms.dao.LibraryDAO;
import com.rixon.lms.dao.mapper.PropertyMapper;
import com.rixon.lms.dao.resultset.PropertyRS;
import com.rixon.lms.domain.Property;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 8:48 PM
 */
public class PropertyProvider {

    private static Map<String,Property> propertyMap;

    static  {
        propertyMap = new HashMap<String, Property>();
        List<PropertyRS> properties=LibraryDAO.getInstance().getAllProperties();
        for(PropertyRS propertyRS :properties) {
            propertyMap.put(propertyRS.getName(), PropertyMapper.mapToProperty(propertyRS));
        }
    }

    private PropertyProvider () {}

    public static Property getProperty(String property) {

        if (propertyMap.containsKey(property)) {
            return propertyMap.get(property);
        }
        throw new IllegalArgumentException("No property exists by name "+property);
    }
}
