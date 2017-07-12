package com.tiagohs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Professor;
import com.tiagohs.repository.ProfessorRepository;

@Service("professorService")
public class ProfessorServiceImpl extends BaseService<Professor, JpaRepository<Professor,Long>> implements ProfessorService {
	
	private ProfessorRepository professorRepository;
	
	@Autowired
	public AlunoServiceImpl(ProfessorRepository professorRepository) {
		super(professorRepository);
		
		this.professorRepository = professorRepository;
	}
	
	
}
