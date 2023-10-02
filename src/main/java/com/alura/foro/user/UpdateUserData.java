package com.alura.foro.user;

import jakarta.validation.constraints.Email;

public record UpdateUserData(
		String username,
		@Email
		String email) {

}
