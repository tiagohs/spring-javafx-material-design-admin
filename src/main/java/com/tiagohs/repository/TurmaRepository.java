package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagohs.model.Turma;

@Repository("turmaRepository")
public class TurmaRepository extends JpaRepository<Turma, Long> {

}
