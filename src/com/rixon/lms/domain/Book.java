package com.rixon.lms.domain;

import java.util.Date;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 7:56 PM
 */
public class Book {

    private String title;
    private String author;
    private UniqueIdentifier isbn;
    private String publisher;
    private Date publishedDate;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public UniqueIdentifier getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public Book(String title, String author, UniqueIdentifier isbn, String publisher, Date publishedDate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }


    public static class BookBuilder {
        private String title;
        private String author;
        private UniqueIdentifier isbn;
        private String publisher;
        private Date publishedDate;

        public BookBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder setIsbn(UniqueIdentifier isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookBuilder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public BookBuilder setPublishedDate(Date publishedDate) {
            this.publishedDate = publishedDate;
            return this;
        }

        public Book createBook() {
            return new Book(title, author, isbn, publisher, publishedDate);
        }
    }
}
