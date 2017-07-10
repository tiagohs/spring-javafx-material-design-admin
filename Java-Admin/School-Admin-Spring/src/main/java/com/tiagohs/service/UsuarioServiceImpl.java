package com.tiagohs.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Role;
import com.tiagohs.model.Usuario;
import com.tiagohs.repository.UsuarioRepository;

@Service("userService")
public class UsuarioServiceImpl extends BaseService<Usuario, JpaRepository<Usuario,Long>> implements UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super(repository);
		
		this.usuarioRepository = repository;
	}
	
	@Override
	public Usuario findUserByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public void saveUser(Usuario usuario) {
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuario.setActive(1);
		
        Role userRole = roleService.findByRole("ADMIN");
        usuario.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        usuarioRepository.save(usuario);
		
	}

}
