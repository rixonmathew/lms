package com.rixon.lms.dao.resultset;

import javax.persistence.*;
import java.util.Map;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 6:36 PM
 */
@Entity()
@Table(name="ITEM")
@NamedQuery(name=ItemRS.ALL_ITEMS_QUERY,query = "select item from ItemRS item")
public class ItemRS {

    public final static String ALL_ITEMS_QUERY="allItems";

    private int id;
    private ItemTypeRS itemTypeRS;
    private String name;
    private String description;
    private Map<PropertyRS,ItemPropertyRS> properties;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @ManyToOne
    @JoinColumn(name="ITEM_TYPE_ID")
    public ItemTypeRS getItemTypeRS() {
        return itemTypeRS;
    }

    public void setItemTypeRS(ItemTypeRS itemTypeRS) {
        this.itemTypeRS = itemTypeRS;
    }

    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="ITEM_ID")
    @MapKey(name = "propertyRS")
    public Map<PropertyRS,ItemPropertyRS> getProperties() {
        return properties;
    }

    public void setProperties(Map<PropertyRS,ItemPropertyRS> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "ItemRS{" +
                "id=" + id +
                ", itemTypeRS=" + itemTypeRS +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", properties=" + properties +
                '}';
    }
}
