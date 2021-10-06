package com.example.demo.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.data.UserDetailResponseDTO;
import com.example.demo.domain.data.UserListResponseDTO;
import com.example.demo.domain.data.UserUpdateRequestDTO;
import com.example.demo.port.api.UserServicePort;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServicePort userServicePort;

	@GetMapping
	public ResponseEntity<Iterable<UserListResponseDTO>> getAllUsers(
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "20") Integer size,
			@RequestParam(required = false, defaultValue = "id,asc") String sort) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Total-Elements", userServicePort.countAllUsers().toString());

		return new ResponseEntity<>(userServicePort.getUsers(page, size, sort), responseHeaders, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public UserDetailResponseDTO getUser(@PathVariable UUID id) {
		return userServicePort.getUser(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDetailResponseDTO> putUser(@PathVariable UUID id,
			@Valid @RequestBody UserUpdateRequestDTO userUpdateRequest) {

		UserDetailResponseDTO udr = userServicePort.putUser(id, userUpdateRequest);
		return new ResponseEntity<>(udr, HttpStatus.OK);
	}
	
}
