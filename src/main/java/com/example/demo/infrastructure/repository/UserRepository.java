package com.example.demo.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.infrastructure.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository <User,UUID> {
	User findByEmailIgnoreCase(String email);
}
