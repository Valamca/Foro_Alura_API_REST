package com.alura.foro.account;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationAccountData(
		@NotBlank(message = "El usuario es obligatorio")
		String username,
		@NotBlank(message = "La contraseña es obligatoria")
		String password) {

}
