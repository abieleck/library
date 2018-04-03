package com.kodilla.library.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookAlreadyBorrowedException extends Exception {
    public BookAlreadyBorrowedException(long bookId) {
        super("Book with ID=" + bookId + " is already borrowed");
    }
}
