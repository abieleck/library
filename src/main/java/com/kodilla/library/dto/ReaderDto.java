package com.kodilla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReaderDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date registrationDate;

}
