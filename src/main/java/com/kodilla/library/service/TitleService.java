package com.kodilla.library.service;

import com.kodilla.library.controller.exception.AuthorNotFoundException;
import com.kodilla.library.controller.exception.TitleNotFoundException;
import com.kodilla.library.model.Author;
import com.kodilla.library.model.Title;
import com.kodilla.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleService {
    @Autowired
    TitleRepository titleRepository;

    @Autowired
    AuthorService authorService;

    public Title save(final Title title) throws AuthorNotFoundException {
        Author author = authorService.getById(title.getAuthor().getId());
        return titleRepository.save(new Title(title.getTitle(), author, title.getIssueYear()));
    }

    public Title getById(long id) throws TitleNotFoundException {
        return titleRepository.findById(id).orElseThrow(() -> new TitleNotFoundException(id));
    }

    public List<Title> getByAuthorId(long authorId) throws AuthorNotFoundException {
        authorService.getById(authorId);
        return titleRepository.getByAuthor(authorId);
    }
}
