package com.example.demo.domain.service;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.demo.domain.data.UserDetailResponseDTO;
import com.example.demo.domain.data.UserListResponseDTO;
import com.example.demo.domain.data.UserUpdateRequestDTO;
import com.example.demo.infrastructure.mappers.UserMapper;
import com.example.demo.port.api.UserServicePort;
import com.example.demo.port.spi.UserPersistencePort;

public class UserServiceImplementation implements UserServicePort {

	private UserPersistencePort userPersistencePort;

	public UserServiceImplementation(UserPersistencePort userPersistencePort) {
		this.userPersistencePort = userPersistencePort;
	}

	@Override
	public Iterable<UserListResponseDTO> getUsers(Integer page, Integer size, String sort) {
		Pageable pageable = null;

		String sortTemp[] = null;
		String sortColumn = null;

		if (size == null) {
			size = userPersistencePort.countAllUsers();
		}

		sortTemp = sort.split(",");
		sortColumn = sortTemp[0];

		if (sortTemp.length > 1 && sortTemp[1].equals("desc")) {
			pageable = PageRequest.of(page, size, Sort.by(sortColumn).descending());
		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortColumn).ascending());
		}
		// 
		return UserMapper.INSTANCE.userListToUserlistResponseIterable(userPersistencePort.getUsersPage(pageable));
	}

	@Override
	public UserDetailResponseDTO getUser(UUID id) {
		return UserMapper.INSTANCE.usertOUserDetailResponseDTO(userPersistencePort.getUser(id));
	}

	@Override
	public UserDetailResponseDTO putUser(UUID id, UserUpdateRequestDTO userUpdateRequest) {
		return UserMapper.INSTANCE.usertOUserDetailResponseDTO(userPersistencePort.putUser(id, userUpdateRequest));
	}

	@Override
	public Integer countAllUsers() {
		return userPersistencePort.countAllUsers();
	}

}
