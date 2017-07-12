package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagohs.model.Aluno;

@Repository("alunoRepository")
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
