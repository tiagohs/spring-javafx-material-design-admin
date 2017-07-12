package com.tiagohs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Disciplina;
import com.tiagohs.repository.DisciplinaRepository;

@Service("disciplinaService")
public class DisciplinaServiceImpl extends BaseService<Disciplina, JpaRepository<Disciplina,Long>> implements DisciplinaService {
	
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	public AlunoServiceImpl(DisciplinaRepository disciplinaRepository) {
		super(disciplinaRepository);
		
		this.disciplinaRepository = disciplinaRepository;
	}
}
