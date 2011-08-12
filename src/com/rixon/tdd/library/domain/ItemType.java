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

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Interface to represent an Item Type that can be added to the Library.
 *
 * @author : Rixon Mathew (rixonmathew@gmail.com)
 *
 */
@Entity
@Table(name = "ITEM_TYPE")
@NamedQuery(name = "allItemTypes",query = "select a from ItemType a")
public class ItemType implements Serializable {


    private Integer id;
    private String type;
    private String description;

    public ItemType(){
    }

    @Id
    public Integer getId() {
      return id;
    }

    /**
     * Method to get ths short description of the Item Type Code
     * @return Code for the Item Type
     */
    @Column(name="TYPE")
    public String getType() {
        return type;
    }

    /**
     * Method to get the detailed Description of the Item
     * @return
     */
    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemType(int id,String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    /**
     * Method to get All Custom Properties that belongs to a particular Item Type
     * @return Read Only List of Custom Property
     */
    public List<CustomProperty> getAllCustomProperties() {
        return null; //TODO provide implementation
    }

    /**
     * Method to set a particular Custom property
     * @param customProperty
     */
    public void addCustomProperty(CustomProperty customProperty) {
        //TODO provide implementation
    }

    @Override
    public boolean equals(Object itemTypeToCompare) {
        if (this == itemTypeToCompare) return true;
        if (itemTypeToCompare == null || getClass() != itemTypeToCompare.getClass()) return false;

        ItemType itemType = (ItemType) itemTypeToCompare;

        if (!type.equals(itemType.type)) return false;
        return description.equals(itemType.description);

    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ItemType {").append("id=>").append(id).append("|")
                                          .append("type=>").append(type).append("|")
                                          .append("description=>").append(description).append("}");
        return stringBuilder.toString();
    }
}
