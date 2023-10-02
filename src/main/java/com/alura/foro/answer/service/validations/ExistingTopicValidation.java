package com.alura.foro.answer.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.foro.answer.CreationAnswerData;
import com.alura.foro.infra.errores.IntegrityValidation;
import com.alura.foro.topic.TopicRepository;

@Component
public class ExistingTopicValidation implements AnswerValidations {

	@Autowired
	private TopicRepository topicRepository;
	
	
	public void validations(CreationAnswerData creationAnswerData) {
		if(!topicRepository.existsById(creationAnswerData.topicId())) {
			throw new IntegrityValidation("El Tema seleccionado no existe.");
		}
	}
	
}
