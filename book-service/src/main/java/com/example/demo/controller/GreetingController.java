/*
 * Copyright (c) 2022 Phenom People, Inc.
 * All rights reserved.
 */
package com.example.demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Greeting;

import io.swagger.v3.oas.annotations.Hidden;


/**
 * @author rakesh.shah
 *
 */
@Hidden
@RestController
@RequestMapping(value = "/v1/api")
public class GreetingController {

    /**
     * Template.
     */
     private static final String TEMPLATE = "Hello, %s!";

     /**
      *  Atomic Counter.
      */
     private final AtomicLong counter = new AtomicLong();

     /**
      * Greeting API.
      * @param name
      * @return greeting object
      */
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") final String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}