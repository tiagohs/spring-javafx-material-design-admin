package com.tiagohs.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tiagohs.model.dto.UsuarioDTO;

public class PasswordValidatorImpl implements ConstraintValidator<PasswordValidator, UsuarioDTO> {

	@Override
	public void initialize(PasswordValidator arg0) {
		
	}

	@Override
	public boolean isValid(UsuarioDTO usuario, ConstraintValidatorContext arg1) {
		
		if (usuario == null) {
			return false;
		}
		
		return usuario.getPassword().equals(usuario.getConfirmPassword());
	}

	

}
