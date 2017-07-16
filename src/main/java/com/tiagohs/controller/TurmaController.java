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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tiagohs.model.dto.DtoConverter;
import com.tiagohs.model.dto.TurmaDTO;
import com.tiagohs.service.TurmaService;

@Controller
public class TurmaController {
	
	private static final String TURMA_HOME_DEFAULT = "/admin/turma/";
	private static final String TURMA_HOME = "/admin/turma/home";
	private static final String TURMA_CREATE = "/admin/turma/create";
	private static final String TURMA_EDIT = "/admin/turma/edit";
	private static final String TURMA_DELETE = "/admin/turma/delete";
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private DtoConverter dtoConverter;
	
	@RequestMapping(value={TURMA_HOME, TURMA_HOME_DEFAULT}, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		List<TurmaDTO> turmas = dtoConverter.convertListToListDtoTurmas(turmaService.findAll());
		
		modelAndView.addObject("turmas", turmas);
		modelAndView.setViewName(TURMA_HOME);
		
		return modelAndView;
	}
	
	@RequestMapping(value = TURMA_CREATE, method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		TurmaDTO turma = new TurmaDTO();
		
		modelAndView.addObject("turma", turma);
		modelAndView.setViewName(TURMA_CREATE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = TURMA_CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute("turma") TurmaDTO turmaDTO, BindingResult bindingResult) {
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = TURMA_EDIT, method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		TurmaDTO turma = null;
		
		try {
			turma = dtoConverter.entityToDto(turmaService.find(id));
		} catch (Exception e) {
			return new ModelAndView("redirect:" + TURMA_HOME);
		}
		
		if (null != turma) {
			modelAndView.addObject("turma", turma);
			modelAndView.setViewName(TURMA_EDIT);
		} else {
			return new ModelAndView("redirect:" + TURMA_HOME);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = TURMA_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(@Valid @ModelAttribute("turma") TurmaDTO turmaDTO, BindingResult bindingResult) {
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = TURMA_DELETE, method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		TurmaDTO turma = null;
		
		try {
			turma = dtoConverter.entityToDto(turmaService.find(id));
		} catch (Exception e) {
			modelAndView.addObject("turma", turma);
		}
		
		if (null != turma) {
			modelAndView.addObject("turma", turma);
			modelAndView.setViewName(TURMA_DELETE);
		} else {
			return new ModelAndView("redirect:" + TURMA_HOME);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = TURMA_DELETE, method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("turma") TurmaDTO turmaDTO) {
		
		return new ModelAndView();
	}
}
