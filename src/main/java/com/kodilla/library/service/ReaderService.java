package com.kodilla.library.service;

import com.kodilla.library.controller.exception.ReaderNotFoundException;
import com.kodilla.library.model.Reader;
import com.kodilla.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class ReaderService {
    @Autowired
    ReaderRepository readerRepository;

    public Reader save(final Reader reader) {
        Reader readerToCreate = new Reader(reader.getFirstName(), reader.getLastName(), Date.valueOf(LocalDate.now()));
        return readerRepository.save(readerToCreate);
    }

    public Reader getById(long readerId) throws ReaderNotFoundException {
        return readerRepository.findById(readerId).orElseThrow(() -> new ReaderNotFoundException(readerId));
    }
}
