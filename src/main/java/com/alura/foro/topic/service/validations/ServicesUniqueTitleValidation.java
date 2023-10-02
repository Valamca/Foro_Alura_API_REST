package com.alura.foro.topic.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.foro.infra.errores.IntegrityValidation;
import com.alura.foro.topic.TopicRepository;
import com.alura.foro.topic.UpdateTopicData;

@Component
public class ServicesUniqueTitleValidation implements PutValidations {

	@Autowired
	TopicRepository topicRepository;		

	@Override
	public void validation(UpdateTopicData updateTopicDate) {
		
		boolean title = topicRepository.existsByTitle(updateTopicDate.title());
		
		if(title) {
			throw new IntegrityValidation("Este título ya existe en el foro, prueba con otro");
		}
	}

	
}
