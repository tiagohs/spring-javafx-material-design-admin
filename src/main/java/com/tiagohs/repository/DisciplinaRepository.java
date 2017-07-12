package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagohs.model.Disciplina;

@Repository("disciplinaRepository")
public class DisciplinaRepository extends JpaRepository<Disciplina, Long> {

}
