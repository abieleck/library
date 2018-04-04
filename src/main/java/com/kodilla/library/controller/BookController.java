package com.kodilla.library.controller;

import com.kodilla.library.controller.exception.*;
import com.kodilla.library.dto.BookStatusDto;
import com.kodilla.library.model.Book;
import com.kodilla.library.dto.BookDto;
import com.kodilla.library.dto.BorrowDto;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.mapper.BorrowMapper;
import com.kodilla.library.service.BookService;
import com.kodilla.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/book")
public class BookController {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public BookDto createNew(@RequestBody BookDto bookDto) throws TitleNotFoundException {
        Book book = bookMapper.mapToBook(bookDto);
        Book createdBook = bookService.save(book);
        return bookMapper.mapToBookDto(createdBook);
    }

    @RequestMapping(method = RequestMethod.PATCH, consumes = APPLICATION_JSON_VALUE, value = "/{id}/status")
    public BookDto changeStatus(@PathVariable long id, @RequestBody BookStatusDto bookStatusDto)
            throws BookNotFoundException, MissingInformationException {
        if (bookStatusDto.getStatus() == null) {
            throw new MissingInformationException("Updated book status missing from the request.");
        } else {
            return bookMapper.mapToBookDto(bookService.updateStatus(id, bookStatusDto.getStatus()));
        }
    }

}
