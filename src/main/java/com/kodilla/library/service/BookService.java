package com.kodilla.library.service;

import com.kodilla.library.controller.exception.BookNotAvailableForTitle;
import com.kodilla.library.controller.exception.BookNotFoundException;
import com.kodilla.library.controller.exception.TitleNotFoundException;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.domain.Title;
import com.kodilla.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    TitleService titleService;

    @Autowired
    ReaderService readerService;

    public Book getById(long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book save(final Book book) throws TitleNotFoundException {
        Title title = titleService.getById(book.getTitle().getId());
        return bookRepository.save(new Book(book.getId(), title, book.getStatus()));
    }

    public Book updateStatus(long id, BookStatus newStatus) throws BookNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return bookRepository.save(new Book(id, book.getTitle(), newStatus));
    }

    public long getAvailableCountByTitle(long titleId) throws TitleNotFoundException {
        titleService.getById(titleId);
        return bookRepository.getCountAvailableByTitle(titleId);
    }

    public List<Book> getAvailableByTitle(long titleId) throws TitleNotFoundException {
        titleService.getById(titleId);
        return bookRepository.getAvailableByTitle(titleId);
    }

    public Book getFirstAvailableByTitle(long titleId) throws TitleNotFoundException, BookNotAvailableForTitle {
        titleService.getById(titleId);
        return bookRepository.getFirstAvailableByTitle(titleId).orElseThrow(() -> new BookNotAvailableForTitle(titleId));
    }

}
