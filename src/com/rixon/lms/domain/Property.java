package com.rixon.lms.domain;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 11:10 PM
 */
public final class Property {

    private final String name;
    private final String description;

    private  Property(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        if (!description.equals(property.description)) return false;
        return name.equals(property.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    public static class PropertyBuilder {
        private String name;
        private String description;

        public PropertyBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PropertyBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Property createProperty() {
            return new Property(name, description);
        }
    }
}
