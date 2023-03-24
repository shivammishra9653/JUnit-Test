/*
 * Copyright (c) 2022 Phenom People, Inc.
 * All rights reserved.
 */
package com.example.demo.exception;


/**
 * This is an exception class wrapping all internal class.
 *
 * @author rakesh.shah
 *
 */
public class BookServiceException extends Exception {


    /**
     * Constructor.
     * @param message - message
     */
    public BookServiceException(String message) {
        super(message);
    }

    /**
     * Exception.
     * @param message - error message
     * @param cause - root cause of exception.
     */
    public BookServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
