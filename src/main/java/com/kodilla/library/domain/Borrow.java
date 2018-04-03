package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@NamedQueries({
        @NamedQuery(
                name = "Borrow.getActiveByBook",
                query = "FROM Borrow WHERE book.id = :BOOK_ID AND returnDate is null"
        ),
        @NamedQuery(
                name = "Borrow.getByReader",
                query = "FROM Borrow WHERE reader.id = :READER_ID"
        ),
        @NamedQuery(
                name = "Borrow.getActiveByReader",
                query = "FROM Borrow WHERE reader.id = :READER_ID AND returnDate is null"
        )
})
@Entity
@Table(name = "BORROWS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Borrow {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "READER_ID", nullable = false)
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
