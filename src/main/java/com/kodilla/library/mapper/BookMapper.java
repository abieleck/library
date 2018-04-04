package com.kodilla.library.mapper;

import com.kodilla.library.model.Book;
import com.kodilla.library.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    @Autowired
    private TitleMapper titleMapper;

    public BookDto mapToBookDto(Book book) {
        if (book == null) {
            return null;
        }
        return new BookDto(book.getId(), titleMapper.mapToTitleDto(book.getTitle()), book.getStatus());
    }

    public Book mapToBook(BookDto bookDto) {
        if (bookDto == null)
            return null;
        return new Book(bookDto.getId(), titleMapper.mapToTitle(bookDto.getTitleDto()), bookDto.getStatus());
    }

}
