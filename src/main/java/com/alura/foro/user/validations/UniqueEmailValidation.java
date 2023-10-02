package com.alura.foro.user.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.foro.infra.errores.IntegrityValidation;
import com.alura.foro.user.CreationUserData;
import com.alura.foro.user.UpdateUserData;
import com.alura.foro.user.UserReposity;

@Component
public class UniqueEmailValidation implements UserValidations {
	
	@Autowired
	private UserReposity userReposity;

	@Override
	public void validation(CreationUserData creationUserData) {
		
		boolean email = userReposity.existsByEmail(creationUserData.email());
		
		if(email) {
			throw new IntegrityValidation("El email ingresado ya existe.");
		}
	}
	
	public void validationU(UpdateUserData updateUserData) {
		
		boolean email = userReposity.existsByEmail(updateUserData.email());
		
		if(email) {
			throw new IntegrityValidation("El email ingresado ya existe.");
		}
	}
	
	
	
	
}
