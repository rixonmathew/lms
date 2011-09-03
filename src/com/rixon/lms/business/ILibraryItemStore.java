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

package com.rixon.lms.business;

import com.rixon.lms.domain.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Rixon(rixonmathew@gmail.com)
 * User: rixon
 * Date: 6/4/11
 * Time: 10:04 PM
 */
//TODO add Java docs
public interface ILibraryItemStore {

    public Set<ILibraryItem> getItemCatalog();

    public void addItemToLibrary(ILibraryItem libraryItem);

    public void addMultipleItemToLibrary(List<ILibraryItem> libraryItems);

    public void archiveItem(ILibraryItem libraryItem);

    public void unArchiveItem(ILibraryItem libraryItem);

    public int getCopiesAvailableForItem(ILibraryItem libraryItem);

    public SearchResult searchByTitle(String itemTitle);

    /**
     * This method will do an exact match of the preferred Id of the Library ItemRS,.
     * @param preferredId The preferred id for the Library ItemRS. For books it can be isbn. For Movies and Audio CD's
     *                    the parameter can defer or can default to item title;
     * @return returns the library item if one was found for
     */
    public SearchResult searchByIdentifier(UniqueIdentifier preferredId);

    public SearchResult searchAllAttributes(String searchText);

    //TODO: Is Store the really the best place to define limit? What if Library
    //needs to define the limit based on member type;
    public int getCheckOutLimitForItem();
    
    public void updateLibraryItem(ILibraryItem libraryItem);
    
    public void checkOutItem(CheckedOutItem checkedOutItem);
    
    public List<CheckedOutItem> getCheckedOutItemsForMember(LibraryMember member);

    public void reserveItem(ItemReservation itemItemReservation);

    public List<ItemReservation> getReservedItemsForMember(LibraryMember member);

    public void returnItem(CheckedOutItem checkedOutItem);
}
