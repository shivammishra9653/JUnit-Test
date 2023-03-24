/*
 * Copyright (c) 2022 Phenom People, Inc.
 * All rights reserved.
 */
package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Model object for book repository.
 *
 * @author rakesh.shah
 *
 */
public final class Book {
    /** id. */
    private long id;
    /** id. */
    private String name;
    /** id. */
    private String author;
    /** id. */
    private int noOfPages;
    /** id. */
    private int yearOfPublish;
    /** id. */
    private Details details;
    /** max length.*/
    private static final int MAX = 30;

    /**
     * Constructor.
     */
    public Book() {

    }

    /**
     * Returns name of the book.
     * @return book-name
     */
    @NotBlank
    @Size(min = 0, max = MAX)
    public String getName() {
        return name;
    }

    /**
     * Sets name of the book.
     * @param name - name of the book
     */
    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    /**
     *
     * @return author
     */
    @NotBlank
    @Size(min = 0, max = MAX)
    public String getAuthor() {
        return author;
    }
    /**
     *
     * @param author
     */
    public void setAuthor(String author) {
        if (author != null) {
            this.author = author;
        }
    }

    /**
     * Returns the number of pages.
     * @return - no of pages.
     */
    public int getNoOfPages() {
        return noOfPages;
    }

    /**
     *
     * @param noOfPages
     */
    public void setNoOfPages(int noOfPages) {
        if (noOfPages > 0) {
            this.noOfPages = noOfPages;
        }
    }

    /**
     *
     * @return - year of publish
     */
    public int getYearOfPublish() {
        return yearOfPublish;
    }

    /**
     *
     * @param yearOfPublish
     */
    public void setYearOfPublish(int yearOfPublish) {
        if (yearOfPublish > 0) {
            this.yearOfPublish = yearOfPublish;
        }
    }

    /**
     *
     *@return - toString version of the object
     */
    public String toString() {
        return "name:" + name + "\nauthor:" + author + "\nnoOfPages:" + noOfPages + "\nyearOfPublish:" + yearOfPublish;
    }

    /*
     *
     * @return true if both objects are equals.ß
     *
     @Override
    public boolean equals(Object obj) {
        if (obj instanceof Book) {
            Book book = (Book) obj;
            //For simplicity null checks are omitted..
            return  this.name.equals(book.name)
                    &&
                    this.author.equals(book.name)
                    &&
                    this.noOfPages == book.noOfPages
                    &&
                    this.yearOfPublish == book.yearOfPublish;
        }
        return false;
    }
*/
    /**
     *
     * @param i
     */
    public void setId(long i) {
        this.id = i;
    }

    /**
     *
     * @return - id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @return - details
     */
    public Details getDetails() {
        return details;
    }

    /**
     *
     * @param details
     */
    public void setDetails(Details details) {
        this.details = details;
    }


    //This is a subentity for the sake of example.
    private static class Details {

        /** idbn. */
        private String isbn;
        /** category. */
        private String category;
        /** edition. */
        private String edition;


        public String getIsbn() {
            return isbn;
        }
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
        public String getCategory() {
            return category;
        }
        public void setCategory(String category) {
            this.category = category;
        }
        public String getEdition() {
            return edition;
        }
        public void setEdition(String edition) {
            this.edition = edition;
        }

    }



    /**
     *
     * @param book
     */
    public void updateData(Book book) {
        this.author = (book.author != null ? book.author : this.author);
        this.name = (book.name != null ?  book.name : this.name);
        this.yearOfPublish = (book.yearOfPublish > 0 ? book.yearOfPublish : this.yearOfPublish);
        this.noOfPages = (book.noOfPages > 0 ? book.noOfPages : this.noOfPages);
        this.details = (book.details != null ? book.details : this.details);
    }
}
