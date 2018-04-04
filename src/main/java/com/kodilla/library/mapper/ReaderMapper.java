package com.kodilla.library.mapper;

import com.kodilla.library.model.Reader;
import com.kodilla.library.dto.ReaderDto;
import org.springframework.stereotype.Component;

@Component
public class ReaderMapper {
    public ReaderDto mapToReaderDto(Reader reader) {
        if (reader == null) {
            return null;
        }
        return new ReaderDto(reader.getId(), reader.getFirstName(), reader.getLastName(), reader.getRegistrationDate());
    }

    public Reader mapToReader(ReaderDto readerDto) {
        if (readerDto == null) {
            return null;
        }
        return new Reader(readerDto.getId(), readerDto.getFirstName(), readerDto.getLastName(),
                readerDto.getRegistrationDate());
    }
}
