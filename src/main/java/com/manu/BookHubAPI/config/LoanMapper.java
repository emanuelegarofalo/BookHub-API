package com.manu.BookHubAPI.config;

import com.manu.BookHubAPI.dto.LoanDTO;
import com.manu.BookHubAPI.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(expression = "java(loan.getUser().getUsername())", target = "userName")
    @Mapping(expression = "java(loan.getBook().getTitle())", target = "bookTitle")
    LoanDTO toLoanDTO(Loan loan);
}
