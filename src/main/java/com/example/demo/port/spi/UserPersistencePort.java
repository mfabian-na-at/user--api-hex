package com.example.demo.port.spi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.example.demo.domain.data.UserUpdateRequestDTO;
import com.example.demo.infrastructure.entity.User;

public interface UserPersistencePort {

	public List<User> getUsers();
	
	public ArrayList<User> getUsersPage(Pageable page);

	public User getUser(UUID id);

	public User putUser(UUID id, UserUpdateRequestDTO userUpdateRequest);
	
	public Integer countAllUsers();
}
