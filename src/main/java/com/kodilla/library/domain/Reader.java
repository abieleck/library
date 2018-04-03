package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "READERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Reader {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 100, nullable = false)
    private String lastName;

    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

    public Reader(String firstName, String lastName, Date registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
    }
}
