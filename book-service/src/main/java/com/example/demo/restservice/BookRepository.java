/*
 * Copyright (c) 2022 Phenom People, Inc.
 * All rights reserved.
 */
package com.example.demo.restservice;

import static java.util.stream.Collectors.toList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;

/**
 *
 * @author rakesh.shah
 *
 */
@Service
public class BookRepository {

    /** concurrent hashmap. */
    private ConcurrentHashMap<Long, Book> books = new ConcurrentHashMap<>();

    /** constant. */
    private static final int MAX = 100;

    /** constructor. */
    public BookRepository() {

        for (int i = 0; i < MAX; i++) {
            Book book = new Book();
            book.setId(i);
            book.setName("abc" + i);
            book.setAuthor("bcd" + i);
            book.setNoOfPages(i + MAX);
            books.put((long) i, book);
        }
    }

    /**
     * findByID.
     *
     * @param id - id of a book
     * @return book
     */
    public Book findById(long id) {
        return books.get(id);
    }

    /**
     * Returns the collection of the books.
     *
     * @return collection of books
     */
    public Collection<Book> getBooks() {
        return books.values();
    }

    /**
     * returns the paginated response.
     *
     * @param pageable
     * @return page
     */
    public Page<Book> getBooks(Pageable pageable) {
        int toSkip = pageable.getPageSize() * pageable.getPageNumber();
        List<Book> result = books.values().stream().skip(toSkip).limit(pageable.getPageSize()).collect(toList());
        return new PageImpl<>(result, pageable, books.size());
    }

    /**
     * add book to the book store.
     *
     * @param book - book object
     * @return recently added book
     */
    public Book addBook(Book book) {
        long id = books.size() + 1;
        book.setId(id);
        saveBook(book);
        return book;
    }

    /**
     * Save book in the local collection.
     * @param book - object to be saved.
     * @return patched book object with partial update
     */
    public Book saveBook(Book book) {
        return books.put(book.getId(), book);
    }

}
