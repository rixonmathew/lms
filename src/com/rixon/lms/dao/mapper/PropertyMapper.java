package com.rixon.lms.dao.mapper;

import com.rixon.lms.dao.resultset.PropertyRS;
import com.rixon.lms.domain.Property;


/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 11:13 PM
 */
public class PropertyMapper {

    public static Property mapToProperty(PropertyRS propertyRS) {
        Property.PropertyBuilder propertyBuilder = new Property.PropertyBuilder();
        propertyBuilder.setName(propertyRS.getName());
        propertyBuilder.setDescription(propertyRS.getDescription());
        return propertyBuilder.createProperty();
    }

    public static PropertyRS mapToPropertyRS(Property property) {
        PropertyRS propertyRS = new PropertyRS();
        propertyRS.setName(property.getName());
        propertyRS.setDescription(property.getDescription());
        return propertyRS;
    }
}
