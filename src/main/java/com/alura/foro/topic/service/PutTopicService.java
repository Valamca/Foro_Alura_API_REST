package com.alura.foro.topic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.foro.infra.errores.IntegrityValidation;
import com.alura.foro.topic.Topic;
import com.alura.foro.topic.TopicDetailData;
import com.alura.foro.topic.TopicRepository;
import com.alura.foro.topic.UpdateTopicData;
import com.alura.foro.topic.service.validations.PutValidations;

@Service
public class PutTopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	List<PutValidations> validations;
	
	public TopicDetailData updateTopic(Long id, UpdateTopicData updateTopicDate) {
		
		if(!topicRepository.existsById(id)||topicRepository.findByActivo(false)) {
			throw new IntegrityValidation("El Tema que quieres actualizar no existe");
		}

		validations.forEach(p -> p.validation(updateTopicDate));
		
		Topic topic = topicRepository.getReferenceById(id);
		topic.updateTopicData(updateTopicDate);
		return new TopicDetailData(topic);
	}

}
