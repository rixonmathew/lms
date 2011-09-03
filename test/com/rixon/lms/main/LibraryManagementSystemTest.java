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

import com.rixon.lms.business.ItemTypeProvider;
import com.rixon.lms.business.PropertyProvider;
import com.rixon.lms.domain.*;
import com.rixon.lms.exception.LibrarySystemException;
import com.rixon.lms.type.CheckOutStatus;
import com.rixon.lms.util.PropertyConstants;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Test Class to Test the requirements for the LibraryManagementSystem System.
 */
public class LibraryManagementSystemTest {

    private static LibraryManagementSystem libraryManagementSystem;

    //Utility variables
    private static DateFormat dateFormat;
    private static final String BOOK="BOOK";
    private static final String MOVIE="MOVIE";

    @BeforeClass
    public static void setUpLibrary() {
        libraryManagementSystem = new LibraryManagementSystem();
        dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        addMockDataToLibrary(libraryManagementSystem);
    }

    @AfterClass
    public static void tearDown() {
        libraryManagementSystem = null;
    }

    @Test
    public void testBookCatalog() {
        Set<ILibraryItem> libraryBookCatalog = libraryManagementSystem.getAllItems(ItemTypeProvider.getItemType(BOOK));
        assertNotNull(libraryBookCatalog);
        assertTrue(libraryBookCatalog.size() > 0);
    }

    @Test
    public void testMovieCatalog() {
        Set<ILibraryItem> movieCatalog = libraryManagementSystem.getAllItems(ItemTypeProvider.getItemType(MOVIE));
        assertNotNull(movieCatalog);
        assertTrue(movieCatalog.size()>0);
    }

    @Test
    public void testAddMember() {
        UniqueIdentifier membershipId = new UniqueIdentifier.UniqueIdentifierBuilder().setType("ID").setValue("201").createUniqueIdentifier();
        libraryManagementSystem.addMember(new LibraryMember(membershipId, "Ravi", "Shetty", "603-546-5619"));
        LibraryMember member = libraryManagementSystem.searchForMemberById(membershipId);
        assertNotNull(member);
        assertEquals("Member id is not same", membershipId, member.getMembershipId());
    }


