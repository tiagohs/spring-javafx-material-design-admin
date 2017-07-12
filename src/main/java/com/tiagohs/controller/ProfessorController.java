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

import com.tiagohs.model.dto.DtoConverter;
import com.tiagohs.model.dto.ProfessorDTO;
import com.tiagohs.service.ProfessorService;

@Controller
public class ProfessorController {
	
	private static final String PROFESSOR_HOME_DEFAULT = "/admin/professor/";
	private static final String PROFESSOR_HOME = "/admin/professor/home";
	private static final String PROFESSOR_CREATE = "/admin/professor/create";
	private static final String PROFESSOR_EDIT = "/admin/professor/edit";
	private static final String PROFESSOR_DELETE = "/admin/professor/delete";
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private DtoConverter dtoConverter;
	
	@RequestMapping(value={PROFESSOR_HOME, PROFESSOR_HOME_DEFAULT}, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		List<ProfessorDTO> professores = dtoConverter.convertListToListDtoProfessores(professorService.findAll());
		
		modelAndView.addObject("professores", professores);
		modelAndView.setViewName(PROFESSOR_HOME);
		
		return modelAndView;
	}
	
	@RequestMapping(value = PROFESSOR_CREATE, method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		ProfessorDTO professor = new ProfessorDTO();
		
		modelAndView.addObject("professor", professor);
		modelAndView.setViewName(PROFESSOR_CREATE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = PROFESSOR_CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute("professor") ProfessorDTO professorDTO, BindingResult bindingResult) {
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = PROFESSOR_EDIT, method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		ProfessorDTO professor = dtoConverter.entityToDto(professorService.find(id));
		
		if (null != professor) {
			modelAndView.addObject("professor", professor);
			modelAndView.setViewName(PROFESSOR_EDIT);
		} else {
			return new ModelAndView("redirect:" + PROFESSOR_HOME);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = PROFESSOR_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(@Valid @ModelAttribute("professor") ProfessorDTO professorDTO, BindingResult bindingResult) {
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = PROFESSOR_DELETE, method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		ProfessorDTO professor = dtoConverter.entityToDto(professorService.find(id));
		
		if (null != professor) {
			modelAndView.addObject("professor", professor);
			modelAndView.setViewName(PROFESSOR_DELETE);
		} else {
			return new ModelAndView("redirect:" + PROFESSOR_HOME);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = PROFESSOR_DELETE, method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("professor") ProfessorDTO professorDTO) {
		
		return new ModelAndView();
	}
	
}
