package com.alura.foro.answer.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.foro.answer.CreationAnswerData;
import com.alura.foro.infra.errores.IntegrityValidation;
import com.alura.foro.user.UserReposity;

@Component
public class ExistingUsernameValidation implements AnswerValidations {

	@Autowired
	private UserReposity userReposity;
	
	@Override
	public void validations(CreationAnswerData creationAnswerData) {

		if(!userReposity.existsByUsername(creationAnswerData.username())) {
			throw new IntegrityValidation("El Usuario ingresado no existe.");
		}
	}

}
