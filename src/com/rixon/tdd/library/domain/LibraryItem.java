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

package com.rixon.tdd.library.domain;

import com.rixon.tdd.library.type.TypeOfCustomProperty;

import java.util.Map;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 6/12/11 - 10:11 AM
 */
public class LibraryItem implements ILibraryItem {

    private String title;
    private ItemOwnerInformation ownerInformation;
    private ItemType itemType;
    private Map<TypeOfCustomProperty,CustomProperty> customProperties;
    private UniqueIdentifier identifier;

    private LibraryItem(String title, ItemOwnerInformation ownerInformation, ItemType itemType,
                       Map<TypeOfCustomProperty, CustomProperty> customProperties, UniqueIdentifier identifier) {
        this.title = title;
        this.ownerInformation = ownerInformation;
        this.itemType = itemType;
        this.customProperties = customProperties;
        this.identifier = identifier;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public ItemOwnerInformation getOwner() {
        return ownerInformation;
    }

    @Override
    public ItemType getItemType() {
        return itemType;
    }

    @Override
    public CustomProperty getCustomProperty(TypeOfCustomProperty propertyType) {
        return customProperties.get(propertyType);
    }

    @Override
    public void setCustomProperty(CustomProperty customProperty) {
        customProperties.put(customProperty.getPropertyType(),customProperty);
    }

    @Override
    public UniqueIdentifier getUniqueId() {
        return identifier;
    }

    @Override
    public boolean equals(Object libItemForCompare) {
        if (this == libItemForCompare) return true;
        if (libItemForCompare == null || getClass() != libItemForCompare.getClass()) return false;

        LibraryItem that = (LibraryItem) libItemForCompare;

        if (customProperties != null ? !customProperties.equals(that.customProperties) : that.customProperties != null)
            return false;
        if (!identifier.equals(that.identifier)) return false;
        if (!itemType.equals(that.itemType)) return false;
        if (!ownerInformation.equals(that.ownerInformation)) return false;
        return title.equals(that.title);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + ownerInformation.hashCode();
        result = 31 * result + itemType.hashCode();
        result = 31 * result + (customProperties != null ? customProperties.hashCode() : 0);
        result = 31 * result + identifier.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LibraryItem{").append("title=>").append(title).append("|")
                                            .append("identifier=>").append(identifier).append("|")
                                            .append("ownerInformation=>").append(ownerInformation).append("|")
                                            .append("itemType=>").append(itemType).append("|");

        if (customProperties != null) {
            stringBuilder.append("customProperties=>").append(customProperties).append("}");
        } else {
            stringBuilder.append("}");
        }

        return stringBuilder.toString();
    }

    public static class LibraryItemBuilder {
        private String title;
        private ItemOwnerInformation ownerInformation;
        private ItemType itemType;
        private Map<TypeOfCustomProperty, CustomProperty> customProperties;
        private UniqueIdentifier identifier;

        public LibraryItemBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public LibraryItemBuilder setOwnerInformation(ItemOwnerInformation ownerInformation) {
            this.ownerInformation = ownerInformation;
            return this;
        }

        public LibraryItemBuilder setItemType(ItemType itemType) {
            this.itemType = itemType;
            return this;
        }

        public LibraryItemBuilder setCustomProperties(Map<TypeOfCustomProperty, CustomProperty> customProperties) {
            this.customProperties = customProperties;
            return this;
        }

        public LibraryItemBuilder setIdentifier(UniqueIdentifier identifier) {
            this.identifier = identifier;
            return this;
        }

        public LibraryItem createLibraryItem() {
            return new LibraryItem(title, ownerInformation, itemType, customProperties, identifier);
        }
    }
}
