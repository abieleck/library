package com.kodilla.library.mapper;

import com.kodilla.library.model.Author;
import com.kodilla.library.dto.AuthorDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public Author mapToAuthor(AuthorDto authorDto) {
        if (authorDto == null) {
            return null;
        }
        return new Author(authorDto.getId(), authorDto.getName());
    }

    public AuthorDto mapToAuthorDto(Author author) {
        if (author == null) {
            return null;
        }
        return new AuthorDto(author.getId(), author.getName());
    }

}
