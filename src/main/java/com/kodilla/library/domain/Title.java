package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NamedQuery(
        name = "Title.getByAuthor",
        query = "FROM Title WHERE author.id = :AUTHOR_ID"
)
@Entity
@Table(name = "TITLES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Title {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", length = 300, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @Column(name="ISSUE_YEAR")
    private Integer issueYear;

    public Title(String title, Author author, int issueYear) {
        this.title = title;
        this.author = author;
        this.issueYear = issueYear;
    }
}
