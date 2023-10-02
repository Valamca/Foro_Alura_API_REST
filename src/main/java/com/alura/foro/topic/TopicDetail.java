package com.alura.foro.topic;

import com.alura.foro.course.DataResponseCourse;

public record TopicDetail(String title, String message, Status status,String autor, DataResponseCourse course) {

	public TopicDetail(Topic topic) {
		this(topic.getTitle(), topic.getMessage(), topic.getStatus(), topic.getAutor(),
				new DataResponseCourse(topic.getCourse().getId(), topic.getCourse().getNombre(), topic.getCourse().getCategoria()));
	}

}
