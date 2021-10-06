package com.example.demo.domain.data;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequestDTO {
	
	@NotNull(message="Email no debe ser nulo")
	private String email;
	
	@NotNull(message="Name no debe ser nulo")
	private String name;
	
	@NotNull(message="Last Name no debe ser nulo")
	private String lastName;
	
	private LocalDateTime vacationStart;
	
	private LocalDateTime vacationEnding;
	
	@NotNull(message="Role no debe ser nulo, Sólo puede ser ADMINISTRADOR o DEVELOPER")
	private String role;
		
}
