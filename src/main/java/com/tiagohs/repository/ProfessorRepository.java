package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagohs.model.Professor;

@Repository("professorRepository")
public class ProfessorRepository extends JpaRepository<Professor, Long> {

}
