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

package com.rixon.tdd.library.dao;

import com.rixon.tdd.library.domain.ILibraryItem;
import com.rixon.tdd.library.domain.ItemType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Rixon Mathew(rixonmathew@gmail.com)
 * date   : 6/12/11 - 1:43 PM
 */
public class DAOTest {

    private static LibraryDAO libraryDAO;

    @BeforeClass
    public static void setUp() {
        libraryDAO = new LibraryDAO();
    }

    @AfterClass
    public static void tearDown()
    {
        libraryDAO = null;
    }

    @Test
    public void testGetAllItemTypes()  {
        List<ItemType> itemTypes = libraryDAO.getAllItemTypes();
        assertNotNull(itemTypes);
        for(ItemType itemType:itemTypes)
        {
            System.out.println(itemType);
        }
    }

    @Test
    public void testGetAllItems() {
        List<ILibraryItem> items = libraryDAO.getAllItems();
        assertNotNull(items);
        for (ILibraryItem iLibraryItem:items) {
            System.out.println(iLibraryItem);
        }
    }


//    @Test
//    public void addAndRemoveItemTypes(){
//        ItemType type = new ItemType(10,"Test1","Test123");
//        libraryDAO.addItemType(type);
//        //remove the itemtype that has been added
//        libraryDAO.removeItemType(type);
//    }
}
