package com.kodilla.library.controller;

import com.kodilla.library.controller.exception.*;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookDto;
import com.kodilla.library.domain.BorrowDto;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.mapper.BorrowMapper;
import com.kodilla.library.service.BookService;
import com.kodilla.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    BookService bookService;

    @Autowired
    BorrowService borrowService;

    @Autowired
    BorrowMapper borrowMapper;

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public BookDto createNew(@RequestBody BookDto bookDto) throws TitleNotFoundException {
        Book book = bookMapper.mapToBook(bookDto);
        Book createdBook = bookService.save(book);
        return bookMapper.mapToBookDto(createdBook);
    }

    @RequestMapping(method = RequestMethod.PATCH, consumes = APPLICATION_JSON_VALUE, value = "/status")
    public BookDto changeStatus(@RequestBody BookDto bookDto)
            throws BookIncorrectInformationException, BookNotFoundException {
        if (bookDto.getId() == null) {
            throw new BookIncorrectInformationException("Book ID not provided.");
        } else if (bookDto.getStatus() == null) {
            throw new BookIncorrectInformationException("Updated book status not provided.");
        } else {
            return bookMapper.mapToBookDto(bookService.updateStatus(bookDto.getId(), bookDto.getStatus()));
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/borrow-by/{readerId}")
    public BorrowDto borrow(@PathVariable long id, @PathVariable long readerId) throws
            BookNotLendableException,
            BookAlreadyBorrowedException,
            ReaderNotFoundException,
            BookNotFoundException {
        return borrowMapper.mapToBorrowDto(borrowService.borrowBookByReader(id, readerId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/return")
    public BorrowDto returnBook(@PathVariable long id) throws BookNotFoundException, BookNotBorrowedException {
        return borrowMapper.mapToBorrowDto(borrowService.returnBook(id));
    }


}
