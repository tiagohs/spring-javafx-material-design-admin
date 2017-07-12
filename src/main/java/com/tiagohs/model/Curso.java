package com.tiagohs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "curso_id")
	private long id;
	
	@Column(name = "descricao", length = 200)
	private String descricao;
	
	@Column(name = "coordenador", length = 30)
	private String coordenador;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "curso_professor", 
				joinColumns = @JoinColumn(name = "curso_id"),
				inverseJoinColumns = @JoinColumn(name = "professor_id"))
	private List<Professor> professor;
	
	@ManyToMany(mappedBy = "curso")
	private List<Aluno> aluno;
	
	@OneToOne(mappedBy = "curso", optional = true, fetch = FetchType.LAZY)
	private Disciplina disciplina;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCoordenador() {
		return coordenador;
	}
	public void setCoordenador(String coordenador) {
		this.coordenador = coordenador;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
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
	
	
}
