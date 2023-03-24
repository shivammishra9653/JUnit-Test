/*
 * Copyright (c) 2022 Phenom People, Inc.
 * All rights reserved.
 */

package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.Book;
import com.example.demo.restservice.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookController.class)
@RunWith(SpringRunner.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;


    @MockBean
    private BookRepository repository;


    @Autowired
    private BookController controller;

    /**
     * TestFindByID functionality.
     *
     * @throws Exception
     */
    @Test
    public void testFindByID() throws Exception {
        Book book = getDummyBook();
        when(repository.findById(1L)).thenReturn(book);

        this.mvc.perform(get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.author").value("Rakesh"));
    }

    private Book getDummyBook() {
        //Mocked Object
        Book book = new Book();
        book.setName("Java Programming");
        book.setId(1L);
        book.setAuthor("Rakesh");
        book.setYearOfPublish(1988);
        book.setNoOfPages(100);
        return book;
    }

    @Test
    public void testFindByIDWithException() throws Exception {
        Book book = getDummyBook();
        when(repository.findById(1L)).thenReturn(book);

        this.mvc.perform(get("/api/books/0")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().is4xxClientError());
    }

    /**
     * TestFindByID functionality.
     *
     * @throws Exception
     */
    @Test
    public void testBooks() throws Exception {

        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setId(0);
            book.setAuthor("Rakesh");
            book.setYearOfPublish(1988);
            book.setNoOfPages(100);
            books.add(book);
        }
        when(repository.getBooks()).thenReturn(books);

        this.mvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].author").value("Rakesh"));
    }

    /**
     * Create Book Unit Test.
     */
    @Test
    public void testCreateBook() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Book book = getDummyBook();
        when(repository.addBook(book)).thenReturn(book);
        String bookJson = mapper.writeValueAsString(book);
        MvcResult andReturn = this.mvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON).content(bookJson)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                        .andReturn();

    }


    /**
     * Test the update scenario 
     */

    @Test
    public void testUpdateBook() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Book book = getDummyBook();
        when(repository.findById(1L)).thenReturn(book);
        when(repository.saveBook(book)).thenReturn(book);
        String bookJson = mapper.writeValueAsString(book);
        MvcResult andReturn = this.mvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON).content(bookJson)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                        .andReturn();
    }


    @Test
    public void testUpdateBookException() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Book book = getDummyBook();
        when(repository.findById(0)).thenReturn(null);
        String bookJson = mapper.writeValueAsString(book);
        MvcResult andReturn = this.mvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON).content(bookJson)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isPreconditionFailed())
                        .andReturn();
    }

    @Test
    public void testPatchBook() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Book book = getDummyBook();
        when(repository.findById(1L)).thenReturn(book);
        when(repository.saveBook(book)).thenReturn(book);
        String bookJson = mapper.writeValueAsString(book);
        MvcResult andReturn = this.mvc.perform(patch("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON).content(bookJson))
                        .andExpect(status().isOk())
                        .andReturn();
    }

}
