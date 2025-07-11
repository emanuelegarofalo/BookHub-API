package com.manu.BookHubAPI.config.mapper;

import com.manu.BookHubAPI.dto.UserDTO;
import com.manu.BookHubAPI.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {LoanMapper.class, BookMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toUserDTO(User user);
}
