package com.rixon.tdd.library.domain;

import com.rixon.tdd.library.exception.InstanceNotAvailableException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rixon
 * Date: 16/6/11
 * Time: 8:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchResultDetail {
    private ILibraryItem libraryItem;
    private List<ILibraryItemInstance> itemInstances;
    private int totalCopiesInLibrary;
    private int copiesCheckedOut;
    private int copiesReserved;
    private int copiesArchived;

    private SearchResultDetail(ILibraryItem libraryItem, List<ILibraryItemInstance> itemInstances, int totalCopiesInLibrary, int copiesCheckedOut, int copiesReserved, int copiesArchived) {
        this.libraryItem = libraryItem;
        this.itemInstances = itemInstances;
        this.totalCopiesInLibrary = totalCopiesInLibrary;
        this.copiesCheckedOut = copiesCheckedOut;
        this.copiesReserved = copiesReserved;
        this.copiesArchived = copiesArchived;
    }

    public ILibraryItem getLibraryItem() {
        return libraryItem;
    }

    public int getTotalCopiesInLibrary() {
        return totalCopiesInLibrary;
    }

    public int getCopiesCheckedOut() {
        return copiesCheckedOut;
    }

    public int getCopiesReserved() {
        return copiesReserved;
    }

    public int getCopiesArchived() {
        return copiesArchived;
    }

    public ILibraryItemInstance getNextAvailableInstance() {
        for(ILibraryItemInstance itemInstance:itemInstances){
            if (!itemInstance.isCheckedOut() && !itemInstance.isArchived()) {
                return itemInstance;
            }
        }
        throw new InstanceNotAvailableException(" No instance found for library item "+libraryItem);
    }

    public static class SearchResultDetailBuilder {

        private ILibraryItem libraryItem;
        private List<ILibraryItemInstance> itemInstances;
        private int totalCopiesInLibrary;
        private int copiesCheckedOut;
        private int copiesReserved;
        private int copiesArchived;

        public SearchResultDetailBuilder setLibraryItem(ILibraryItem libraryItem) {
            this.libraryItem = libraryItem;
            return this;
        }

        public SearchResultDetailBuilder setLibraryItemInstances(List<ILibraryItemInstance> itemInstances) {
            this.itemInstances = itemInstances;
            return this;
        }

        public SearchResultDetailBuilder setTotalCopiesInLibrary(int totalCopiesInLibrary) {
            this.totalCopiesInLibrary = totalCopiesInLibrary;
            return this;
        }

        public SearchResultDetailBuilder setCopiesCheckedOut(int copiesCheckedOut) {
            this.copiesCheckedOut = copiesCheckedOut;
            return this;
        }

        public SearchResultDetailBuilder setCopiesReserved(int copiesReserved) {
            this.copiesReserved = copiesReserved;
            return this;
        }

        public SearchResultDetailBuilder setCopiesArchived(int copiesArchived) {
            this.copiesArchived = copiesArchived;
            return this;
        }

        public SearchResultDetail createSearchResultDetail() {
            return new SearchResultDetail(libraryItem,itemInstances, totalCopiesInLibrary, copiesCheckedOut, copiesReserved, copiesArchived);
        }
    }

}
