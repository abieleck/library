package com.kodilla.library.controller;

import com.kodilla.library.controller.exception.ReaderNotFoundException;
import com.kodilla.library.dto.BorrowDto;
import com.kodilla.library.model.Reader;
import com.kodilla.library.dto.ReaderDto;
import com.kodilla.library.mapper.BorrowMapper;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.service.BorrowService;
import com.kodilla.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/reader")
public class ReaderController {

    @Autowired
    ReaderMapper readerMapper;

    @Autowired
    ReaderService readerService;

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ReaderDto createNew(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        Reader createdReader = readerService.save(reader);
        return readerMapper.mapToReaderDto(createdReader);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ReaderDto getById(@PathVariable long id) throws ReaderNotFoundException {
        return readerMapper.mapToReaderDto(readerService.getById(id));
    }

}
