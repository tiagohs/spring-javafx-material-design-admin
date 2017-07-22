package com.tiagohs.model.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.tiagohs.util.PasswordValidator;

@PasswordValidator(message = "Senha e confirmar senha devem ser iguais, tente novamente.")
public class UsuarioDTO {
	
	private long id;
	
	@Email(message = "Email inválido, tente novamente")
	@NotEmpty(message = "Campo Obrigatório")
	private String email;
	
	@Length(min = 5, message = "Sua senha tem que ter pelo menos 5 caracteres")
	@NotEmpty(message = "Campo Obrigatório")
	private String password;
	
	@NotEmpty(message = "Campo Obrigatório")
	private String confirmPassword;
	
	private String role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
}
