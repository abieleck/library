package com.kodilla.library.controller;

import com.kodilla.library.controller.exception.AuthorNotFoundException;
import com.kodilla.library.model.Author;
import com.kodilla.library.dto.AuthorDto;
import com.kodilla.library.dto.TitleDto;
import com.kodilla.library.mapper.AuthorMapper;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.service.AuthorService;
import com.kodilla.library.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/author")
public class AuthorController {
    @Autowired
    AuthorMapper authorMapper;

    @Autowired
    AuthorService authorService;

    @Autowired
    TitleService titleService;

    @Autowired
    TitleMapper titleMapper;

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public AuthorDto createNew(@RequestBody AuthorDto authorDto) {
        Author author = authorMapper.mapToAuthor(authorDto);
        Author createdAuthor = authorService.save(author);
        return authorMapper.mapToAuthorDto(createdAuthor);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/titles")
    public List<TitleDto> getTitlesByAuthor(@PathVariable long id) throws AuthorNotFoundException {
        return titleService.getByAuthorId(id).stream()
                .map(titleMapper::mapToTitleDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public AuthorDto getById(@PathVariable long id) throws AuthorNotFoundException {
        return authorMapper.mapToAuthorDto(authorService.getById(id));
    }
}
