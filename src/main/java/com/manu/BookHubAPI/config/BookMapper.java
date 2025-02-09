package com.manu.BookHubAPI.config;

import com.manu.BookHubAPI.dto.BookDTO;
import com.manu.BookHubAPI.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {LoanMapper.class})
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(expression = "java(book.getWriters().stream().map(w -> w.getName()).collect(java.util.stream.Collectors.toSet()))", target = "writersNames")
    BookDTO toBookDTO(Book book);
}
