package com.kodilla.library.repository;

import com.kodilla.library.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
