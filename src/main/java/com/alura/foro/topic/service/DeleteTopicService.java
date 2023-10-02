package com.alura.foro.topic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.foro.topic.Topic;
import com.alura.foro.topic.TopicRepository;

@Service
public class DeleteTopicService {

	@Autowired
	private TopicRepository topicRepository;

	public void disableTopic(Long id) {
		Topic topic = topicRepository.getReferenceById(id);
		topic.disableTopic();
		
	}
	
	
	
}
