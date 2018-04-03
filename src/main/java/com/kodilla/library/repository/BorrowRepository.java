package com.kodilla.library.repository;

import com.kodilla.library.domain.Borrow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {

    @Query
    List<Borrow> getActiveByBook(@Param("BOOK_ID") long bookId);

    @Query
    List<Borrow> getActiveByReader(@Param("READER_ID") long readerId);

    @Query
    List<Borrow> getByReader(@Param("READER_ID") long readerId);
}
