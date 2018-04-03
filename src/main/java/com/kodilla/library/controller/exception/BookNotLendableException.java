package com.kodilla.library.controller.exception;

import com.kodilla.library.domain.BookStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotLendableException extends Exception {
    public BookNotLendableException(long bookId, BookStatus status) {
        super("Book with ID=" + bookId + " is in status " + status + ", which does not permit lending.");
    }
}
