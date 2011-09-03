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

package com.rixon.lms.main;


import com.rixon.lms.business.LibraryItemStore;
import com.rixon.lms.business.LibraryMemberStore;
import com.rixon.lms.business.StoreProvider;
import com.rixon.lms.domain.*;

import java.util.List;
import java.util.Set;

/**
 * User: rixon
 * Date: 5/28/11
 * Time: 1:34 PM
 */
public class LibraryManagementSystem {

    private final LibraryMemberStore memberStore;

    public LibraryManagementSystem()
    {
        memberStore = new LibraryMemberStore();
    }

    //TODO how to initialize the stores for the Library and inject it to LMS
    public Set<LibraryMember> getAllMembers() {
        return memberStore.getMembers();
    }

    public void addMember(LibraryMember member) {
        memberStore.addMember(member);
    }

    public LibraryMember searchForMemberById(UniqueIdentifier memberId) {
        for (LibraryMember member:memberStore.getMembers())
        {
            if (member.getMembershipId().equals(memberId))
            {
                return member;
            }
        }
        throw new IllegalArgumentException("Member not found for id:"+memberId);
    }

    public Set<ILibraryItem> getAllItems(ItemType itemType) {
    	//TODO do you need a separate store for every item type?
        LibraryItemStore itemStore = StoreProvider.getStoreForItem(itemType);
        return itemStore.getItemCatalog();
    }

    public void addLibraryItems(ItemType itemType, List<ILibraryItem> libraryItems) {
    	LibraryItemStore itemStore = StoreProvider.getStoreForItem(itemType);
        itemStore.addMultipleItemToLibrary(libraryItems);
    }

    public SearchResult searchForItemByTitle(ItemType itemType,String itemTitle){
        LibraryItemStore itemStore = StoreProvider.getStoreForItem(itemType);
        return itemStore.searchByTitle(itemTitle);
    }

    public SearchResult searchForItemByIdentifier(ItemType itemType, UniqueIdentifier uniqueIdentifier){
        LibraryItemStore itemStore = StoreProvider.getStoreForItem(itemType);
        return itemStore.searchByIdentifier(uniqueIdentifier);
    }

    public SearchResult searchAllAttributes(ItemType itemType,String itemTitle){
        LibraryItemStore itemStore = StoreProvider.getStoreForItem(itemType);
        return itemStore.searchAllAttributes(itemTitle);
    }

    public void archiveItem(ILibraryItem libraryItem) {
        LibraryItemStore itemStore = StoreProvider.getStoreForItem(libraryItem.getItemType());
        itemStore.archiveItem(libraryItem);
    }

    public void unArchiveItem(ILibraryItem libraryItem) {
        LibraryItemStore itemStore = StoreProvider.getStoreForItem(libraryItem.getItemType());
        itemStore.unArchiveItem(libraryItem);
    }

	public void setCustomProperty(ItemPropertyValue propertyValue,ILibraryItem... libraryItem  ) {
		for(ILibraryItem item:libraryItem){
			LibraryItemStore itemStore = StoreProvider.getStoreForItem(item.getItemType());
			item.setItemPropertyValue(propertyValue);
			itemStore.updateLibraryItem(item);
		}
		
	}

	public void checkOutItemToMember(CheckedOutItem checkedOutItem) {
        ItemType itemType = checkedOutItem.getLibraryItemInstance().getLibraryItem().getItemType();
        LibraryItemStore itemStore = StoreProvider.getStoreForItem(itemType);
		itemStore.checkOutItem(checkedOutItem);
	}

	public List<CheckedOutItem> getCheckedOutItemsForMember(ItemType itemType,
                                                            LibraryMember member) {
		LibraryItemStore itemStore = StoreProvider.getStoreForItem(itemType);
		return itemStore.getCheckedOutItemsForMember(member);
	}

	public int getItemCheckOutLimit(ItemType itemType) {
		LibraryItemStore itemStore = StoreProvider.getStoreForItem(itemType);
		return itemStore.getCheckOutLimitForItem();
	}

    public void reserveItem(ItemReservation itemReservation) {
        LibraryItemStore itemStore = StoreProvider.getStoreForItem(itemReservation.getLibraryItemInstance().getLibraryItem().getItemType());
        itemStore.reserveItem(itemReservation);
    }

    public List<ItemReservation> getReservedItemsForMember(ItemType itemType, LibraryMember member) {
        LibraryItemStore itemStore = StoreProvider.getStoreForItem(itemType);
        return itemStore.getReservedItemsForMember(member);
    }

    public void returnItem(CheckedOutItem item) {
        ItemType itemType = item.getLibraryItemInstance().getLibraryItem().getItemType();
        LibraryItemStore itemStore = StoreProvider.getStoreForItem(itemType);
        itemStore.returnItem(item);
    }
}