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

package com.rixon.lms.dao;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.rixon.lms.dao.resultset.ItemRS;
import com.rixon.lms.dao.resultset.ItemTypeRS;
import com.rixon.lms.dao.resultset.MemberRS;
import com.rixon.lms.dao.resultset.RoleRS;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;


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
        List<ItemTypeRS> itemTypeRSs = libraryDAO.getAllItemTypes();
        assertNotNull(itemTypeRSs);
        for(ItemTypeRS itemTypeRS : itemTypeRSs)
        {
            System.out.println(itemTypeRS);
        }
    }

    @Test
    public void testGetAllItems() {
        List<ItemRS> itemRSs = libraryDAO.getAllItems();
        assertNotNull(itemRSs);
        for (ItemRS itemRS : itemRSs) {
            System.out.println(itemRS);
        }
    }

    @Test
    public void testGetItemByName() {
        String itemName = "Programming Perl";
        List<ItemRS> itemRSs = libraryDAO.getItemByName(itemName);
        assertNotNull(itemRSs);
        for(ItemRS itemRS : itemRSs) {
            assertEquals("Name is not same",itemName, itemRS.getName());
        }
    }

//    @Test
//    public void addAndRemoveItemTypes(){
//        ItemTypeRS type = new ItemTypeRS(10,"Test1","Test123");
//        libraryDAO.addItemType(type);
//        //remove the itemtype that has been added
//        libraryDAO.removeItemType(type);
//    }
    
    @Test
    public void testGetAllRoles() {
        List<RoleRS> rolesResultSet = libraryDAO.getAllRoles();
        assertNotNull(rolesResultSet);
        for (RoleRS role:rolesResultSet) {
        	System.out.println(role);
        }
    	
    }

    @Test
    public void testValidMember() {
        final String emailId="rixonmathew@gmail.com";
        final String password="lms123#";
        MemberRS memberRS = libraryDAO.findMember(emailId,password);
        assertNotNull(memberRS);
        assertEquals("Email id does not match",emailId,memberRS.getEmailId());
        assertEquals("Password does not match",password,memberRS.getPassword());
    }


    @Test
    public void testInvalidMember() {
        final String emailId="notpresent@gmail.com";
        final String password="password";
        MemberRS memberRS = libraryDAO.findMember(emailId,password);
        assertNull(memberRS);
    }

    @Test
    public void addNewMember() {
        final String email = "stevejobs@apple.com";
        final String password = "apple123";
        MemberRS memberRS = DAOMockDataProvider.getMemberRecord("Steve","Jobs",email,"1158876659","Cupertino in United States of America", password);
        libraryDAO.addMember(memberRS);
        //find the newly Added member
        memberRS = libraryDAO.findMember(email,password);
        assertNotNull(memberRS);
        assertEquals("Email id does not match",email,memberRS.getEmailId());
        assertEquals("Password does not match",password,memberRS.getPassword());
    }
}
