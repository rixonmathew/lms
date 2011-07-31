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
package com.rixon.tdd.library.business;

import java.util.*;

import com.rixon.tdd.library.domain.*;
import com.rixon.tdd.library.exception.LibrarySystemException;
import com.rixon.tdd.library.domain.ItemType;

/**
 * Created by Rixon(rixonmathew@gmail.com)
 * User: rixon
 * Date: 6/6/11
 * Time: 8:46 PM
 */
public class LibraryItemStore implements ILibraryItemStore {

    private Map<UniqueIdentifier,ILibraryItem> libraryItemsCatalog = new HashMap<UniqueIdentifier,ILibraryItem>();
    private Map<UniqueIdentifier,List<ILibraryItemInstance>> itemInstances = new HashMap<UniqueIdentifier,List<ILibraryItemInstance>>();
    private Map<UniqueIdentifier, List<CheckedOutItem>> itemsCheckedOut = new HashMap<UniqueIdentifier, List<CheckedOutItem>>();
    private Map<UniqueIdentifier,List<ItemReservation>> memberReservations = new HashMap<UniqueIdentifier, List<ItemReservation>>();
    private int itemCheckOutLimit = 2;

    public void setItemCheckOutLimit(int itemCheckOutLimit) {
        this.itemCheckOutLimit = itemCheckOutLimit;
    }

    private  boolean isValidItemPresentInLibrary(ILibraryItem libraryItem){
    	if (libraryItem ==null)
    		return false;
        return libraryItemsCatalog.containsKey(libraryItem.getUniqueId());
    }

    private void validateItem(ILibraryItem libraryItem) {
		if (!isValidItemPresentInLibrary(libraryItem))
        	throw new IllegalArgumentException(libraryItem.getItemType()+" not present in Library "+ libraryItem);
	}

    private boolean atleastOneCopyOfItemPresent(ILibraryItem libraryItem) {
        if (!libraryItemsCatalog.containsKey(libraryItem.getUniqueId())) {
            return false;
        }

        List<ILibraryItemInstance> itemInstanceList = getItemInstances(libraryItem.getUniqueId());
        for (ILibraryItemInstance item:itemInstanceList) {
            if (!item.isArchived()) {
                return true;
            }
        }
        return false;
    }

    private List<ILibraryItemInstance> getItemInstances(UniqueIdentifier identifier) {
        List<ILibraryItemInstance> items = itemInstances.get(identifier);
        if (items == null) {
            items = new ArrayList<ILibraryItemInstance>();
        }
        return items;
    }

    private void validateItemForCheckOut(ILibraryItem itemToCheckOut, LibraryMember member) {
        validateItem(itemToCheckOut);
        if (!atleastOneCopyOfItemPresent(itemToCheckOut))
        {
            throw new LibrarySystemException("Checkout not possible since copies available is 0 for "+itemToCheckOut);

        }
        if (!verifyCheckOutLimitOfItem(member))
        {
            throw new LibrarySystemException("Checkout not possible since member has already checked out max limit :"+ itemCheckOutLimit);
        }
    }


    private void doCheckOutOfItem(CheckedOutItem checkedOutItem) {

        UniqueIdentifier membershipId = checkedOutItem.getMember().getMembershipId();
        List<CheckedOutItem> items;
        if(itemsCheckedOut.containsKey(membershipId)) {
            items= itemsCheckedOut.get(membershipId);
        }
        else {
            items = new ArrayList<CheckedOutItem>();
        }
        items.add(checkedOutItem);
        itemsCheckedOut.put(membershipId,items);
        updateCheckedOutFlag(checkedOutItem.getLibraryItemInstance());
    }

    private void updateCheckedOutFlag(ILibraryItemInstance libraryItemInstance) {
        libraryItemInstance.setCheckedOutFlag(true);
    }

    private ILibraryItemInstance getAvailableInstance(ILibraryItem itemToCheckOut) {
        for (ILibraryItemInstance itemInstance: getItemInstances(itemToCheckOut.getUniqueId())) {
            if (!itemInstance.isArchived() && !itemInstance.isCheckedOut() && !itemInstance.isReserved()) {
                return itemInstance;
            }
        }
        return null; //todo introduce concept of null objects
    }

    private boolean verifyCheckOutLimitOfItem(LibraryMember member) {
        if (!itemsCheckedOut.containsKey(member.getMembershipId()))
          return true;
        List<CheckedOutItem> items = itemsCheckedOut.get(member.getMembershipId());
        return items.size() < itemCheckOutLimit;
    }

    @Override
    public void addItemToLibrary(ILibraryItem libraryItem) {

        if (libraryItemsCatalog.containsKey(libraryItem.getUniqueId())) {
            updateItemInstanceContainer(libraryItem);
        } else {
            libraryItemsCatalog.put(libraryItem.getUniqueId(),libraryItem);
            updateItemInstanceContainer(libraryItem);
        }
    }

    private void updateItemInstanceContainer(ILibraryItem libraryItem) {
        List<ILibraryItemInstance> items = getItemInstances(libraryItem.getUniqueId());
        items.add(new LibraryItemInstance(libraryItem,new UniqueIdentifier.UniqueIdentifierBuilder().setType("ID").generateUniqueValue().createUniqueIdentifier()));
        itemInstances.put(libraryItem.getUniqueId(),items);
    }

