package com.alura.foro.topic;

import com.alura.foro.course.CourseRegistrationData;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRegistrationData(
		@NotBlank(message = "El título es obligatorio")
		String title,
		
		@NotBlank(message = "El mensaje es obligatorio")
		String message,
		
		@NotBlank(message = "El autor es obligatorio")
		String autor,
		
		@NotNull(message = "Los datos del curso son obligatorios")
		@Valid
		CourseRegistrationData course) {

}
