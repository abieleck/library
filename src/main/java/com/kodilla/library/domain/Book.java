package com.kodilla.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
@NoArgsConstructor
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long Id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "TITLE_ID"))
    private Title title;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public Book(Title title, BookStatus status) {
        this.title = title;
        this.status = status;
    }
}
