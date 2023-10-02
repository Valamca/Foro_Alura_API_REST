package com.alura.foro.answer.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.foro.answer.AnswerRepository;
import com.alura.foro.answer.AnwersUpdatingData;
import com.alura.foro.answer.CreationAnswerData;
import com.alura.foro.infra.errores.IntegrityValidation;

@Component
public class UniqueAnswerValidation implements AnswerValidations {

	@Autowired
	AnswerRepository answerRepository;

	@Override
	public void validations(CreationAnswerData creationAnswerData) {
		if(answerRepository.existsByAnswer(creationAnswerData.answer())) {
			throw new IntegrityValidation("Esta respuesta ya existe.");
		}
		
	}
	
	public void validationsU(AnwersUpdatingData answerUpdatingData) {
		if(answerRepository.existsByAnswer(answerUpdatingData.answer())) {
			throw new IntegrityValidation("Esta respuesta ya existe.");
		}
	}
	
	
}
