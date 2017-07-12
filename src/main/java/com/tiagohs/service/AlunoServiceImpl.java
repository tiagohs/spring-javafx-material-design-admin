package com.tiagohs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Aluno;
import com.tiagohs.repository.AlunoRepository;

@Service("alunoService")
public class AlunoServiceImpl extends BaseService<Aluno, JpaRepository<Aluno,Long>> implements AlunoService {
	
	private AlunoRepository alunoRepository;
	
	@Autowired
	public AlunoServiceImpl(AlunoRepository alunoRepository) {
		super(alunoRepository);
		
		this.alunoRepository = alunoRepository;
	}
}
