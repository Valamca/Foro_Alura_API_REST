package com.alura.foro.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreationUserData(
		@NotBlank(message = "El usuario es obligatorio")
		String username, 
		@NotNull(message = "El corre electrónico es obligatio")
		@Email
		String email) {
}
