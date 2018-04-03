package com.kodilla.library.controller;

import com.kodilla.library.controller.exception.AuthorNotFoundException;
import com.kodilla.library.controller.exception.BookNotAvailableForTitle;
import com.kodilla.library.controller.exception.TitleNotFoundException;
import com.kodilla.library.domain.BookDto;
import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.TitleDto;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.service.BookService;
import com.kodilla.library.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping("/title")
public class TitleController {

    @Autowired
    TitleService titleService;

    @Autowired
    BookService bookService;

    @Autowired
    TitleMapper titleMapper;

    @Autowired
    BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public TitleDto createNew(@RequestBody TitleDto titleDto) throws AuthorNotFoundException {
        Title title = titleMapper.mapToTitle(titleDto);
        Title createdTitle = titleService.save(title);
        return titleMapper.mapToTitleDto(createdTitle);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public TitleDto getById(@PathVariable long id) throws TitleNotFoundException {
        return titleMapper.mapToTitleDto(titleService.getById(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/books/available/count")
    public long getAvailableBooksCount(@PathVariable long id) throws TitleNotFoundException {
        return bookService.getAvailableCountByTitle(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/books/available")
    public List<BookDto> getAvailableBooks(@PathVariable long id) throws TitleNotFoundException {
        return bookService.getAvailableByTitle(id).stream()
                .map(bookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/books/available/first")
    public BookDto getFirstAvailableBook(@PathVariable long id)
            throws BookNotAvailableForTitle, TitleNotFoundException {
        return bookMapper.mapToBookDto(bookService.getFirstAvailableByTitle(id));
    }
}
