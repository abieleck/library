package com.kodilla.library.repository;

import com.kodilla.library.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query
    long getCountAvailableByTitle(@Param("TITLE_ID") long titleId);

    @Query
    List<Book> getAvailableByTitle(@Param("TITLE_ID") long titleId);

    @Query
    Optional<Book> getFirstAvailableByTitle(@Param("TITLE_ID") long titleId);

}
