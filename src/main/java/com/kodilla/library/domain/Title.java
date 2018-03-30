package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TITLES")
@NoArgsConstructor
@Getter
public class Title {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long Id;

    @Column(name = "TITLE", length = 300, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @Column(name="ISSUE_YEAR")
    private int issueYear;

    public Title(String title, Author author, int issueYear) {
        this.title = title;
        this.author = author;
        this.issueYear = issueYear;
    }
}
