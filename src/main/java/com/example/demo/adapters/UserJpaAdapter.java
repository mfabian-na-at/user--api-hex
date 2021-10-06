package com.example.demo.adapters;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.data.UserUpdateRequestDTO;
import com.example.demo.exceptions.EmailAlredyExist;
import com.example.demo.exceptions.InvalidRole;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.infrastructure.entity.User;
import com.example.demo.infrastructure.repository.UserRepository;
import com.example.demo.port.spi.UserPersistencePort;

@Service
public class UserJpaAdapter implements UserPersistencePort {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUser(UUID id) {
		if (userRepository.existsById(id)) {
			Optional<User> optionalUser = userRepository.findById(id);
			User userResponse = new User();
			
			userResponse.setId(optionalUser.get().getId());
			userResponse.setCreationDate(optionalUser.get().getCreationDate());
			userResponse.setModificationDate(optionalUser.get().getModificationDate());
			userResponse.setEnabled(optionalUser.get().isEnabled());
			userResponse.setEmail(optionalUser.get().getEmail());
			userResponse.setName(optionalUser.get().getName());
			userResponse.setLastName(optionalUser.get().getLastName());
			userResponse.setVacationStart(optionalUser.get().getVacationStart());
			userResponse.setVacationEnding(optionalUser.get().getVacationEnding());
			userResponse.setRole(optionalUser.get().getRole());
			
			return userResponse;
		} else {
			throw new UserNotFound("Este usuario con id [" + id + "] no existe");
		}
	}

	@Override
	public User putUser(UUID id, UserUpdateRequestDTO userUpdateRequest) {
		Optional<User> userTemp = userRepository.findById(id);
		
		LocalDateTime timestamp = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);

		if (userRepository.existsById(id)) {

			User udr = null;
			
			userTemp.get().setModificationDate(timestamp);

			if (!userTemp.get().getEmail().equals(userUpdateRequest.getEmail())
					&& userUpdateRequest.getEmail() != null) {
				if (userRepository.findByEmailIgnoreCase(userUpdateRequest.getEmail()) != null) {
					throw new EmailAlredyExist(
							"Ya existe el email [" + userUpdateRequest.getEmail() + "] en los registros");
				}
				userTemp.get().setEmail(userUpdateRequest.getEmail());
			}

			if (!userTemp.get().getName().equals(userUpdateRequest.getName()) && userUpdateRequest.getName() != null) {
				userTemp.get().setName(userUpdateRequest.getName());
			}

			if (!userTemp.get().getLastName().equals(userUpdateRequest.getLastName())
					&& userUpdateRequest.getLastName() != null) {
				userTemp.get().setLastName(userUpdateRequest.getLastName());
			}

			if (!userTemp.get().getVacationStart().equals(userUpdateRequest.getVacationStart())
					&& userUpdateRequest.getVacationStart() != null) {
				userTemp.get().setVacationStart(userUpdateRequest.getVacationStart());
			}

			if (!userTemp.get().getVacationEnding().equals(userUpdateRequest.getVacationEnding())
					&& userUpdateRequest.getVacationEnding() != null) {
				userTemp.get().setVacationEnding(userUpdateRequest.getVacationEnding());
			}

			if (!userTemp.get().getRole().equals(userUpdateRequest.getRole()) && userUpdateRequest.getRole() != null) {
				if (!(userUpdateRequest.getRole().equals("DEVELOPER")
						|| userUpdateRequest.getRole().equals("ADMINISTRATOR"))) {
					throw new InvalidRole("Sólo puede tener el rol DEVELOPER o " + "ADMINISTRATOR, no puede ser ["
							+ userUpdateRequest.getRole() + "]");
				}
				userTemp.get().setRole(userUpdateRequest.getRole());
			}

			udr = new User();
			udr.setId(userTemp.get().getId());
			udr.setCreationDate(userTemp.get().getCreationDate());
			udr.setModificationDate(timestamp);
			udr.setEnabled(userTemp.get().isEnabled());
			udr.setEmail(userTemp.get().getEmail());
			udr.setName(userTemp.get().getName());
			udr.setLastName(userTemp.get().getLastName());
			udr.setVacationStart(userTemp.get().getVacationStart());
			udr.setVacationEnding(userTemp.get().getVacationEnding());
			udr.setRole(userTemp.get().getRole());

			userRepository.save(userTemp.get());

			return udr;
		} else {
			throw new UserNotFound("Este usuario con id [" + id + "] no existe");
		}
	}

	@Override
	public Integer countAllUsers() {
		return (int) userRepository.count();
	}

	@Override
	public ArrayList<User> getUsersPage(Pageable page) {
		ArrayList<User> users = new ArrayList<>();
		for (User u : userRepository.findAll(page)) {
			users.add(u);
		}
		return users;
	}

}
