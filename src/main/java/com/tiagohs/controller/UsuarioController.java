package com.tiagohs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tiagohs.model.dto.DtoConverter;
import com.tiagohs.model.dto.UsuarioDTO;
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
		UsuarioDTO usuario = new UsuarioDTO();
		
		modelAndView.addObject("usuario", usuario);
		modelAndView.setViewName(USUARIO_CREATE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = USUARIO_CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute("usuario") UsuarioDTO usuarioDTO, BindingResult bindingResult) {
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = USUARIO_EDIT, method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		UsuarioDTO usuario = dtoConverter.entityToDto(usuarioService.find(id));
		
		if (null != usuario) {
			modelAndView.addObject("usuario", usuario);
			modelAndView.setViewName(USUARIO_EDIT);
		} else {
			return new ModelAndView("redirect:" + USUARIO_HOME);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = USUARIO_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(@Valid @ModelAttribute("usuario") UsuarioDTO usuarioDTO, BindingResult bindingResult) {
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = USUARIO_DELETE, method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		UsuarioDTO usuario = dtoConverter.entityToDto(usuarioService.find(id));
		
		if (null != usuario) {
			modelAndView.addObject("usuario", usuario);
			modelAndView.setViewName(USUARIO_DELETE);
		} else {
			return new ModelAndView("redirect:" + USUARIO_HOME);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = USUARIO_DELETE, method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
		
		return new ModelAndView();
	}
}
