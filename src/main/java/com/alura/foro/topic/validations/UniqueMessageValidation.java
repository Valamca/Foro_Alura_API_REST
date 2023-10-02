package com.alura.foro.topic.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.foro.infra.errores.IntegrityValidation;
import com.alura.foro.topic.TopicRegistrationData;
import com.alura.foro.topic.TopicRepository;

@Component
public class UniqueMessageValidation implements TopicValidations {

	@Autowired
	private TopicRepository topicRepository;
	
	public void validation(TopicRegistrationData registrationData) {
		
		if(registrationData.message() == null) {
			throw new IntegrityValidation("El mensaje no puede ser nulo");
		}
		
		boolean message = topicRepository.existsByMessage(registrationData.message());
		
		if(message) {
			throw new IntegrityValidation("Este mensaje ya existe en nuestro foro");
		}
	}
}
