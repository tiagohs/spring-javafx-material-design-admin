package com.tiagohs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tiagohs.model.dto.AlunoDTO;
import com.tiagohs.model.dto.DisciplinaDTO;
import com.tiagohs.model.dto.DtoConverter;
import com.tiagohs.service.DisciplinaService;

@Controller
public class DisciplinaController {
	
	private static final String DISCIPLINA_HOME_DEFAULT = "/admin/disciplina/";
	private static final String DISCIPLINA_HOME = "/admin/disciplina/home";
	private static final String DISCIPLINA_CREATE = "/admin/disciplina/create";
	private static final String DISCIPLINA_EDIT = "/admin/disciplina/edit";
	private static final String DISCIPLINA_DELETE = "/admin/disciplina/delete";
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private DtoConverter dtoConverter;
	
	@RequestMapping(value={DISCIPLINA_HOME, DISCIPLINA_HOME_DEFAULT}, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		List<DisciplinaDTO> disciplinas = dtoConverter.convertListToListDtoDisciplinas(disciplinaService.findAll());
		
		modelAndView.addObject("disciplinas", disciplinas);
		modelAndView.setViewName(DISCIPLINA_HOME);
		
		return modelAndView;
	}
	
	@RequestMapping(value = DISCIPLINA_CREATE, method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		DisciplinaDTO disciplina = new DisciplinaDTO();
		
		modelAndView.addObject("disciplina", disciplina);
		modelAndView.setViewName(DISCIPLINA_CREATE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = DISCIPLINA_CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute("disciplina") DisciplinaDTO disciplinaDTO, BindingResult bindingResult) {
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = DISCIPLINA_EDIT, method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		DisciplinaDTO disciplina = dtoConverter.entityToDto(disciplinaService.find(id));
		
		if (null != disciplina) {
			modelAndView.addObject("disciplina", disciplina);
			modelAndView.setViewName(DISCIPLINA_EDIT);
		} else {
			return new ModelAndView("redirect:" + DISCIPLINA_HOME);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = DISCIPLINA_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(@Valid @ModelAttribute("disciplina") DisciplinaDTO disciplinaDTO, BindingResult bindingResult) {
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = DISCIPLINA_DELETE, method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		DisciplinaDTO disciplina = dtoConverter.entityToDto(disciplinaService.find(id));
		
		if (null != disciplina) {
			modelAndView.addObject("disciplina", disciplina);
			modelAndView.setViewName(DISCIPLINA_DELETE);
		} else {
			return new ModelAndView("redirect:" + DISCIPLINA_HOME);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = DISCIPLINA_DELETE, method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("disciplina") DisciplinaDTO disciplinaDTO) {
		
		return new ModelAndView();
	}
}
