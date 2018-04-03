package com.kodilla.library.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TitleDto {
    private Long id;

    private String title;

    @JsonProperty("author")
    private AuthorDto authorDto;

    private Integer issueYear;

}