    @Override
	public void addMultipleItemToLibrary(List<ILibraryItem> multipleItems) {
		for(ILibraryItem libraryItem:multipleItems){
			addItemToLibrary(libraryItem);
		}
	}

	@Override
	public void archiveItem(ILibraryItem libraryItem) {
        if (!libraryItemsCatalog.containsKey(libraryItem.getUniqueId()))
            throw new IllegalArgumentException(libraryItem.getItemType()+" is not present in libraryManagementSystem" + libraryItem);
        for(ILibraryItemInstance itemInstance: getItemInstances(libraryItem.getUniqueId())) {
              itemInstance.setArchiveFlag(true);
        }

	}

	@Override
	public void unArchiveItem(ILibraryItem libraryItem) {
        if (!libraryItemsCatalog.containsKey(libraryItem.getUniqueId()))
            throw new IllegalArgumentException(libraryItem.getItemType()+" is not present in libraryManagementSystem" + libraryItem);
        for(ILibraryItemInstance itemInstance:getItemInstances(libraryItem.getUniqueId())) {
              itemInstance.setArchiveFlag(false);
        }
	}

	@Override
	public void checkOutItem(CheckedOutItem checkedOutItem) {
		validateItemForCheckOut(checkedOutItem.getLibraryItemInstance().getLibraryItem(), checkedOutItem.getMember());
		doCheckOutOfItem(checkedOutItem);

	}

	@Override
	public Set<ILibraryItem> getItemCatalog() {
        Set<ILibraryItem> itemSet = new HashSet<ILibraryItem>();
        itemSet.addAll(libraryItemsCatalog.values());
    	return Collections.unmodifiableSet(itemSet);
	}

	@Override
	public int getCheckOutLimitForItem() {
		return itemCheckOutLimit;
	}

	@Override
	public List<CheckedOutItem> getCheckedOutItemsForMember(LibraryMember member) {
		return Collections.unmodifiableList(itemsCheckedOut.get(member.getMembershipId()));	}


    @Override
	public int getCopiesAvailableForItem(ILibraryItem libraryItem) {
		int copiesAvailable=0;
        for(ILibraryItemInstance itemInstance: getItemInstances(libraryItem.getUniqueId())) {
            if (!itemInstance.isArchived() && !itemInstance.isCheckedOut())
                copiesAvailable++;
        }
        return copiesAvailable;
	}

	@Override
	public SearchResult searchByTitle(String itemTitle) {
        //TODO We are scanning over the entire map to search via title. Check if one more data structure will be required for
        //TODO introduce pattern for partial search rather than exact match;

        List<ILibraryItemInstance> itemInstances = new ArrayList<ILibraryItemInstance>();
        for (ILibraryItem libraryItem : libraryItemsCatalog.values()) {
            if (libraryItem.getTitle().equalsIgnoreCase(itemTitle)) {
                itemInstances.addAll(getItemInstances(libraryItem.getUniqueId()));
            }
        }
        SearchResult searchResult = new SearchResult(itemInstances);
        return searchResult;
	}

	@Override
	public SearchResult searchByIdentifier(UniqueIdentifier uniqueIdentifier) {
        List<ILibraryItemInstance> items = getItemInstances(uniqueIdentifier);
        SearchResult searchResult = new SearchResult(items);
        return searchResult;
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



    @Override
	public void updateLibraryItem(ILibraryItem libraryItem) {
		validateItem(libraryItem);
		libraryItemsCatalog.put(libraryItem.getUniqueId(),libraryItem);
	}

    @Override
    public void reserveItem(ItemReservation itemItemReservation) {
        validateItemToBeReserved(itemItemReservation.getLibraryItemInstance().getLibraryItem());
        doReservationOfLibraryItem(itemItemReservation);
    }


    private void validateItemToBeReserved(ILibraryItem libraryItem) {
         validateItem(libraryItem);
         //TODO validate if the item is reserved already
     }

    private void doReservationOfLibraryItem(ItemReservation itemReservation) {
        LibraryMember libraryMember = itemReservation.getLibraryMember();
        if (memberReservations.containsKey(libraryMember.getMembershipId())){
            List<ItemReservation> reservations = memberReservations.get(libraryMember.getMembershipId());
            reservations.add(itemReservation);
            memberReservations.put(libraryMember.getMembershipId(),reservations);
        } else
        {
            List<ItemReservation> reservations = new ArrayList<ItemReservation>();
            reservations.add(itemReservation);
            memberReservations.put(libraryMember.getMembershipId(),reservations);
        }
    }

    @Override
    public List<ItemReservation> getReservedItemsForMember(ItemType itemType, LibraryMember member) {
        return memberReservations.get(member.getMembershipId());
    }

    @Override
    public void returnItem(CheckedOutItem item) {

        LibraryMember member = item.getMember();
        List<CheckedOutItem> checkedOutItems = itemsCheckedOut.get(member.getMembershipId());
        if (checkedOutItems.contains(item)) {
           checkedOutItems.remove(item);
           checkedOutItems.add(item);
        }
        ILibraryItemInstance libraryItemInstance = item.getLibraryItemInstance();
        libraryItemInstance.setCheckedOutFlag(false);

    }
}