package com.kodilla.library.mapper;

import com.kodilla.library.domain.Borrow;
import com.kodilla.library.domain.BorrowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BorrowMapper {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    ReaderMapper readerMapper;

    public Borrow mapToBorrow(BorrowDto borrowDto) {
        if (borrowDto == null) {
            return null;
        }
        return new Borrow(
                borrowDto.getId(),
                bookMapper.mapToBook(borrowDto.getBookDto()),
                readerMapper.mapToReader(borrowDto.getReaderDto()),
                borrowDto.getBorrowDate(),
                borrowDto.getReturnDate());
    }

    public BorrowDto mapToBorrowDto(Borrow borrow) {
        if (borrow == null) {
            return null;
        }
        return new BorrowDto(
                borrow.getId(),
                bookMapper.mapToBookDto(borrow.getBook()),
                readerMapper.mapToReaderDto(borrow.getReader()),
                borrow.getBorrowDate(),
                borrow.getReturnDate());
    }
}
