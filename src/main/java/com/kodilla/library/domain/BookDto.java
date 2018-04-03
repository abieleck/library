package com.kodilla.library.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;

    @JsonProperty("title")
    private TitleDto titleDto;

    private BookStatus status;
}
