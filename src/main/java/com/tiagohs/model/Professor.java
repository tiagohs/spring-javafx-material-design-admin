package com.tiagohs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "professor_id")
	private long id;
	
	@Column(name = "nome")
	private String nome;
	
	@OneToMany(mappedBy = "professor", targetEntity = Telefone.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Telefone> telefone;
	
	@OneToMany(mappedBy = "professor", targetEntity = Endereco.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Endereco> endereco;
	
	@ManyToMany(mappedBy = "professor")
	private List<Curso> curso;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "professor_turma", 
				joinColumns = @JoinColumn(name = "professor_id"),
				inverseJoinColumns = @JoinColumn(name = "turma_id"))
	private List<Turma> turma;
	
	@Enumerated(EnumType.STRING)
	private TitulacaoMaxima titulacaoMaxima;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Telefone> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}
	public List<Endereco> getEndereco() {
		return endereco;
	}
	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}
	public TitulacaoMaxima getTitulacaoMaxima() {
		return titulacaoMaxima;
	}
	public void setTitulacaoMaxima(TitulacaoMaxima titulacaoMaxima) {
		this.titulacaoMaxima = titulacaoMaxima;
	}
	public List<Curso> getCurso() {
		return curso;
	}
	public void setCurso(List<Curso> curso) {
		this.curso = curso;
	}
	public List<Turma> getTurma() {
		return turma;
	}
	public void setTurma(List<Turma> turma) {
		this.turma = turma;
	}
	
	

}
