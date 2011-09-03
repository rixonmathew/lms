package com.rixon.lms.main;

import com.rixon.lms.business.ItemTypeProvider;
import com.rixon.lms.business.PropertyProvider;
import com.rixon.lms.domain.*;
import com.rixon.lms.type.OwnerType;
import com.rixon.lms.util.PropertyConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 9/1/11 - 7:55 PM
 */
public class MockDataProvider {

    private static final String BOOK="BOOK";
    private static final String MOVIE="MOVIE";

    public static List<ILibraryItem> getListOfMockBooks() {
        return null;
    }

    static List<ILibraryItem> getMockListOfBooks() {
        List<ILibraryItem> books = new ArrayList<ILibraryItem>();
        books.add(new BookBuilder().setTitle("Effective Java").setIsbn("9780321356680").setCategory("Technical:Java")
                                   .addAuthor("Joshua Bloch").createBook());
        books.add(new BookBuilder().setTitle("Effective Java").setIsbn("9780321356680").setCategory("Technical:Java")
                                   .addAuthor("Joshua Bloch").createBook());
        books.add(new BookBuilder().setTitle("David Copperfield").addAuthor("Charles Dickens").setIsbn("9181321356680")
                                   .setCategory("Fiction:Classic").createBook());
        books.add(new BookBuilder().setTitle("David Copperfield").addAuthor("Charles Dickens").setIsbn("9181321356680")
                                   .setCategory("Fiction:Classic").createBook());
        books.add(new BookBuilder().setTitle("David Copperfield").addAuthor("Charles Dickens").setIsbn("9181321356680")
                                   .setCategory("Fiction:Classic").createBook());
        books.add(new BookBuilder().setTitle("TDD By Example").addAuthor("Kent Beck").setIsbn("9180321356680")
                                   .setCategory("Technical:TDD").createBook());
        books.add(new BookBuilder().setTitle("TDD By Example").addAuthor("Kent Beck").setIsbn("9180321356680")
                                   .setCategory("Technical:TDD").createBook());
        books.add(new BookBuilder().setTitle("Clean Code").addAuthor("Robert C Martin").setIsbn("9180321336680")
                                   .setCategory("Technical:Programming").createBook());
        books.add(new BookBuilder().setTitle("The Pragmatic Programmer, From Journeyman To Master").addAuthor("Andrew Hunt")
                                   .setIsbn("9180421356680").setCategory("Technical:Programming").createBook());
        books.add(new BookBuilder().setTitle("The Lord of the Rings").addAuthor("JRR Tolkien").setIsbn("9180521121680")
                                   .setCategory("Classic:Fiction").createBook());
        return books;
    }

    static  List<ILibraryItem> getMockListOfMovies() {
        List<ILibraryItem> movies = new ArrayList<ILibraryItem>();

        movies.add(new MovieBuilder().setTitle("Godfather").setDirector("Francis Ford Coppolla").setRating("U/A")
                                     .setBarCode("BAR11100001").setGenre("Thriller").createMovie());
        movies.add(new MovieBuilder().setTitle("Godfather").setDirector("Francis Ford Coppolla").setRating("U/A")
                                     .setBarCode("BAR11100001").setGenre("Thriller").createMovie());

        movies.add(new MovieBuilder().setTitle("Sholay").setDirector("Ramesh Sippy").setRating("G")
                                     .setBarCode("BAR11100011").setGenre("Action").createMovie());
        movies.add(new MovieBuilder().setTitle("Sholay").setDirector("Ramesh Sippy").setRating("G")
                                     .setBarCode("BAR11100011").setGenre("Action").createMovie());

        movies.add(new MovieBuilder().setTitle("Dabangg").setDirector("Arbaaz Khan").setBarCode("BAR11100021")
                                     .setRating("G").setGenre("Masala").createMovie());
        movies.add(new MovieBuilder().setTitle("Dabangg").setDirector("Arbaaz Khan").setBarCode("BAR11100021")
                                     .setRating("G").setGenre("Masala").createMovie());

        movies.add(new MovieBuilder().setTitle("Inception").setDirector("Christopher Nolan").setBarCode("BAR11100031")
                                     .setRating("G").setRating("Futuristic").createMovie());
        movies.add(new MovieBuilder().setTitle("Inception").setDirector("Christopher Nolan").setBarCode("BAR11100031")
                                     .setRating("G").setRating("Futuristic").createMovie());

        movies.add(new MovieBuilder().setTitle("The Social Network").setDirector("David Fincher")
                                     .setBarCode("BAR11100041").setRating("G").setGenre("Book Adaption").createMovie());
        movies.add(new MovieBuilder().setTitle("The Social Network").setDirector("David Fincher")
                                     .setBarCode("BAR11100041").setRating("G").setGenre("Book Adaption").createMovie());

        movies.add(new MovieBuilder().setTitle("The Lord of the Rings").setDirector("Peter Jackson")
                                     .setBarCode("BAR11100051").setGenre("War").setRating("PG-13").createMovie());
        movies.add(new MovieBuilder().setTitle("The Lord of the Rings").setDirector("Peter Jackson")
                                     .setBarCode("BAR11100051").setGenre("War").setRating("PG-13").createMovie());

        return movies;
    }

