package com.rixon.lms.domain;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: rixon
 * Date: 16/6/11
 * Time: 1:00 PM
 * Domain class representing the results of Search Query
 */
public class SearchResult {

    private final Map<UniqueIdentifier, SearchResultDetail> searchedLibraryItems = new HashMap<UniqueIdentifier,SearchResultDetail>();

    public SearchResultDetail getSearchDetailForId(UniqueIdentifier identifier) {
        return searchedLibraryItems.get(identifier); //TODO introduce concept of Null object
    }

    public List<SearchResultDetail> getAllSearchedItems() {
        List<SearchResultDetail> searchResultDetails = new ArrayList<SearchResultDetail>();
        searchResultDetails.addAll(searchedLibraryItems.values());
        return Collections.unmodifiableList(searchResultDetails) ;
    }

    public SearchResult(List<ILibraryItemInstance> libraryItemInstances) {
        initDataStructures(libraryItemInstances);
    }

    private void initDataStructures(List<ILibraryItemInstance> libraryItemInstances) {
        Map<UniqueIdentifier, List<ILibraryItemInstance>> details = new HashMap<UniqueIdentifier,List<ILibraryItemInstance>>();
        for (ILibraryItemInstance itemInstance:libraryItemInstances) {
            UniqueIdentifier uniqueId = itemInstance.getLibraryItem().getUniqueId();
            if (details.containsKey(uniqueId)) {
                List<ILibraryItemInstance> instances = details.get(uniqueId);
                instances.add(itemInstance);
                details.put(uniqueId,instances);

            }else {
                List<ILibraryItemInstance> instances = new ArrayList<ILibraryItemInstance>();
                instances.add(itemInstance);
                details.put(uniqueId,instances);
            }
        }
        initializeSearchedDataItems(details);

    }

    private void initializeSearchedDataItems(Map<UniqueIdentifier, List<ILibraryItemInstance>> details) {
        for(UniqueIdentifier uniqueIdentifier:details.keySet()){
            List<ILibraryItemInstance> items = details.get(uniqueIdentifier);
            int copiesCheckedOut = getCountOfCheckedOutItems(items);
            int copiesReserved = getCountOfReservedItems(items);
            int copiesArchived = getCountOfArchivedItems(items);
            SearchResultDetail searchResultDetail = new SearchResultDetail.SearchResultDetailBuilder().setLibraryItem(items.get(0).getLibraryItem())
                                                        .setTotalCopiesInLibrary(items.size()).setCopiesCheckedOut(copiesCheckedOut)
                                                        .setCopiesReserved(copiesReserved).setCopiesArchived(copiesArchived)
                                                        .setLibraryItemInstances(items)
                                                        .createSearchResultDetail();
            searchedLibraryItems.put(uniqueIdentifier,searchResultDetail);
        }

    }

    private int getCountOfCheckedOutItems(List<ILibraryItemInstance> items) {
        int count=0;
        for(ILibraryItemInstance itemInstance:items) {
            if (itemInstance.isCheckedOut())
               count++;
        }
        return count;
    }

    private int getCountOfReservedItems(List<ILibraryItemInstance> items) {
        int count=0;
        for(ILibraryItemInstance itemInstance:items) {
            if (itemInstance.isReserved())
               count++;
        }
        return count;
    }

    private int getCountOfArchivedItems(List<ILibraryItemInstance> items) {
        int count=0;
        for(ILibraryItemInstance itemInstance:items) {
            if (itemInstance.isArchived())
               count++;
        }
        return count;
    }

}