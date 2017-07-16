package com.tiagohs.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		aluno.setMatricula(RandomStringUtils.randomNumeric(10));
		
		modelAndView.addObject("aluno", aluno);
		modelAndView.setViewName(ALUNO_CREATE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = ALUNO_CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute("aluno") AlunoDTO alunoDTO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			String errors = "";
			for (ObjectError e : bindingResult.getAllErrors()) {
				errors += " // " + e.getDefaultMessage();
			}
			
			modelAndView.setViewName("forward:" + ALUNO_CREATE);
			
			AlunoDTO aluno = new AlunoDTO();
			aluno.setMatricula(RandomStringUtils.randomNumeric(10));
			modelAndView.addObject("aluno", aluno);
			modelAndView.addObject("error", true);
			modelAndView.addObject("errorDescription", errors);
			
			return modelAndView;
		}
		
		if (null != alunoDTO) {
			try {
				
				System.out.println("Matricula: " + alunoDTO.getMatricula());
				
				alunoService.save(dtoConverter.dtoToEntity(alunoDTO));
				
				List<AlunoDTO> alunos = dtoConverter.convertListToListDtoAlunos(alunoService.findAll());
				modelAndView.addObject("alunos", alunos);
				
				modelAndView.addObject("success", "Aluno salvo com sucesso.");
				modelAndView.setViewName("redirect:" + ALUNO_HOME);
				
				return modelAndView;
			} catch (Exception e) {
				modelAndView.setViewName("redirect:" + ALUNO_CREATE);
				modelAndView.addObject("error", true);
				
				return modelAndView;
			}
		}
		
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = ALUNO_EDIT, method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") long id) {
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
	public ModelAndView delete(@RequestParam("id") long id) {
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
	public ModelAndView deleteConfirm(@RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null != id) {
			
			try {
				alunoService.delete(Long.parseLong(id));
				
				modelAndView.addObject("success", "Aluno excluido com sucesso.");
			} catch (Exception e) {
				modelAndView.addObject("error", "Erro ao excluir o aluno, tente novamente.");
			}
		}
		
		List<AlunoDTO> alunos = dtoConverter.convertListToListDtoAlunos(alunoService.findAll());
		modelAndView.addObject("alunos", alunos);
		modelAndView.setViewName(ALUNO_HOME);
		
		return modelAndView;
	}
}
