package com.kodilla.library.mapper;

import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.TitleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TitleMapper {

    @Autowired
    private AuthorMapper authorMapper;

    public Title mapToTitle(TitleDto titleDto) {
        if (titleDto == null) {
            return null;
        }
        return new Title(titleDto.getId(), titleDto.getTitle(), authorMapper.mapToAuthor(titleDto.getAuthorDto()),
                titleDto.getIssueYear());
    }

    public TitleDto mapToTitleDto(Title title) {
        if (title == null) {
            return null;
        }
        return new TitleDto(title.getId(), title.getTitle(), authorMapper.mapToAuthorDto(title.getAuthor()),
                title.getIssueYear());
    }
}
