/*
 * Copyright (c) 2022 Phenom People, Inc.
 * All rights reserved.
 */
package com.example.demo.model;

public class Greeting {
    /** Id. */
    private final long id;

    /** Greeting Content. */
    private final String content;

    /**
     * Constructor for greeting object.
     * @param id - id
     * @param content - content to greet
     */
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
    /**
     * returns the id.
     * @return - id
     */
    public long getId() {
        return id;
    }

    /**
     * returns the content for a greeting.
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * To String implementation.
     */
    @Override
    public String toString() {
        return content;
    }
}