    @Test
    public void testAddBook() {
        String bookTitle = "SOLID Principles";
        List<ILibraryItem> libraryItems = new ArrayList<ILibraryItem>();
        libraryItems.add(new MockDataProvider.BookBuilder().setTitle(bookTitle).setIsbn("112-1-12-123123-1").setCategory("Technical:OOPS")
                                           .addAuthor("Pablo").addAuthor("Robert C Martin").createBook());
        libraryManagementSystem.addLibraryItems(ItemTypeProvider.getItemType(BOOK), libraryItems);
        SearchResult searchedBooks = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(BOOK), bookTitle);
        //WE expect only one book so assert the list size
        List<SearchResultDetail> details = searchedBooks.getAllSearchedItems();
        assertEquals("Size not 1",1,details.size());
        ILibraryItem book = details.get(0).getLibraryItem();
        assertEquals("Book Title is not same", bookTitle, book.getName());
    }

    @Test
    public void testAddMovie() {
        String movieTitle = "Ready";
        List<ILibraryItem> movies = new ArrayList<ILibraryItem>();
        movies.add(new MockDataProvider.MovieBuilder().setTitle(movieTitle).setDirector("Anees Bazmee").setBarCode("BAR11223")
                                     .setRating("G").setGenre("Comedy").createMovie());
        libraryManagementSystem.addLibraryItems(ItemTypeProvider.getItemType(MOVIE), movies);
        SearchResult searchedMovies = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(MOVIE), movieTitle);
        List<SearchResultDetail> details = searchedMovies.getAllSearchedItems();
        //WE expect only one book so assert the list size
        assertEquals("Size not 1",1,details.size());
        ILibraryItem movie = details.get(0).getLibraryItem();
        assertEquals("Movie Title is not same", movieTitle, movie.getName());
    }

    @Test
    public void testArchivalOfBooks() {
        String bookTitle = "Effective Java";
        SearchResult searchedBooks = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(BOOK), bookTitle);
        List<SearchResultDetail> details = searchedBooks.getAllSearchedItems();
        //WE expect only one book so assert the list size
        assertEquals("Size not 1", 1, details.size());
        ILibraryItem book = details.get(0).getLibraryItem();
        libraryManagementSystem.archiveItem(book);
        searchedBooks = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(BOOK), bookTitle);
        details = searchedBooks.getAllSearchedItems();
        //WE expect only one book so assert the list size
        assertEquals("Size not 1", 1, details.size());

        assertEquals("Title not same", bookTitle, details.get(0).getLibraryItem().getName());
        assertEquals("Archive count not same", details.get(0).getTotalCopiesInLibrary(), details.get(0).getCopiesArchived());

        libraryManagementSystem.unArchiveItem(book);
        searchedBooks = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(BOOK), bookTitle);
        assertNotNull(searchedBooks);
        details = searchedBooks.getAllSearchedItems();
        assertEquals("Size not 1", 1, details.size());

        assertEquals("Title not same", bookTitle, details.get(0).getLibraryItem().getName());
       assertEquals("Archive count not 0", 0, details.get(0).getCopiesArchived());
    }


    @Test
    public void testArchivalOfMovies() {
        String movieTitle = "Dabangg";
        SearchResult searchedMovies = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(MOVIE), movieTitle);
        List<SearchResultDetail> details = searchedMovies.getAllSearchedItems();
        //WE expect only one book so assert the list size
        assertEquals("Size not 1", 1, details.size());
        ILibraryItem book = details.get(0).getLibraryItem();
        libraryManagementSystem.archiveItem(book);
        searchedMovies = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(MOVIE), movieTitle);
        details = searchedMovies.getAllSearchedItems();
        //WE expect only one book so assert the list size
        assertEquals("Size not 1", 1, details.size());

        assertEquals("Title not same", movieTitle, details.get(0).getLibraryItem().getName());

        assertEquals("Archive count not same", details.get(0).getTotalCopiesInLibrary(), details.get(0).getCopiesArchived());

        libraryManagementSystem.unArchiveItem(book);
        searchedMovies = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(MOVIE), movieTitle);
        assertNotNull(searchedMovies);
        details = searchedMovies.getAllSearchedItems();
        assertEquals("Size not 1", 1, details.size());

        assertEquals("Title not same", movieTitle, details.get(0).getLibraryItem().getName());

        assertEquals("Archive count not 0", 0, details.get(0).getCopiesArchived());
    }


    @Test
    public void testAddMultipleCopiesOfBooks() {
        ILibraryItem book1 = new MockDataProvider.BookBuilder().setTitle("Let us C").addAuthor("Yeshwant Kanetkar").setIsbn("978-3-16-148410-0").createBook();
        ILibraryItem book2 = new MockDataProvider.BookBuilder().setTitle("Let us C").addAuthor("Yeshwant Kanetkar").setIsbn("978-3-16-148410-0").createBook();
        List<ILibraryItem> books = new ArrayList<ILibraryItem>();
        books.add(book1);
        books.add(book2);
        String bookISBN = "978-3-16-148410-0";
        libraryManagementSystem.addLibraryItems(ItemTypeProvider.getItemType(BOOK), books);
        UniqueIdentifier uniqueIdForBook = TestUtils.getUniqueIdForBook(bookISBN);
        SearchResult searchedBook = libraryManagementSystem.searchForItemByIdentifier(ItemTypeProvider.
                getItemType(BOOK), uniqueIdForBook);
        assertNotNull(searchedBook);
        SearchResultDetail searchResultDetail = searchedBook.getSearchDetailForId(uniqueIdForBook);
        assertEquals("Count not same",2,searchResultDetail.getTotalCopiesInLibrary());

    }

    @Test
    public void testAddMultipleCopiesOfMovies() {
        String barCode = "BarCode110110110";
        ILibraryItem movie1 = new MockDataProvider.MovieBuilder().setTitle("Mera Naam Joker").setDirector("Raj Kapoor")
                                                .setBarCode(barCode).setRating("PG-15").setGenre("Tragedy")
                                                .createMovie();
        ILibraryItem movie2 = new MockDataProvider.MovieBuilder().setTitle("Mera Naam Joker").setDirector("Raj Kapoor")
                                                .setBarCode(barCode).setRating("PG-15").setGenre("Tragedy")
                                                .createMovie();
        List<ILibraryItem> movies = new ArrayList<ILibraryItem>();
        movies.add(movie1);
        movies.add(movie2);
        libraryManagementSystem.addLibraryItems(ItemTypeProvider.getItemType(MOVIE),movies);
        UniqueIdentifier uniqueIdForMovie = TestUtils.getUniqueIdForMovie(barCode);
         SearchResult searchedMovie = libraryManagementSystem.searchForItemByIdentifier(ItemTypeProvider.
                 getItemType(MOVIE), uniqueIdForMovie);
         assertNotNull(searchedMovie);
         SearchResultDetail searchResultDetail = searchedMovie.getSearchDetailForId(uniqueIdForMovie);
         assertEquals("Count not same",2,searchResultDetail.getTotalCopiesInLibrary());
    }

    @Test
    public void testAssingRatingsToMovies() {
        String movieRating = "PG";
        String movieBarCode = "BAR11100011";
        UniqueIdentifier identifier = new UniqueIdentifier.UniqueIdentifierBuilder().setType(PropertyConstants.BARCODE).
                                           setValue(movieBarCode).createUniqueIdentifier();
        SearchResult searchedMovie = libraryManagementSystem.searchForItemByIdentifier(ItemTypeProvider
                .getItemType(MOVIE), identifier);
        assertNotNull(searchedMovie);
        ILibraryItem movie = searchedMovie.getSearchDetailForId(identifier).getLibraryItem();
        libraryManagementSystem.setCustomProperty(new ItemPropertyValue.ItemPropertyValueBuilder().
                setProperty(PropertyProvider.getProperty(PropertyConstants.RATING)).setPropertyValue(movieRating)
                .createItemPropertyValue(),movie);
        searchedMovie = libraryManagementSystem.searchForItemByIdentifier(ItemTypeProvider.getItemType(MOVIE),
                identifier);
        movie = searchedMovie.getSearchDetailForId(identifier).getLibraryItem();
        String searchedMovieRating = movie.getItemPropertyValue(PropertyProvider.getProperty(PropertyConstants.RATING))
                .getPropertyValue();
        assertEquals("Movie Rating is not same", movieRating, searchedMovieRating);
    }


    @Test
    public void testAssignCategoryToBooks() {
        String bookISBN = "9780321356680";
        UniqueIdentifier identifier = TestUtils.getUniqueIdForBook(bookISBN);

        String bookCategory = "Fiction;Mystery";
        //TODO Category is hierarchical. Think of a better data structure or format;

        SearchResult searchResult = libraryManagementSystem.searchForItemByIdentifier(ItemTypeProvider.
                getItemType(BOOK), identifier);
        assertNotNull(searchResult);
        ILibraryItem book = searchResult.getSearchDetailForId(identifier).getLibraryItem();
        libraryManagementSystem.setCustomProperty(new ItemPropertyValue.ItemPropertyValueBuilder()
                .setProperty(PropertyProvider.getProperty(PropertyConstants.CATEGORY)).setPropertyValue(bookCategory)
                .createItemPropertyValue(),book);
        searchResult = libraryManagementSystem.searchForItemByIdentifier(ItemTypeProvider.getItemType(BOOK), identifier);
        book = searchResult.getSearchDetailForId(identifier).getLibraryItem();
        String searchedBookCategory = book.getItemPropertyValue(PropertyProvider.
                getProperty(PropertyConstants.CATEGORY)).getPropertyValue();
        assertEquals("Category is not same", bookCategory, searchedBookCategory);
    }


    @Test
    public void testSimpleCheckOutForBooks() {
        LibraryMember member;
        SearchResult searchResult = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(BOOK) ,"David Copperfield");
        assertNotNull(searchResult);
        assertEquals("Size not 1",1,searchResult.getAllSearchedItems().size());

        ILibraryItemInstance bookInstance =  searchResult.getAllSearchedItems().get(0).getNextAvailableInstance();
        UniqueIdentifier memberId = new UniqueIdentifier.UniqueIdentifierBuilder().setType("ID").setValue("1").createUniqueIdentifier();
        member = libraryManagementSystem.searchForMemberById(memberId);
        CheckedOutItem checkedOutItem = new CheckedOutItem.CheckedOutItemBuilder()
                                        .setLibraryItemInstance(bookInstance).setMember(member)
                                        .setCheckedOutDate(getFormattedDate("06/16/2011"))
                                        .setReturnDate(getFormattedDate("06/30/2011")).createItemCheckedOut();

        libraryManagementSystem.checkOutItemToMember(checkedOutItem);

        List<CheckedOutItem> booksCheckedOutItem = libraryManagementSystem.getCheckedOutItemsForMember(ItemTypeProvider.getItemType(BOOK), member);
        assertNotNull(booksCheckedOutItem);
        assertEquals("Size not 1",1, booksCheckedOutItem.size());
        CheckedOutItem checkedOut2Item = booksCheckedOutItem.get(0);
        assertEquals("Checked out items do not match",checkedOutItem, checkedOut2Item);
        assertTrue(booksCheckedOutItem.contains(checkedOutItem));
    }

    @Test
    public void testSimpleCheckOutForMovies() {

        LibraryMember member;
        SearchResult searchResult = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(MOVIE) ,"Sholay");
        assertNotNull(searchResult);
        assertEquals("Size not 1",1,searchResult.getAllSearchedItems().size());

        ILibraryItemInstance movieInstance =  searchResult.getAllSearchedItems().get(0).getNextAvailableInstance();
        UniqueIdentifier memberId = new UniqueIdentifier.UniqueIdentifierBuilder().setType("ID").setValue("1").createUniqueIdentifier();
        member = libraryManagementSystem.searchForMemberById(memberId);
        CheckedOutItem checkedOutItem = new CheckedOutItem.CheckedOutItemBuilder()
                                        .setLibraryItemInstance(movieInstance).setMember(member)
                                        .setCheckedOutDate(getFormattedDate("06/16/2011"))
                                        .setReturnDate(getFormattedDate("06/30/2011")).createItemCheckedOut();

        libraryManagementSystem.checkOutItemToMember(checkedOutItem);

        List<CheckedOutItem> moviesCheckedOutItem = libraryManagementSystem.getCheckedOutItemsForMember(ItemTypeProvider.getItemType(MOVIE), member);
        assertNotNull(moviesCheckedOutItem);
        assertEquals("Size not 1", 1, moviesCheckedOutItem.size());
        CheckedOutItem checkedOut2Item = moviesCheckedOutItem.get(0);
        assertEquals("Checked out items do not match", checkedOutItem, checkedOut2Item);
        assertTrue(moviesCheckedOutItem.contains(checkedOutItem));
    }

    @Test(expected = LibrarySystemException.class)
    public void testCheckOutLimitForBooks() {
        int bookCheckOutLimit = libraryManagementSystem.getItemCheckOutLimit(ItemTypeProvider.getItemType(BOOK));
        LibraryMember member = libraryManagementSystem.searchForMemberById(getUniqueIdForMember("2"));
        List<ILibraryItem> books = MockDataProvider.getMockListOfBooks();
        if ( bookCheckOutLimit > books.size()) {
            throw new IllegalArgumentException("Mock list of books s lesser than check out limit. Add more mock books");
        }
        for (ILibraryItem book : books) {
            SearchResult searchResult = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(BOOK), book.getName());
            assertNotNull(searchResult);
            assertEquals("Size not 1", 1, searchResult.getAllSearchedItems().size());
            ILibraryItemInstance bookInstance = searchResult.getAllSearchedItems().get(0).getNextAvailableInstance();
            if (bookInstance == null)
                continue;

            CheckedOutItem checkedOutItem = new CheckedOutItem.CheckedOutItemBuilder()
                    .setLibraryItemInstance(bookInstance).setMember(member)
                    .setCheckedOutDate(getFormattedDate("06/16/2011"))
                    .setReturnDate(getFormattedDate("06/30/2011")).createItemCheckedOut();
            libraryManagementSystem.checkOutItemToMember(checkedOutItem);
        }
    }



    @Test(expected = LibrarySystemException.class)
    public void testCheckOutLimitforMovies() {
        int movieCheckOutLimit = libraryManagementSystem.getItemCheckOutLimit(ItemTypeProvider.getItemType(MOVIE));

        LibraryMember member = libraryManagementSystem.searchForMemberById(getUniqueIdForMember("2"));
        List<ILibraryItem> movies = MockDataProvider.getMockListOfMovies();
        if (movieCheckOutLimit > movies.size()) {
            throw new IllegalArgumentException("Mock list of movies is lesser than check out limit. Add more mock movies");
        }

        for (int ctr = 0; ctr < movies.size(); ctr++) {
            SearchResult searchResult = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(MOVIE), movies.get(ctr).getName());
            assertNotNull(searchResult);
            assertEquals("Size not 1", 1, searchResult.getAllSearchedItems().size());
            ILibraryItemInstance movieInstance = searchResult.getAllSearchedItems().get(0).getNextAvailableInstance();
            if (movieInstance == null)
                continue;

            CheckedOutItem checkedOutItem = new CheckedOutItem.CheckedOutItemBuilder()
                    .setLibraryItemInstance(movieInstance).setMember(member)
                    .setCheckedOutDate(getFormattedDate("06/16/2011"))
                    .setReturnDate(getFormattedDate("06/30/2011")).createItemCheckedOut();
            libraryManagementSystem.checkOutItemToMember(checkedOutItem);
        }

    }


    @Test
    public void testBookReservation()
    {
        String bookISBN = "9180421356680";
        UniqueIdentifier identifier = TestUtils.getUniqueIdForBook(bookISBN);
        SearchResult searchResult = libraryManagementSystem.searchForItemByIdentifier(ItemTypeProvider.getItemType(BOOK),identifier);
        assertNotNull(searchResult);
        SearchResultDetail searchResultDetail = searchResult.getSearchDetailForId(identifier);
        LibraryMember member = libraryManagementSystem.searchForMemberById(getUniqueIdForMember("1"));
        assertNotNull(member);
        Date reservationDate = getFormattedDate("01/07/2011");
        Date reservationUptoDate = getFormattedDate("01/07/2011");

        ItemReservation  bookReservation = new ItemReservation.ItemReservationBuilder().setLibraryItemInstance(searchResultDetail.getNextAvailableInstance())
                                                          .setLibraryMember(member).setReservationDate(reservationDate)
                                                          .setReservationValidUpto(reservationUptoDate).createItemReservation();
        libraryManagementSystem.reserveItem(bookReservation);

        List<ItemReservation> booksReserved = libraryManagementSystem.getReservedItemsForMember(ItemTypeProvider.getItemType(BOOK),member);
        assertNotNull(booksReserved);
        assertTrue(booksReserved.contains(bookReservation));

        //assert that any other searchResult that has not been reserved is not in the list
        searchResult = libraryManagementSystem.searchForItemByTitle(ItemTypeProvider.getItemType(BOOK),"David Copperfield");
        assertNotNull(searchResult);
        assertEquals("Size not 1",1,searchResult.getAllSearchedItems().size());
        searchResultDetail = searchResult.getAllSearchedItems().get(0);
        ItemReservation  bookReservation2 = new ItemReservation.ItemReservationBuilder().setLibraryItemInstance(searchResultDetail.getNextAvailableInstance())
                                                                  .setLibraryMember(member).setReservationDate(reservationDate)
                                                                  .setReservationValidUpto(reservationUptoDate).createItemReservation();

        assertFalse(booksReserved.contains(bookReservation2));
    }

    @Test
    public void testReturnOfLibraryItem()
    {
        /**
         *     1)  Add 2 new books
         *     2)  Check out a book
         *     2)  Search for the book and verify checkout Count status
         *     3)  Return a book
         *     3)  Search for the book again and verify the checkout count status
         */
        List<ILibraryItem> books = createMockBooksForTestReturn();
        libraryManagementSystem.addLibraryItems(ItemTypeProvider.getItemType(BOOK),books);
        String bookId = "978-81-775-8996-2";
        UniqueIdentifier uniqueIdForBook = TestUtils.getUniqueIdForBook(bookId);
        SearchResult searchResult = libraryManagementSystem.searchForItemByIdentifier(ItemTypeProvider.getItemType(BOOK), uniqueIdForBook);
        assertNotNull(searchResult);
        SearchResultDetail searchResultDetail = searchResult.getSearchDetailForId(uniqueIdForBook);
        LibraryMember member = libraryManagementSystem.searchForMemberById(getUniqueIdForMember("1"));
        CheckedOutItem checkedOutItem = new CheckedOutItem.CheckedOutItemBuilder().setMember(member)
                                                          .setLibraryItemInstance(searchResultDetail.getNextAvailableInstance())
                                                          .setCheckedOutDate(getFormattedDate("06/17/2011"))
                                                          .setReturnDate(getFormattedDate("07/01/2011"))
                                                          .createItemCheckedOut();
        libraryManagementSystem.checkOutItemToMember(checkedOutItem);

        //Now search fo checked out item and return that
        List<CheckedOutItem> allCheckedOutItems = libraryManagementSystem.getCheckedOutItemsForMember(ItemTypeProvider.getItemType(BOOK),member);
        for(CheckedOutItem item:allCheckedOutItems) {
            if (item.equals(checkedOutItem)) {

                item.setActualReturnDate(getFormattedDate("06/18/2011"));
                item.setCheckOutStatus(CheckOutStatus.RETURNED);
                item.setFees(new Money("INR",0.00f));
                libraryManagementSystem.returnItem(item);
            }
        }
        //TODO complete the tests
        //allCheckedOutItems= libraryManagementSystem.getCheckedOutItemsForMember(ItemTypeProvider.getItemType(BOOK),member);

    }

    private List<ILibraryItem> createMockBooksForTestReturn() {
        ILibraryItem book1 = new MockDataProvider.BookBuilder().setTitle("Software Architecture in Practice").addAuthor("Len Bass").addAuthor("Paul Clements")
                                              .addAuthor("Rick Kazman").setCategory("Technical:Architecture").setIsbn("978-81-775-8996-2")
                                              .createBook();
        ILibraryItem book2 = new MockDataProvider.BookBuilder().setTitle("Software Architecture in Practice").addAuthor("Len Bass").addAuthor("Paul Clements")
                                              .addAuthor("Rick Kazman").setCategory("Technical:Architecture").setIsbn("978-81-775-8996-2")
                                              .createBook();
        ILibraryItem book3 = new MockDataProvider.BookBuilder().setTitle("Software Architecture in Practice").addAuthor("Len Bass").addAuthor("Paul Clements")
                                              .addAuthor("Rick Kazman").setCategory("Technical:Architecture").setIsbn("978-81-775-8996-2")
                                              .createBook();

        List<ILibraryItem> books = new ArrayList<ILibraryItem>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        return books;
    }


    private static Date getFormattedDate(String dateString)
    {

        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Invalid date string "+dateString);
    }



    private static void addMockDataToLibrary(LibraryManagementSystem libraryManagementSystem) {
        addMockBooksToLibrary(libraryManagementSystem);
        addMockMoviesToLibrary(libraryManagementSystem);
        addMockMemberToLibrary(libraryManagementSystem);
    }

    private static void addMockBooksToLibrary(LibraryManagementSystem libraryManagementSystem) {
        libraryManagementSystem.addLibraryItems(ItemTypeProvider.getItemType(BOOK), MockDataProvider.getMockListOfBooks());
    }


    private static void addMockMoviesToLibrary(LibraryManagementSystem libraryManagementSystem) {
        libraryManagementSystem.addLibraryItems(ItemTypeProvider.getItemType(MOVIE), MockDataProvider.getMockListOfMovies());
    }


    private static void addMockMemberToLibrary(LibraryManagementSystem libraryManagementSystem) {
        libraryManagementSystem.addMember(new LibraryMember(getUniqueIdForMember("1"), "Vaibhav", "Deshpande", "9029195455"));
        libraryManagementSystem.addMember(new LibraryMember(getUniqueIdForMember("2"), "Dinkar", "Gupta", "9029195455"));
        libraryManagementSystem.addMember(new LibraryMember(getUniqueIdForMember("3"), "Rixon", "Mathew", "9545590291"));
    }

    private static UniqueIdentifier getUniqueIdForMember(String memberId) {
        return new UniqueIdentifier.UniqueIdentifierBuilder().setType("ID").
                                                       setValue(memberId).createUniqueIdentifier();
    }
}