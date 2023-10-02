package com.alura.foro.user.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.foro.infra.errores.IntegrityValidation;
import com.alura.foro.user.CreationUserData;
import com.alura.foro.user.UpdateUserData;
import com.alura.foro.user.UserReposity;

@Component
public class UniqueUsernameValidation implements UserValidations{

	@Autowired
	private UserReposity userReposity;
	
	public void validation(CreationUserData creationUserData) {
		
		boolean user = userReposity.existsByUsername(creationUserData.username());
		
		if(user) {
			throw new IntegrityValidation("El usuario elegido ya existe, intenta uno nuevo.");
		}
	}

	@Override
	public void validationU(UpdateUserData updateUserData) {

		boolean user = userReposity.existsByUsername(updateUserData.username());
		
		if(user) {
			throw new IntegrityValidation("El usuario elegido ya existe, intenta uno nuevo.");
		}
	}
}
