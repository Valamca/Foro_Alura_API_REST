package com.alura.foro.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreationAnswerData(
		@NotNull(message = "El id del tema es obligatorio")
		Long topicId, 
		@NotBlank(message = "La respuesta es obligatoria")
		String answer, 
		@NotBlank(message = "El usuario es obligatorio")
		String username) {
}
