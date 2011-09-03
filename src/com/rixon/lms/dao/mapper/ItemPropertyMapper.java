package com.rixon.lms.dao.mapper;

import com.rixon.lms.dao.resultset.ItemPropertyRS;
import com.rixon.lms.domain.ItemPropertyValue;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 11:29 PM
 */
class ItemPropertyMapper {

    public static ItemPropertyValue mapToItemProperty(ItemPropertyRS itemPropertyRS) {
        ItemPropertyValue.ItemPropertyValueBuilder builder = new ItemPropertyValue.ItemPropertyValueBuilder();
        builder.setProperty(PropertyMapper.mapToProperty(itemPropertyRS.getPropertyRS()));
        builder.setPropertyValue(itemPropertyRS.getValue());
        return builder.createItemPropertyValue();
    }
}
