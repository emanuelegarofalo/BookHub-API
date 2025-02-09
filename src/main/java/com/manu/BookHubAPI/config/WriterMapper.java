package com.manu.BookHubAPI.config;

import com.manu.BookHubAPI.dto.WriterDTO;
import com.manu.BookHubAPI.model.Writer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WriterMapper {
    WriterMapper INSTANCE = Mappers.getMapper(WriterMapper.class);

    @Mapping(expression = "java(writer.getBooks().stream().map(b -> b.getTitle()).collect(java.util.stream.Collectors.toSet()))", target = "books")
    WriterDTO toWriterDTO(Writer writer);
}
