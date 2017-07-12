package com.tiagohs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "turma")
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "turma_id")
	private long id;
	
	@Column(name = "ano")
	private int ano;
	
	@Column(name = "semestre")
	private String semestre;

	@Column(name = "dia_semana")
	private int diaSemana;

	@Column(name = "horario_realizacao")
	private int horarioRealizacao;
	
	@OneToMany(mappedBy = "turma", targetEntity = Disciplina.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Disciplina> disciplina;
	
	@ManyToMany(mappedBy = "turma")
	private List<Professor> professor;
	
	@OneToMany(mappedBy = "turma", targetEntity = Disciplina.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Aluno> aluno;
	
	@OneToOne(mappedBy = "turma", optional = true, fetch = FetchType.LAZY)
	private Avaliacao avaliacao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public int getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}
	public int getHorarioRealizacao() {
		return horarioRealizacao;
	}
	public void setHorarioRealizacao(int horarioRealizacao) {
		this.horarioRealizacao = horarioRealizacao;
	}
	public List<Disciplina> getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(List<Disciplina> disciplina) {
		this.disciplina = disciplina;
	}
	public List<Professor> getProfessor() {
		return professor;
	}
	public void setProfessor(List<Professor> professor) {
		this.professor = professor;
	}
	public List<Aluno> getAluno() {
		return aluno;
	}
	public void setAluno(List<Aluno> aluno) {
		this.aluno = aluno;
	}
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	
}
