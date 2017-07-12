package com.tiagohs.model;

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "aluno")
public class Aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "aluno_id")
	private int id;
	
	@Column(name = "matricula", nullable = false, unique = true)
	private String matricula;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Column(name = "dataNascimento")
	@Type(type="date")
	private Date dataNascimento;
	
	@OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Endereco> endereco;
	
	@OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Telefone> telefone;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "aluno_curso", 
	   joinColumns = @JoinColumn(name = "aluno_id"), 
	   inverseJoinColumns = @JoinColumn(name = "curso_id"))
	private List<Curso> curso;
	
	@OneToOne(mappedBy = "aluno", optional = true, fetch = FetchType.LAZY)
	private Avaliacao avaliacao;
	
	@ManyToOne
	private Turma turma;
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Endereco> getEndereco() {
		return endereco;
	}
	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}
	public List<Telefone> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	public List<Curso> getCurso() {
		return curso;
	}
	public void setCurso(List<Curso> curso) {
		this.curso = curso;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
}
