package com.kodilla.library.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TitleNotFoundException extends Exception {
    public TitleNotFoundException(long titleId) {
        super("Title with ID=" + titleId + " not found in database.");
    }
}
