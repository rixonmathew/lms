package com.rixon.lms.dao.resultset;

import javax.persistence.*;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 7:18 PM
 */
@Entity
@Table(name="ITEM_PROPERTY")
public class ItemPropertyRS {

    private int id;
    private int item_id;
    private PropertyRS propertyRS;
    private String value;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="ITEM_ID")
    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }


    @OneToOne
    @JoinColumn(name = "PROPERTY_ID")
    public PropertyRS getPropertyRS() {
        return propertyRS;
    }

    public void setPropertyRS(PropertyRS propertyRS) {
        this.propertyRS = propertyRS;
    }

    //TODO rename column in table to value
    @Column(name="PROPERTY_VALUE")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ItemPropertyRS{" +
                "id=" + id +
                ", item_id=" + item_id +
                ", propertyRS=" + propertyRS +
                ", value='" + value + '\'' +
                '}';
    }
}
