package com.tiagohs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tiagohs.model.dto.AlunoDTO;
import com.tiagohs.model.dto.DtoConverter;
import com.tiagohs.service.AlunoService;

@Controller
public class AlunoController {
	
	private static final String ALUNO_HOME_DEFAULT = "/admin/aluno/";
	private static final String ALUNO_HOME = "/admin/aluno/home";
	private static final String ALUNO_CREATE = "/admin/aluno/create";
	private static final String ALUNO_EDIT = "/admin/aluno/edit";
	private static final String ALUNO_DELETE = "/admin/aluno/delete";
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private DtoConverter dtoConverter;
	
	@RequestMapping(value={ALUNO_HOME, ALUNO_HOME_DEFAULT}, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		List<AlunoDTO> alunos = dtoConverter.convertListToListDtoAlunos(alunoService.findAll());
		
		modelAndView.addObject("alunos", alunos);
		modelAndView.setViewName(ALUNO_HOME);
		
		return modelAndView;
	}
	
	@RequestMapping(value = ALUNO_CREATE, method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		AlunoDTO aluno = new AlunoDTO();
		
		modelAndView.addObject("aluno", aluno);
		modelAndView.setViewName(ALUNO_CREATE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = ALUNO_CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute("aluno") AlunoDTO alunoDTO, BindingResult bindingResult) {
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = ALUNO_EDIT, method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		AlunoDTO aluno = null;
		try {
			aluno = dtoConverter.entityToDto(alunoService.find(id));
		} catch (Exception e) {
			return new ModelAndView("redirect:" + ALUNO_HOME);
		}
		
		if (null != aluno) {
			modelAndView.addObject("aluno", aluno);
			modelAndView.setViewName(ALUNO_EDIT);
		} else {
			return new ModelAndView("redirect:" + ALUNO_HOME);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = ALUNO_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(@Valid @ModelAttribute("aluno") AlunoDTO alunoDTO, BindingResult bindingResult) {
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = ALUNO_DELETE, method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		AlunoDTO aluno;
		
		try {
			aluno = dtoConverter.entityToDto(alunoService.find(id));
		} catch (Exception e) {
			return new ModelAndView("redirect:" + ALUNO_HOME);
		}
		
		if (null != aluno) {
			modelAndView.addObject("aluno", aluno);
			modelAndView.setViewName(ALUNO_DELETE);
		} else {
			return new ModelAndView("redirect:" + ALUNO_HOME);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = ALUNO_DELETE, method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("aluno") AlunoDTO alunoDTO) {
		
		return new ModelAndView();
	}
}
