package com.rixon.lms.dao.mapper;

import com.rixon.lms.business.PropertyProvider;
import com.rixon.lms.dao.resultset.ItemPropertyRS;
import com.rixon.lms.dao.resultset.ItemRS;
import com.rixon.lms.dao.resultset.PropertyRS;
import com.rixon.lms.domain.*;
import com.rixon.lms.util.LMSUtil;
import com.rixon.lms.util.PropertyConstants;
import org.apache.openjpa.persistence.jdbc.Unique;


import java.util.HashMap;
import java.util.Map;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 11:25 PM
 */
public class ItemMapper {
    public static ILibraryItem mapToLibraryITem(ItemRS itemRS) {
        if (itemRS== null)
        {
            return null;
        }
        LibraryItem.LibraryItemBuilder builder = new LibraryItem.LibraryItemBuilder();
        builder.setName(itemRS.getName());
        builder.setItemProperties(mapItemProperties(itemRS.getProperties()));
        //TODO think about how to set a generic identitier? Current assumption is that all items will have an isbn

        Property property = PropertyProvider.getProperty(PropertyConstants.ISBN);
        PropertyRS propertyRS = PropertyMapper.mapToPropertyRS(property);
        String isbn = itemRS.getProperties().get(propertyRS).getValue();
        builder.setIdentifier(LMSUtil.createISBN(isbn));
        builder.setItemType(ItemTypeMapper.mapToItemType(itemRS.getItemTypeRS()));
        return builder.createLibraryItem();
    }

    private static Map<Property,ItemPropertyValue> mapItemProperties(Map<PropertyRS,ItemPropertyRS> propertyRSMap) {
        Map<Property,ItemPropertyValue> itemPropertyValueMap = new HashMap<Property, ItemPropertyValue>();
        for (PropertyRS propertyRS:propertyRSMap.keySet()) {
            Property property = PropertyMapper.mapToProperty(propertyRS);
            ItemPropertyValue propertyValue = ItemPropertyMapper.mapToItemProperty(propertyRSMap.get(propertyRS));
            itemPropertyValueMap.put(property,propertyValue);

        }
        return itemPropertyValueMap;
    }
}
