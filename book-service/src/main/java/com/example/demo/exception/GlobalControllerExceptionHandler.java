/*
 * Copyright (c) 2022 Phenom People, Inc.
 * All rights reserved.
 */
package com.example.demo.exception;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global Exception Controller Advice.
 * @author rakesh.shah
 *
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * for a given exception it conveys the correct details as a part of the response.
     * @param ex - exception class
     * @return -appropriate response
     */
    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConnversion(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * for a given exception it conveys the correct details as a part of the response.
     * @param ex - exception class
     * @return -appropriate response
     */
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleBookNotFound(BookNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    /**
     * for a given exception it conveys the correct details as a part of the response.
     * @param ex - exception class
     * @return -appropriate response
     */
    @ExceptionHandler(BookServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleBookNotUpdated(BookServiceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }
}