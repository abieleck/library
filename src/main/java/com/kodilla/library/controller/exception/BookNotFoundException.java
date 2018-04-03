package com.kodilla.library.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends Exception {
    public BookNotFoundException(long bookId) {
        super("Book with ID=" + bookId + " was not found in database.");
    }
}
