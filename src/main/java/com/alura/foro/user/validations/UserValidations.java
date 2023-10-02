package com.alura.foro.user.validations;

import com.alura.foro.user.CreationUserData;
import com.alura.foro.user.UpdateUserData;

public interface UserValidations {

	public void validation(CreationUserData creationUserData);
	
	public void validationU(UpdateUserData updateUserData);
}
