package com.kodilla.library.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReaderNotFoundException  extends Exception {
    public ReaderNotFoundException(long readerId) {
        super("Reader with ID=" + readerId + " was not found in database.");
    }
}
