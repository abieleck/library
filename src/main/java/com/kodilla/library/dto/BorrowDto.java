package com.kodilla.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class BorrowDto {

    private Long id;

    @JsonProperty("book")
    private BookDto bookDto;

    @JsonProperty("reader")
    private ReaderDto readerDto;

    private Date borrowDate;

    private Date returnDate;
}
