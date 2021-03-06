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


import com.rixon.lms.domain.ItemType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rixon(rixonmathew@gmail.com)
 * User: rixon
 * Date: 6/4/11
 * Time: 10:57 PM
 */
public class StoreProvider {

    private static final Map<ItemType, LibraryItemStore> storeCache = new HashMap<ItemType, LibraryItemStore>();

    public static LibraryItemStore getStoreForItem(ItemType itemType)
    {
        LibraryItemStore store;
        
        store = storeCache.get(itemType);
        if (store == null){
        	store = new LibraryItemStore();
        	storeCache.put(itemType, store);
        }
        return store;
    }
}
