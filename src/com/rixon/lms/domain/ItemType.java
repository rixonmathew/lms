package com.rixon.lms.domain;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 11:40 PM
 */
public class ItemType {

    private String type;
    private String description;

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public ItemType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemType itemType = (ItemType) o;

        if (description != null ? !description.equals(itemType.description) : itemType.description != null)
            return false;
        if (type != null ? !type.equals(itemType.type) : itemType.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public static class ItemTypeBuilder {
        private String type;
        private String description;

        public ItemTypeBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public ItemTypeBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemType createItemType() {
            return new ItemType(type, description);
        }
    }
}

