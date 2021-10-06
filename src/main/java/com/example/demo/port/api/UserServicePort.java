package com.example.demo.port.api;

import java.util.UUID;

import com.example.demo.domain.data.UserDetailResponseDTO;
import com.example.demo.domain.data.UserListResponseDTO;
import com.example.demo.domain.data.UserUpdateRequestDTO;


public interface UserServicePort {
	
	public Iterable<UserListResponseDTO> getUsers(Integer page, Integer size, String sort);
	
	public UserDetailResponseDTO getUser(UUID id);
	
	public UserDetailResponseDTO putUser(UUID id, UserUpdateRequestDTO userUpdateRequest);
	
	public Integer countAllUsers();
}
