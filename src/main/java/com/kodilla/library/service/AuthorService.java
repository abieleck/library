package com.kodilla.library.service;

import com.kodilla.library.controller.exception.AuthorNotFoundException;
import com.kodilla.library.model.Author;
import com.kodilla.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author save(final Author author) {
        return authorRepository.save(author);
    }

    public Author getById(long id) throws AuthorNotFoundException {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

}
