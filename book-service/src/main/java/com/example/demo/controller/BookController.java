/*
 * Copyright (c) 2022 Phenom People, Inc.
 * All rights reserved.
 */
package com.example.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.exception.BookServiceException;
import com.example.demo.model.Book;
import com.example.demo.restservice.BookRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * This is a Rest Controller for Book Service.
 * <property name="headerFile" value="/Users/rakesh.shah/dev-phenom/global-checkstyle/src/
 * main/resources//checkstyle.header" />
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Books", description = "Books API for book management")
public class BookController {

    private static final String BOOK_DOES_NOT_EXIST = "Book does not exist";
    /** This is book repository service. */
    @Autowired
    private BookRepository repository;

    /**
     * This method returns the book objects based on the path variable.
     * @param id - id of book
     * @return instance of Book object
     * @throws BookNotFoundException
     * @see {}
     */
    @Operation(summary = "Get a book by its id from the repository")
    @ApiResponses(value =
    {
      @ApiResponse(responseCode = "200", description = "Found the book",
              content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Book.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied",
              content = { @Content(mediaType = "application/json")}),
      @ApiResponse(responseCode = "404", description = "Book not found",
        content = @Content)
      })
    @GetMapping("/books/{id}")
    public Book findById(@PathVariable final long id) throws BookNotFoundException {
        Book book =  repository.findById(id);
        if (book == null) {
             throw new BookNotFoundException("Book is not available with the given id " + id);
        }
        return book;
    }

    /**
     * This method returns the paginated response of the book.
     * @param pageable - pageable interface
     * @return - collection of books
     */
    @Operation(summary = "Returns the pagindated response of the books.")
    @ApiResponses(value =
       {
               @ApiResponse(responseCode = "200", description = "Retrieved books successfully.",
                           content = { @Content(mediaType = "application/json",
                           schema = @Schema(implementation = Book.class)) }),
               @ApiResponse(responseCode = "404", description = "Books entity does not exists.",
                         content = { @Content(mediaType = "application/json")})
       })
    @GetMapping("/books/filter")
    public Page<Book> filterBooks(final Pageable pageable) {
          return repository.getBooks(pageable);
    }

    /**
     * Returns the complete collection of books available in the book repository.
     * @return collection of books
     */
    @Operation(summary = "Returns the collection of the book. This is a demo, not pagindated response.")
    @ApiResponses(value = {
       @ApiResponse(responseCode = "200", description = "Collection of books.",
        content = { @Content(mediaType = "application/json",
          schema = @Schema(implementation = Book.class)) }),
       @ApiResponse(responseCode = "404", description = "Books entity does not exists.",
        content = @Content) })

    @GetMapping("/books")
    public Collection<Book> findBooks() {
        return repository.getBooks();
    }

    /**
     * It creates the book object and add it to the repository assigning a new unique ID.
     * @param book - payload
     * @param type - experimental parameter
     * @return returns newly created book
     */
    @Operation(summary = "creates a book in the library")
    @ApiResponses(value = {
              @ApiResponse(responseCode = "201", description = "This book is added successfully.",
                      content = { @Content(mediaType = "application/json")}),
              @ApiResponse(responseCode = "502", description = "Book already exists.",
                      content = @Content)
    })
    @PostMapping("/books")
    public Book createBook(@RequestBody final Book book, @Param(value = "type") final String type) {
        return repository.addBook(book);
    }
    /**
     * This is an update operation on for a given book unique ID.
     * @param id - unique Id of book
     * @param book - payload of book data to be updated.
     * @return - true if update is successful.
     * @throws BookServiceException
     */
    @Operation(summary = "Updates the book entity for a given book id.")
    @ApiResponses(value = {
       @ApiResponse(responseCode = "200", description = "Data updated successfully.",
        content = { @Content(mediaType = "application/json",
          schema = @Schema(implementation = Book.class)) }),
       @ApiResponse(responseCode = "404", description = "Book does not exists for a given book id",
        content = @Content) })

    @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@PathVariable long id, @RequestBody final Book book) throws BookServiceException {
        Book foundBook = repository.findById(id);
        if (foundBook != null) {
            foundBook.setAuthor(book.getAuthor());
            foundBook.setName(book.getName());
            foundBook.setYearOfPublish(book.getYearOfPublish());
            foundBook.setNoOfPages(book.getNoOfPages());
            return repository.saveBook(foundBook);
        }
        throw new BookServiceException(BOOK_DOES_NOT_EXIST);
    }


    /**
     * This is for updating book object.
     * @param id
     * @param book
     * @return - true if book object is patched.
     * @throws BookServiceException
     */
    @Operation(summary = "Patch the book entity for a given book id payload")
    @ApiResponses(value = {
       @ApiResponse(responseCode = "200", description = "Data updated successfully.",
        content = { @Content(mediaType = "application/json",
          schema = @Schema(implementation = Book.class)) }),
       @ApiResponse(responseCode = "404", description = "Book does not exists for a given book id",
        content = @Content) })

    @PatchMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book patchBook(
      @PathVariable("id") final long id, @RequestBody final Book book) throws BookServiceException {
        Book foundBook = repository.findById(id);
        if (foundBook != null) {
            foundBook.setAuthor(book.getAuthor());
            foundBook.setName(book.getName());
            foundBook.setYearOfPublish(book.getYearOfPublish());
            foundBook.setNoOfPages(book.getNoOfPages());
            return repository.saveBook(foundBook);
        }
        throw new BookServiceException(BOOK_DOES_NOT_EXIST);
    }

   }