package com.kodilla.library.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BookIncorrectInformationException extends Exception {
    public BookIncorrectInformationException() {
        super();
    }

    public BookIncorrectInformationException(String message) {
        super(message);
    }
}
