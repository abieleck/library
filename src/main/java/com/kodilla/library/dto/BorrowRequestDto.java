package com.kodilla.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BorrowRequestDto {
    private Long readerId;
    private Long bookId;
}
