package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.adapters.UserJpaAdapter;
import com.example.demo.domain.service.UserServiceImplementation;
import com.example.demo.port.api.UserServicePort;
import com.example.demo.port.spi.UserPersistencePort;

@Configuration
public class UserConfiguration {

	@Bean
	public UserPersistencePort userPersistence() {
		return new UserJpaAdapter();
	}

	@Bean
	public UserServicePort userService() {
		return new UserServiceImplementation(userPersistence());
	}
}
