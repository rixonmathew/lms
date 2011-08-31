package com.rixon.lms.dao.mapper;

import com.rixon.lms.dao.resultset.ItemTypeRS;
import com.rixon.lms.domain.ItemType;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 11:42 PM
 */
public class ItemTypeMapper {

    public static ItemType mapToItemType(ItemTypeRS itemTypeRS) {
        ItemType.ItemTypeBuilder builder = new ItemType.ItemTypeBuilder();
        builder.setType(itemTypeRS.getType());
        builder.setDescription(itemTypeRS.getDescription());
        return builder.createItemType();
    }
}
