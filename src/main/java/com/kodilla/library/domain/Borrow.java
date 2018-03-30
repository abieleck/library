package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "BORROWS")
@NoArgsConstructor
@Getter
public class Borrow {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @Column(name = "BORROW_DATE", nullable = false)
    private Date borrowDate;

    @Column(name = "RETURN_DATE")
    private Date returnDate;

    public Borrow(Book book, Reader reader, Date borrowDate) {
        this.book = book;
        this.reader = reader;
        this.borrowDate = borrowDate;
    }
}
