package com.kodilla.library.repository;

import com.kodilla.library.model.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {
}
