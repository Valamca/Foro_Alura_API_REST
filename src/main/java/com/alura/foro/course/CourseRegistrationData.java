package com.alura.foro.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRegistrationData(
		
		@NotBlank(message = "El nombre del curso es obligatorio")
		String nombre,
		
		@NotNull(message = "La categoria es obligatoria")
		Categories categoria) {
}
