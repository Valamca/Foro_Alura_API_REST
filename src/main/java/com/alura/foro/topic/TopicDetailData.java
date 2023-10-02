package com.alura.foro.topic;

import java.time.LocalDateTime;

public record TopicDetailData(Long id, String title, String message, LocalDateTime date_creation, String autor, Status status) {

	public TopicDetailData(Topic topic){
		this(topic.getId(), topic.getTitle(),topic.getMessage(), topic.getDateCreation(), topic.getAutor(), topic.getStatus());
	}
	
}
