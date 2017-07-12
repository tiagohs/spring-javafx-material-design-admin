package com.tiagohs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tiagohs.model.Usuario;
import com.tiagohs.model.dto.DtoConverter;
import com.tiagohs.model.dto.RoleDTO;
import com.tiagohs.model.dto.UsuarioDTO;
import com.tiagohs.service.RoleService;
import com.tiagohs.service.UsuarioService;

@Controller
public class AuthController {
	
	public static final String LOGIN_HOME = "/auth/login";
	public static final String LOGIN_REGISTER = "/auth/registration";
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private DtoConverter dtoConverter;
	
	@RequestMapping(value={"/", LOGIN_HOME}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LOGIN_HOME);
		return modelAndView;
	}
	
	@RequestMapping(value=LOGIN_REGISTER, method = RequestMethod.GET)
	public ModelAndView registration(){
		List<RoleDTO> roles = dtoConverter.convertListToListDtoRoles(roleService.findAll());
		
		ModelAndView modelAndView = new ModelAndView();
		UsuarioDTO user = new UsuarioDTO();
		modelAndView.addObject("user", user);
		modelAndView.addObject("rolesList", roles);
		modelAndView.setViewName(LOGIN_REGISTER);
		
		return modelAndView;
	}
	
	@RequestMapping(value = LOGIN_REGISTER, method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid @ModelAttribute("user") UsuarioDTO user, BindingResult bindingResult) {
		List<RoleDTO> roles = dtoConverter.convertListToListDtoRoles(roleService.findAll());
		
		ModelAndView modelAndView = new ModelAndView();
		Usuario userExists = userService.findUserByEmail(user.getEmail());
		
		if (userExists != null) {
			modelAndView.addObject("rolesList", roles);
			bindingResult .rejectValue("email", "error.user", "Usuário já criado. Tente realizer o login.");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("rolesList", roles);
			modelAndView.setViewName(LOGIN_REGISTER);
		} else {
			userService.saveUser(dtoConverter.dtoToEntity(user));
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("rolesList", roles);
			modelAndView.addObject("user", new UsuarioDTO());
			modelAndView.setViewName(LOGIN_REGISTER);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getEmail());
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
}
}
