package com.tiagohs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tiagohs.model.Usuario;
import com.tiagohs.model.dto.DtoConverter;
import com.tiagohs.model.dto.RoleDTO;
import com.tiagohs.model.dto.UsuarioDTO;
import com.tiagohs.service.RoleService;
import com.tiagohs.service.UsuarioService;

@Controller
public class UsuarioController {
	
	private static final String USUARIO_HOME_DEFAULT = "/admin/usuario/";
	private static final String USUARIO_HOME = "/admin/usuario/home";
	private static final String USUARIO_CREATE = "/admin/usuario/create";
	private static final String USUARIO_EDIT = "/admin/usuario/edit";
	private static final String USUARIO_DELETE = "/admin/usuario/delete";
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private DtoConverter dtoConverter;
	
	@RequestMapping(value={USUARIO_HOME, USUARIO_HOME_DEFAULT}, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		List<UsuarioDTO> usuarios = dtoConverter.convertListToListDtoUsuarios(usuarioService.findAll());
		
		modelAndView.addObject("usuarios", usuarios);
		modelAndView.setViewName(USUARIO_HOME);
		
		return modelAndView;
	}
	
	@RequestMapping(value = USUARIO_CREATE, method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		
		modelAndView.addObject("pageTitle", "stuffs-Admin - Adicionar novo Usuário");
		modelAndView.addObject("pageFormTitle", "Novo Usuário");
		modelAndView.addObject("pageSubTitle", "Cadastrar um novo usuário no sistema.");
		modelAndView.addObject("pageAction", "/admin/usuario/create");
		modelAndView.addObject("usuario", usuarioDTO);
		modelAndView.setViewName(USUARIO_CREATE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = USUARIO_CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute("usuario") UsuarioDTO usuarioDTO, BindingResult bindingResult) {
		List<RoleDTO> roles = dtoConverter.convertListToListDtoRoles(roleService.findAll());
		
		ModelAndView modelAndView = new ModelAndView();
		Usuario userExists = usuarioService.findUserByEmail(usuarioDTO.getEmail());
		
		if (userExists != null) {
			modelAndView.addObject("rolesList", roles);
			bindingResult .rejectValue("email", "error.user", "Usuário já criado. Tente realizer o login.");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("rolesList", roles);
			modelAndView.setViewName(USUARIO_CREATE);
		} else {
			try {
				usuarioService.saveUser(dtoConverter.dtoToEntity(usuarioDTO));
				
				modelAndView.addObject("success", "Usuário salvo com sucesso.");
				modelAndView.setViewName("redirect:" + USUARIO_HOME);
				modelAndView.setViewName(USUARIO_CREATE);
			} catch (Exception e) {
				modelAndView.setViewName("redirect:" + USUARIO_CREATE);
				modelAndView.addObject("error", true);
			}
			
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = USUARIO_EDIT, method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		UsuarioDTO usuario = null;
		
		try {
			usuario = dtoConverter.entityToDto(usuarioService.find(id));
		} catch (Exception e) {
			return new ModelAndView("redirect:" + USUARIO_HOME);
		}
		
		if (null != usuario) {
			usuario.setId(id);
			
			modelAndView.addObject("pageTitle", "stuffs-Admin - Editar Usuário " + usuario.getEmail());
			modelAndView.addObject("pageFormTitle", "Editar Usuário " + usuario.getEmail());
			modelAndView.addObject("pageSubTitle", "Editar dados cadastrais do usuário " + usuario.getEmail());
			modelAndView.addObject("pageAction", "/admin/usuario/edit");
			modelAndView.addObject("usuario", usuario);
			modelAndView.setViewName(USUARIO_CREATE);
		} else {
			return new ModelAndView("redirect:" + USUARIO_HOME);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = USUARIO_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(@Valid @ModelAttribute("usuario") UsuarioDTO usuarioDTO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:" + USUARIO_EDIT);
			modelAndView.addObject("id", usuarioDTO.getId());
			
			return modelAndView;
		}
		
		try {
			System.out.println(usuarioDTO.getId());
			usuarioService.save(dtoConverter.dtoToEntity(usuarioDTO));
			
			List<UsuarioDTO> usuarios = dtoConverter.convertListToListDtoUsuarios(usuarioService.findAll());
			modelAndView.addObject("alunos", usuarios);
			
			modelAndView.addObject("success", "Atualizações feitas com sucesso.");
			modelAndView.setViewName("redirect:" + USUARIO_HOME);
		} catch (Exception e) {
			modelAndView.setViewName("redirect:" + USUARIO_CREATE);
			modelAndView.addObject("error", true);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = USUARIO_DELETE, method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null != id) {
			
			try {
				usuarioService.delete(Long.parseLong(id));
				
				modelAndView.addObject("success", "Usuário excluido com sucesso.");
			} catch (Exception e) {
				modelAndView.addObject("error", "Erro ao excluir o usuário, tente novamente.");
			}
		}
		
		List<UsuarioDTO> usuarios = dtoConverter.convertListToListDtoUsuarios(usuarioService.findAll());
		modelAndView.addObject("alunos", usuarios);
		modelAndView.setViewName(USUARIO_HOME);
		
		return modelAndView;
	}
}
