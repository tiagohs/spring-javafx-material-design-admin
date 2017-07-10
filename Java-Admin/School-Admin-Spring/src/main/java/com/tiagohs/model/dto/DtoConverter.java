package com.tiagohs.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.tiagohs.model.Role;
import com.tiagohs.model.Usuario;

public class DtoConverter {
	
	
	public Usuario convertDtoToUsuario(UsuarioDTO usuarioDto) {
		Usuario usuario = new Usuario();
		
		usuario.setId(usuarioDto.getId());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setPassword(usuarioDto.getPassword());
		
		return usuario;
		
	}
	
	public Role convertDtoToRole(RoleDTO roleDTO) {
		Role role = new Role();
		
		role.setId(roleDTO.getId());
		role.setName(roleDTO.getName());
		role.setRole(roleDTO.getRole());
		
		return role;
	}
	
	public RoleDTO convertRoleToDto(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		
		roleDTO.setId(role.getId());
		roleDTO.setName(role.getName());
		roleDTO.setRole(role.getRole());
		
		return roleDTO;
	}
	
	public List<Role> convertListDtoToListRoles(List<RoleDTO> listRolesDTO) {
		List<Role> listRoles = new ArrayList<>();
		
		for (RoleDTO roleDTO : listRolesDTO) {
			listRoles.add(convertDtoToRole(roleDTO));
		}
		
		return listRoles;
	}
	
	public List<RoleDTO> convertListToListDtoRoles(List<Role> listRoles) {
		List<RoleDTO> listRolesDTO = new ArrayList<>();
		
		for (Role role : listRoles) {
			listRolesDTO.add(convertRoleToDto(role));
		}
		
		return listRolesDTO;
	}
}
