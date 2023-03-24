/*
 * Copyright (c) 2022 Phenom People, Inc.
 * All rights reserved.
 */
package com.example.demo.exception;

/**
 * When book is not available in the repository, this exception happen.
 *
 * @author rakesh.shah
 *
 */
public class BookNotFoundException extends Exception {

    /**
     * Exception.
     * @param message
     */
    public BookNotFoundException(String message) {
        super(message);
    }

}
