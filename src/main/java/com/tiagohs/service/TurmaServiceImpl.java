package com.tiagohs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Turma;
import com.tiagohs.repository.UsuarioRepository;

@Service("turmaService")
public class TurmaServiceImpl extends BaseService<Turma, JpaRepository<Turma,Long>> implements TurmaService {
	
}
