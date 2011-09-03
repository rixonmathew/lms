package com.rixon.lms.dao.resultset;

import javax.persistence.*;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 7:19 PM
 */

@Entity
@Table(name="PROPERTY")
@NamedQuery(name= PropertyRS.ALL_PROPERTY_QUERY,query="select property from PropertyRS property")
public class PropertyRS {
    public final static String ALL_PROPERTY_QUERY = "allProperty";

    private int id;
    private String name;
    private String description;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "PropertyRS{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyRS propertyRS = (PropertyRS) o;

        if (!name.equals(propertyRS.name)) return false;
        return description.equals(propertyRS.description);

    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
