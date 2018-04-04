package com.kodilla.library.repository;

import com.kodilla.library.model.Title;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {

    @Query
    List<Title> getByAuthor(@Param("AUTHOR_ID") long authorId);
}
