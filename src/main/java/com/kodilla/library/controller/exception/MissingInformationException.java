package com.kodilla.library.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingInformationException extends Exception {
    public MissingInformationException(String message) {
        super(message);
    }
}
