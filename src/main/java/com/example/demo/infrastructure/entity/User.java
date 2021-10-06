package com.example.demo.infrastructure.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private UUID id;

	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "role")
	private String role;

	@Column(name = "vacation_start")
	private LocalDateTime vacationStart;

	@Column(name = "vacation_ending")
	private LocalDateTime vacationEnding;

	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	@Column(name = "modification_date")
	private LocalDateTime modificationDate;


}
