package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Author {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    public Author(String name) {
        this.name = name;
    }
}
