package com.example.demo.domain.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserListResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private UUID id;
	private LocalDateTime creationDate;
	private boolean enabled;
	private String email;
	private String name;
	private String lastName;
	private String role;
		
}
