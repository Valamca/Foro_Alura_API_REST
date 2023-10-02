package com.alura.foro.topic.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.foro.infra.errores.IntegrityValidation;
import com.alura.foro.topic.TopicRepository;
import com.alura.foro.topic.UpdateTopicData;

@Component
public class ServicesUniqueMessageValidation implements PutValidations {

	@Autowired
	private TopicRepository topicRepository;
	
	@Override
	public void validation(UpdateTopicData updateTopicDate) {	
		
		boolean message = topicRepository.existsByMessage(updateTopicDate.message());
		
		if(message) {
			throw new IntegrityValidation("Este mensaje ya existe en nuestro foro");
		}
	}
}