    static class BookBuilder {
        private String title;
        private String isbn;
        private final List<String> authors = new ArrayList<String>();
        private String category;

        BookBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        BookBuilder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        BookBuilder addAuthor(String author) {
            this.authors.add(author);
            return  this;
        }

        BookBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        ILibraryItem createBook() {

            LibraryItem.LibraryItemBuilder itemBuilder = new LibraryItem.LibraryItemBuilder();
            itemBuilder.setItemType(ItemTypeProvider.getItemType(BOOK));
            UniqueIdentifier identifier = TestUtils.getUniqueIdForBook(isbn);
            itemBuilder.setIdentifier(identifier);
            itemBuilder.setName(title);
            ItemPropertyValue categoryInformation = new ItemPropertyValue.ItemPropertyValueBuilder()
                    .setProperty(PropertyProvider.getProperty(PropertyConstants.CATEGORY))
                    .setPropertyValue(category).createItemPropertyValue();
            Map<Property, ItemPropertyValue> customProperties = new HashMap<Property, ItemPropertyValue>();
            customProperties.put(PropertyProvider.getProperty(PropertyConstants.CATEGORY),categoryInformation);
            itemBuilder.setItemProperties(customProperties);
            return itemBuilder.createLibraryItem();
        }

    }

    static class MovieBuilder {

        private String title;
        private String director;
        private String rating;
        private String barCode;
        private String genre;

        MovieBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        MovieBuilder setDirector(String director) {
            this.director = director;
            return this;
        }

        MovieBuilder setRating(String rating)
        {
            this.rating = rating;
            return this;
        }

        MovieBuilder setBarCode(String barCode) {
            this.barCode = barCode;
            return this;
        }

        MovieBuilder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        ILibraryItem createMovie()
        {
            LibraryItem.LibraryItemBuilder itemBuilder = new LibraryItem.LibraryItemBuilder();
            itemBuilder.setItemType(ItemTypeProvider.getItemType(MOVIE));
            UniqueIdentifier identifier = new UniqueIdentifier.UniqueIdentifierBuilder().setType(PropertyConstants.BARCODE)
                                               .setValue(barCode).createUniqueIdentifier();
            itemBuilder.setIdentifier(identifier);
            itemBuilder.setName(title);
            ItemOwnerInformation ownerInformation = ItemOwnerInformation.createWithSingleOwner(OwnerType.DIRECTOR, director);
            //itemBuilder.setOwnerInformation(ownerInformation);
            ItemPropertyValue categoryInformation = new ItemPropertyValue.ItemPropertyValueBuilder()
                    .setProperty(PropertyProvider.getProperty(PropertyConstants.RATING))
                    .setPropertyValue(rating).createItemPropertyValue();
            ItemPropertyValue genreInformation = new ItemPropertyValue.ItemPropertyValueBuilder()
                    .setProperty(PropertyProvider.getProperty(PropertyConstants.GENRE))
                    .setPropertyValue(genre).createItemPropertyValue();
            Map<Property, ItemPropertyValue> customProperties = new HashMap<Property, ItemPropertyValue>();
            customProperties.put(PropertyProvider.getProperty(PropertyConstants.CATEGORY),categoryInformation);
            customProperties.put(PropertyProvider.getProperty(PropertyConstants.GENRE),genreInformation);
            itemBuilder.setItemProperties(customProperties);
            return itemBuilder.createLibraryItem();
        }
   }
}
