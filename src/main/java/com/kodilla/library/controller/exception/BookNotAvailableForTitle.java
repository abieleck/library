package com.kodilla.library.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotAvailableForTitle extends Exception {
    public BookNotAvailableForTitle(long titleId) {
        super("There are no books available for lending for title with ID=" + titleId + ".");
    }
}
