package com.kodilla.library.dto;

import com.kodilla.library.model.BookStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookStatusDto {
    private BookStatus status;
}
