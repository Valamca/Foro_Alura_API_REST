package com.alura.foro.topic;

import java.time.LocalDateTime;

import com.alura.foro.course.DataResponseCourse;

public record ListTopics(String title, String message,LocalDateTime date, Status status, String autor, DataResponseCourse course) {

	public ListTopics(Topic topic) {
		this(topic.getTitle(), topic.getMessage(),topic.getDateCreation(), topic.getStatus(), topic.getAutor(), 
				new DataResponseCourse(topic.getCourse().getId(),
				topic.getCourse().getNombre(), topic.getCourse().getCategoria()));
	}
	
} 
