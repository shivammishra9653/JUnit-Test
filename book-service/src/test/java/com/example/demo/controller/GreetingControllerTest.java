/*
 * Copyright (c) 2022 Phenom People, Inc.
 * All rights reserved.
 */

package com.example.demo.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.Greeting;

@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Sample Test.
     */
    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        this.mockMvc.perform(get("/v1/api/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }

    /**
     * Sample Test.
     */
    @Test
    public void validateGreeting() throws Exception {
        Greeting greeting = new Greeting(1, "Hello, Rakesh!");
        this.mockMvc.perform(get("/v1/api/greeting?name=Rakesh")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(greeting.getContent())));
    }
}