package com.example.demo.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.infrastructure.entity.User;
import com.example.demo.domain.data.UserDetailResponseDTO;
import com.example.demo.domain.data.UserListResponseDTO;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Iterable<UserListResponseDTO> userListToUserlistResponseIterable (List<User> list);
    
    UserDetailResponseDTO usertOUserDetailResponseDTO (User user);
    
}
