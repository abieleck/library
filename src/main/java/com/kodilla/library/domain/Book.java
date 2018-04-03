package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "Book.getCountAvailableByTitle",
                query = "SELECT COUNT(*) FROM Book as book " +
                        "WHERE book.title.id = :TITLE_ID " +
                        "AND book.status = 'IN_CIRCULATION' " +
                        "AND NOT EXISTS " +
                        "(FROM Borrow as borrow " +
                        "WHERE borrow.book = book " +
                        "AND borrow.returnDate is null)"
        ),
        @NamedQuery(
                name = "Book.getAvailableByTitle",
                query = "FROM Book as book " +
                        "WHERE book.title.id = :TITLE_ID " +
                        "AND book.status = 'IN_CIRCULATION' " +
                        "AND NOT EXISTS " +
                        "(FROM Borrow as borrow " +
                        "WHERE borrow.book = book " +
                        "AND borrow.returnDate is null)"
        ),
        @NamedQuery(
                name = "Book.getFirstAvailableByTitle",
                query = "FROM Book WHERE id = " +
                        "(SELECT MIN(book.id) FROM Book AS book " +
                        "WHERE book.title.id = :TITLE_ID " +
                        "AND book.status = 'IN_CIRCULATION' " +
                        "AND NOT EXISTS " +
                        "(FROM Borrow as borrow " +
                        "WHERE borrow.book = book " +
                        "AND borrow.returnDate is null))"
        )

})
@Entity
@Table(name = "BOOKS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "TITLE_ID"), nullable = false)
    private Title title;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public Book(Title title, BookStatus status) {
        this.title = title;
        this.status = status;
    }
}
