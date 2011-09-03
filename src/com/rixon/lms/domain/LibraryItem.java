/*
 * Copyright (c) 2011. Rixon Mathew (rixonmathew@gmail.com)
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 *  following conditions are met:
 * Redistribution of source code must retain the above copyright notice, this list of conditions and the
 *  following disclaimer.
 * Redistribution in binary form must reproduce the above copyright notice, this list of conditions and the following
 *  disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of Rixon Mathew nor the names of its contributors may be used to endorse or promote products
 *  derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 *  INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 *  OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.rixon.lms.domain;

import org.apache.commons.collections.MapUtils;
import org.hsqldb.lib.Collection;

import java.util.*;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 6/12/11 - 10:11 AM
 */
public class LibraryItem implements ILibraryItem {

    private final String name;
    private final String description;
    private final ItemType itemType;
    private final Map<Property,ItemPropertyValue> itemProperties;
    private final UniqueIdentifier identifier;

    private LibraryItem(String name, String description,  ItemType itemType,
                       Map<Property,ItemPropertyValue> itemProperties, UniqueIdentifier identifier) {
        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.itemProperties = itemProperties;
        this.identifier = identifier;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public ItemType getItemType() {
        return itemType;
    }

    @Override
    public ItemPropertyValue getItemPropertyValue(Property property) {
        return itemProperties.get(property);
    }

    @Override
    public void setItemPropertyValue(ItemPropertyValue itemPropertyValue) {
        itemProperties.put(itemPropertyValue.getProperty(),itemPropertyValue);
    }

    @Override
    public List<ItemPropertyValue> getAllItemPropertValues() {
        return Collections.unmodifiableList(new ArrayList<ItemPropertyValue>(itemProperties.values()));
    }

    @Override
    public UniqueIdentifier getUniqueId() {
        return identifier;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object libItemForCompare) {
        if (this == libItemForCompare) return true;
        if (libItemForCompare == null || getClass() != libItemForCompare.getClass()) return false;

        LibraryItem that = (LibraryItem) libItemForCompare;

        if (itemProperties != null ? !itemProperties.equals(that.itemProperties) : that.itemProperties != null)
            return false;
        if (!identifier.equals(that.identifier)) return false;
        if (!itemType.equals(that.itemType)) return false;
        if (!description.equals(that.description)) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description==null?0:description.hashCode());
        result = 31 * result + itemType.hashCode();
        result = 31 * result + (itemProperties != null ? itemProperties.hashCode() : 0);
        result = 31 * result + identifier.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LibraryItem{").append("name=>").append(name).append("|")
                                            .append("description=>").append(identifier).append("|")
                                            .append("identifier=>").append(identifier).append("|")
                                            .append("itemType=>").append(itemType).append("|");

        if (itemProperties != null) {
            stringBuilder.append("itemProperties=>").append(itemProperties).append("}");
        } else {
            stringBuilder.append("}");
        }

        return stringBuilder.toString();
    }

    public static class LibraryItemBuilder {
        private String name;
        private String description;
        private ItemType itemType;
        private Map<Property, ItemPropertyValue> itemProperties;
        private UniqueIdentifier identifier;

        public LibraryItemBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public LibraryItemBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public LibraryItemBuilder setItemType(ItemType itemType) {
            this.itemType = itemType;
            return this;
        }

        public LibraryItemBuilder setItemProperties(Map<Property, ItemPropertyValue> itemProperties) {
            this.itemProperties = itemProperties;
            return this;
        }

        public LibraryItemBuilder setIdentifier(UniqueIdentifier identifier) {
            this.identifier = identifier;
            return this;
        }

        public LibraryItem createLibraryItem() {
            return new LibraryItem(name, description, itemType, itemProperties, identifier);
        }
    }
}
