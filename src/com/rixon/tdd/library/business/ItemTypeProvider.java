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

import com.rixon.tdd.library.dao.LibraryDAO;
import com.rixon.tdd.library.domain.ItemType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 6/11/11 - 3:08 PM
 */
public class ItemTypeProvider {

    private static Map<String,ItemType> itemTypeCache = new HashMap<String, ItemType>();

    public static ItemType getItemType(String code)
    {
        loadItemTypeCacheIfRequired();
        ItemType itemType = itemTypeCache.get(code);
        if (itemType==null) {
            throw new IllegalArgumentException("Invalid code type "+code);
        }
        return itemType;
    }

    private static void  loadItemTypeCacheIfRequired() {
        if (itemTypeCache.isEmpty()) {
            List<ItemType> itemTypes = LibraryDAO.getInstance().getAllItemTypes();
            for (ItemType itemType:itemTypes) {
                itemTypeCache.put(itemType.getType(),itemType);
            }
        }
    }
}
