package com.kodilla.library.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotBorrowedException extends Exception {
    public BookNotBorrowedException(long bookId) {
        super("Book with ID=" + bookId + " is not borrowed.");
    }
}
