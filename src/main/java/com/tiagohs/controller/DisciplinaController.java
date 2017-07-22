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
		
		modelAndView.addObject("pageTitle", "stuffs-Admin - Adicionar novo Disciplina");
		modelAndView.addObject("pageFormTitle", "Nova Disciplina");
		modelAndView.addObject("pageSubTitle", "Cadastrar uma nova disciplinas no sistema.");
		modelAndView.addObject("pageAction", "/admin/disciplina/create");
		modelAndView.addObject("disciplina", disciplina);
		modelAndView.setViewName(DISCIPLINA_CREATE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = DISCIPLINA_CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute("disciplina") DisciplinaDTO disciplinaDTO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:" + DISCIPLINA_CREATE);
			
			DisciplinaDTO disciplina = new DisciplinaDTO();
			modelAndView.addObject("disciplina", disciplina);
			modelAndView.addObject("error", true);
			modelAndView.addObject("errorDescription", "Houve algum erro no registro, tente novamente.");
			
			return modelAndView;
		}
		
		if (null != disciplinaDTO) {
			try {
				
				disciplinaService.save(dtoConverter.dtoToEntity(disciplinaDTO));
				
				List<DisciplinaDTO> disciplinas = dtoConverter.convertListToListDtoDisciplinas(disciplinaService.findAll());
				modelAndView.addObject("disciplinas", disciplinas);
				
				modelAndView.addObject("success", "Disciplina salva com sucesso.");
				modelAndView.setViewName("redirect:" + DISCIPLINA_HOME);
			} catch (Exception e) {
				modelAndView.setViewName("redirect:" + DISCIPLINA_CREATE);
				modelAndView.addObject("error", true);
			}
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = DISCIPLINA_EDIT, method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		DisciplinaDTO disciplina = null;
		try {
			disciplina = dtoConverter.entityToDto(disciplinaService.find(id));
		} catch (Exception e) {
			return new ModelAndView("redirect:" + DISCIPLINA_HOME);
		}
		
		if (null != disciplina) {
			disciplina.setId(id);
			
			modelAndView.addObject("pageTitle", "stuffs-Admin - Adicionar novo Disciplina");
			modelAndView.addObject("pageFormTitle", "Nova Disciplina");
			modelAndView.addObject("pageSubTitle", "Cadastrar uma nova disciplinas no sistema.");
			modelAndView.addObject("pageAction", "/admin/disciplina/create");
			modelAndView.addObject("disciplina", disciplina);
			modelAndView.setViewName(DISCIPLINA_CREATE);
		} else {
			return new ModelAndView("redirect:" + DISCIPLINA_HOME);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = DISCIPLINA_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(@Valid @ModelAttribute("disciplina") DisciplinaDTO disciplinaDTO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:" + DISCIPLINA_EDIT);
			modelAndView.addObject("id", disciplinaDTO.getId());
			
			return modelAndView;
		}
		
		try {
			disciplinaService.save(dtoConverter.dtoToEntity(disciplinaDTO));
			
			List<DisciplinaDTO> disciplinas = dtoConverter.convertListToListDtoDisciplinas(disciplinaService.findAll());
			modelAndView.addObject("alunos", disciplinas);
			
			modelAndView.addObject("success", "Atualizações feitas com sucesso.");
			modelAndView.setViewName("redirect:" + DISCIPLINA_HOME);
		} catch (Exception e) {
			modelAndView.setViewName("redirect:" + DISCIPLINA_CREATE);
			modelAndView.addObject("error", true);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = DISCIPLINA_DELETE, method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null != id) {
			
			try {
				disciplinaService.delete(Long.parseLong(id));
				
				modelAndView.addObject("success", "Aluno excluido com sucesso.");
			} catch (Exception e) {
				modelAndView.addObject("error", "Erro ao excluir o aluno, tente novamente.");
			}
		}
		
		List<DisciplinaDTO> disciplinas = dtoConverter.convertListToListDtoDisciplinas(disciplinaService.findAll());
		modelAndView.addObject("disciplinas", disciplinas);
		modelAndView.setViewName(DISCIPLINA_HOME);
		
		return modelAndView;
	}
}
