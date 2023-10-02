package com.alura.foro.topic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alura.foro.topic.ListTopics;
import com.alura.foro.topic.Topic;
import com.alura.foro.topic.TopicRepository;


@Service
public class GetTopicService {

	@Autowired
	private TopicRepository topicRepository;

	public Page<ListTopics> listTopics(Pageable pages) {
		return topicRepository.findByActivoTrue(pages).map(ListTopics::new);
	}

	public Topic listTopic(Long id) {
		return topicRepository.getById(id);
	}
	
}

//titulo, mensaje, fecha de creación, estatus autor y curso