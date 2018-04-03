package com.kodilla.library.controller;

import com.kodilla.library.controller.exception.ReaderNotFoundException;
import com.kodilla.library.domain.BorrowDto;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.ReaderDto;
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
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderMapper readerMapper;

    @Autowired
    ReaderService readerService;

    @Autowired
    BorrowService borrowService;

    @Autowired
    BorrowMapper borrowMapper;

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

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/borrows")
    public List<BorrowDto> getBorrows(@PathVariable long id) throws ReaderNotFoundException {
        return borrowService.getByReader(id).stream()
                .map(borrowMapper::mapToBorrowDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/borrows/active")
    public List<BorrowDto> getActiveBorrows(@PathVariable long id) throws ReaderNotFoundException {
        return borrowService.getActiveByReader(id).stream()
                .map(borrowMapper::mapToBorrowDto)
                .collect(Collectors.toList());
    }
}
