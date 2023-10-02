package com.alura.foro.topic.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.foro.infra.errores.IntegrityValidation;
import com.alura.foro.topic.TopicRegistrationData;
import com.alura.foro.topic.TopicRepository;

@Component
public class UniqueTitleValidation implements TopicValidations{
	
	@Autowired
	TopicRepository topicRepository;
	
	public void validation(TopicRegistrationData registrationData) {
		
		if(registrationData.title() == null) {
			throw new IntegrityValidation("El título no puede ser nulo");
		}
		
		boolean title = topicRepository.existsByTitle(registrationData.title());
		
		if(title) {
			throw new IntegrityValidation("Este título ya existe en el foro, prueba con otro");
		}
	}
	
}
