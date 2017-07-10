package com.tiagohs.service;

import com.tiagohs.model.Usuario;

public interface UsuarioService extends IBaseService<Usuario> {
	
	public Usuario findUserByEmail(String email);
	public void saveUser(Usuario usuario);
}
