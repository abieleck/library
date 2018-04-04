package com.kodilla.library.controller;

import com.kodilla.library.controller.exception.*;
import com.kodilla.library.dto.BorrowDto;
import com.kodilla.library.dto.BorrowRequestDto;
import com.kodilla.library.dto.ReturnRequestDto;
import com.kodilla.library.mapper.BorrowMapper;
import com.kodilla.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/borrow")
public class BorrowController {

    @Autowired
    BorrowService borrowService;

    @Autowired
    BorrowMapper borrowMapper;

    @RequestMapping(method = RequestMethod.POST)
    public BorrowDto borrow(@RequestBody BorrowRequestDto borrowRequestDto) throws
            BookNotLendableException,
            BookAlreadyBorrowedException,
            ReaderNotFoundException,
            BookNotFoundException,
            MissingInformationException {
        if(borrowRequestDto.getBookId() == null) {
            throw new MissingInformationException("Book ID is missing from the request.");
        }
        if(borrowRequestDto.getReaderId() == null) {
            throw new MissingInformationException("Reader ID is missing from the request.");
        }
        return borrowMapper.mapToBorrowDto(borrowService.borrowBookByReader(borrowRequestDto.getBookId(),
                borrowRequestDto.getReaderId()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/return")
    public BorrowDto returnBook(@RequestBody ReturnRequestDto returnRequestDto)
            throws BookNotFoundException, BookNotBorrowedException, MissingInformationException {
        if(returnRequestDto.getBookId() == null) {
            throw new MissingInformationException("Book ID is missing from the request.");
        }
        return borrowMapper.mapToBorrowDto(borrowService.returnBook(returnRequestDto.getBookId()));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/by-reader/{readerId}")
    public List<BorrowDto> getBorrowsByReader(@PathVariable long readerId) throws ReaderNotFoundException {
        return borrowService.getByReader(readerId).stream()
                .map(borrowMapper::mapToBorrowDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/active/by-reader/{readerId}")
    public List<BorrowDto> getActiveBorrowsByReader(@PathVariable long readerId) throws ReaderNotFoundException {
        return borrowService.getActiveByReader(readerId).stream()
                .map(borrowMapper::mapToBorrowDto)
                .collect(Collectors.toList());
    }
}
