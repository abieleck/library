package com.kodilla.library.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends Exception {
    public  AuthorNotFoundException(long authorId) {
        super("Author with ID=" + authorId + " was not found in database.");
    }
}
