package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.domain.data.UserDetailResponseDTO;
import com.example.demo.domain.data.UserListResponseDTO;
import com.example.demo.domain.data.UserUpdateRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(UserController.class)
public class getUsersTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	UserController userController;

	@Test
	public void getUser_basic_success() {
		String str = "2021-09-21T16:32:33.855";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

		UserDetailResponseDTO u1 = new UserDetailResponseDTO();
		u1.setId(UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE1"));
		u1.setEmail("mary@lopez.net");
		u1.setEnabled(true);
		u1.setCreationDate(dateTime);
		u1.setLastName("Lopez");
		u1.setName("Mary");
		u1.setRole("DEVELOPER");
		Mockito.when(userController.getUser(u1.getId())).thenReturn(u1);

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/users/AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE1")
					.contentType(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(jsonPath("$.name", is("Mary")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getUser_basic_fail() {
		String str = "2021-09-21T16:32:33.855";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

		UserDetailResponseDTO u1 = new UserDetailResponseDTO();
		u1.setId(UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE1"));
		u1.setEmail("mary@lopez.net");
		u1.setEnabled(true);
		u1.setCreationDate(dateTime);
		u1.setLastName("Lopez");
		u1.setName("Mary");
		u1.setRole("DEVELOPER");
		Mockito.when(userController.getUser(u1.getId())).thenReturn(u1);

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/users/AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE1")
					.contentType(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(jsonPath("$.name", is("Dino"))); //Debe ser Mary
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllUsers_basic_success() {
		int itSize = 0;
		String str1 = "2021-09-21T16:32:33.855";
		String str2 = "2021-09-21T16:32:35.771";
		String str3 = "2021-09-21T16:32:36.907";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime dateTime1 = LocalDateTime.parse(str1, formatter);
		LocalDateTime dateTime2 = LocalDateTime.parse(str2, formatter);
		LocalDateTime dateTime3 = LocalDateTime.parse(str3, formatter);

		UserListResponseDTO u1 = new UserListResponseDTO();
		u1.setId(UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE1"));
		u1.setEmail("mary@lopez.net");
		u1.setEnabled(true);
		u1.setCreationDate(dateTime1);
		u1.setLastName("Lopez");
		u1.setName("Mary");
		u1.setRole("DEVELOPER");

		UserListResponseDTO u2 = new UserListResponseDTO();
		u2.setId(UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE2"));
		u2.setEmail("pepe@lopez.net");
		u2.setEnabled(true);
		u2.setCreationDate(dateTime2);
		u2.setLastName("Lopez");
		u2.setName("Pepe");
		u2.setRole("ADMINISTRATOR");

		UserListResponseDTO u3 = new UserListResponseDTO();
		u3.setId(UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE4"));
		u3.setEmail("dino@saurio.net");
		u3.setEnabled(true);
		u3.setCreationDate(dateTime3);
		u3.setLastName("Saurio");
		u3.setName("Dino");
		u3.setRole("DEVELOPER");

		Iterable<UserListResponseDTO> list = new ArrayList<>(Arrays.asList(u1, u2, u3));

		for (Object i : list) {
			itSize++;
			i.getClass();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Total-Elements", "" + itSize);

		ResponseEntity<Iterable<UserListResponseDTO>> re = new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);

		Mockito.when(userController.getAllUsers(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
				.thenReturn(re);

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/users/")
					.contentType(org.springframework.http.MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(3)))
					.andExpect(jsonPath("$[0].name", is("Mary")))
					.andExpect(jsonPath("$[1].name", is("Pepe")))
					.andExpect(jsonPath("$[2].name", is("Dino")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllUsers_basic_fail() {
		int itSize = 0;
		String str1 = "2021-09-21T16:32:33.855";
		String str2 = "2021-09-21T16:32:35.771";
		String str3 = "2021-09-21T16:32:36.907";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime dateTime1 = LocalDateTime.parse(str1, formatter);
		LocalDateTime dateTime2 = LocalDateTime.parse(str2, formatter);
		LocalDateTime dateTime3 = LocalDateTime.parse(str3, formatter);

		UserListResponseDTO u1 = new UserListResponseDTO();
		u1.setId(UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE1"));
		u1.setEmail("mary@lopez.net");
		u1.setEnabled(true);
		u1.setCreationDate(dateTime1);
		u1.setLastName("Lopez");
		u1.setName("Mary");
		u1.setRole("DEVELOPER");

		UserListResponseDTO u2 = new UserListResponseDTO();
		u2.setId(UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE2"));
		u2.setEmail("pepe@lopez.net");
		u2.setEnabled(true);
		u2.setCreationDate(dateTime2);
		u2.setLastName("Lopez");
		u2.setName("Pepe");
		u2.setRole("ADMINISTRATOR");

		UserListResponseDTO u3 = new UserListResponseDTO();
		u3.setId(UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE4"));
		u3.setEmail("dino@saurio.net");
		u3.setEnabled(true);
		u3.setCreationDate(dateTime3);
		u3.setLastName("Saurio");
		u3.setName("Dino");
		u3.setRole("DEVELOPER");

		Iterable<UserListResponseDTO> list = new ArrayList<>(Arrays.asList(u1, u2, u3));

		for (Object i : list) {
			itSize++;
			i.getClass();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Total-Elements", "" + itSize);

		ResponseEntity<Iterable<UserListResponseDTO>> re = new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);

		Mockito.when(userController.getAllUsers(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
				.thenReturn(re);

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/users/")
					.contentType(org.springframework.http.MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(3)))
					.andExpect(jsonPath("$[0].name", is("Pepe")))
					.andExpect(jsonPath("$[1].name", is("Mary"))) //Orden Incorrecto
					.andExpect(jsonPath("$[2].name", is("Dino")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void putUser_basic_success() throws JSONException {

		String str1 = "2021-10-10T16:32:33.147";
		String str2 = "2021-10-11T16:32:33.215";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime dateTime1 = LocalDateTime.parse(str1, formatter);
		LocalDateTime dateTime2 = LocalDateTime.parse(str2, formatter);

		UserUpdateRequestDTO user = new UserUpdateRequestDTO();
		user.setEmail("EmailNuevo");
		user.setLastName("lastNameNuevo");
		user.setName("NameNuevo");
		user.setRole("RoleNuevo");
		user.setVacationEnding(dateTime1);
		user.setVacationStart(dateTime2);
		
		
		JSONObject json = new JSONObject()
				.put("email", user.getEmail())
				.put("lastName", user.getLastName())
				.put("name", user.getName())
				.put("role", user.getRole())
				.put("vacationStart", user.getVacationStart())
				.put("vacationEnding", user.getVacationEnding());
		
		UserDetailResponseDTO u1 = new UserDetailResponseDTO();
		u1.setId(UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE4"));
		u1.setEmail(user.getEmail());
		u1.setEnabled(true);
		u1.setCreationDate(dateTime1);
		u1.setLastName(user.getLastName());
		u1.setName(user.getName());
		u1.setRole(user.getRole());
		
		ResponseEntity<UserDetailResponseDTO> re = new ResponseEntity<>(u1, HttpStatus.OK);
		
		Mockito.when(userController.putUser(Mockito.anyObject(), Mockito.any(UserUpdateRequestDTO.class)))
		.thenReturn(re);

		try {
			mockMvc.perform(MockMvcRequestBuilders.put("/users/AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE4")
					.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
					.content(json.toString().getBytes()))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.id", is("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE4".toLowerCase())))
					.andExpect(jsonPath("$.name", is("NameNuevo")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void putUser_basic_fail() throws JSONException {

		String str1 = "2021-10-10T16:32:33.147";
		String str2 = "2021-10-11T16:32:33.215";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime dateTime1 = LocalDateTime.parse(str1, formatter);
		LocalDateTime dateTime2 = LocalDateTime.parse(str2, formatter);

		UserUpdateRequestDTO user = new UserUpdateRequestDTO();
		user.setEmail("EmailNuevo");
		user.setLastName("lastNameNuevo");
		user.setName("NameNuevo");
		user.setRole("RoleNuevo");
		user.setVacationEnding(dateTime1);
		user.setVacationStart(dateTime2);
		
		
		JSONObject json = new JSONObject()
				.put("email", user.getEmail())
				.put("lastName", user.getLastName())
				.put("name", user.getName())
				.put("role", user.getRole())
				.put("vacationStart", user.getVacationStart())
				.put("vacationEnding", user.getVacationEnding());
		
		UserDetailResponseDTO u1 = new UserDetailResponseDTO();
		u1.setId(UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE5"));
		u1.setEmail(user.getEmail());
		u1.setEnabled(true);
		u1.setCreationDate(dateTime1);
		u1.setLastName(user.getLastName());
		u1.setName(user.getName());
		u1.setRole(user.getRole());
		
		ResponseEntity<UserDetailResponseDTO> re = new ResponseEntity<>(u1, HttpStatus.OK);
		
		Mockito.when(userController.putUser(Mockito.anyObject(), Mockito.any(UserUpdateRequestDTO.class)))
		.thenReturn(re);

		try {
			mockMvc.perform(MockMvcRequestBuilders.put("/users/AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE5")
					.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
					.content(json.toString().getBytes()))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.id", is("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEE4".toLowerCase()))) //id incorrecto
					.andExpect(jsonPath("$.name", is("NameNuevo")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
