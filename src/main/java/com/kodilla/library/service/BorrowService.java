package com.kodilla.library.service;

import com.kodilla.library.controller.exception.*;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.domain.Borrow;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReaderService readerService;

    public Borrow borrowBookByReader(long bookId, long readerId) throws
            BookNotFoundException,
            BookAlreadyBorrowedException,
            BookNotLendableException,
            ReaderNotFoundException {
        Book book = bookService.getById(bookId);
        if(book.getStatus() != BookStatus.IN_CIRCULATION) {
            throw new BookNotLendableException(bookId, book.getStatus());
        }
        Reader reader = readerService.getById(readerId);
        if (borrowRepository.getActiveByBook(bookId).size() > 0) {
            throw new BookAlreadyBorrowedException(bookId);
        }
        Date borrowDate = Date.valueOf(LocalDate.now());
        Borrow borrow = new Borrow(book, reader, borrowDate);
        return borrowRepository.save(borrow);
    }

    public Borrow returnBook(long bookId) throws BookNotFoundException, BookNotBorrowedException {
        bookService.getById(bookId);
        return borrowRepository.getActiveByBook(bookId).stream()
                .peek(borrow -> borrowRepository.save(new Borrow(
                        borrow.getId(),
                        borrow.getBook(),
                        borrow.getReader(),
                        borrow.getBorrowDate(),
                        Date.valueOf(LocalDate.now())
                )))
                .findFirst().orElseThrow(() -> new BookNotBorrowedException(bookId));
    }

    public List<Borrow> getActiveByReader(long readerId) throws ReaderNotFoundException {
        readerService.getById(readerId);
        return borrowRepository.getActiveByReader(readerId);
    }

    public List<Borrow> getByReader(long readerId) throws ReaderNotFoundException {
        readerService.getById(readerId);
        return borrowRepository.getByReader(readerId);
    }
}
