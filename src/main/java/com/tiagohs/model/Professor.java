package com.tiagohs.model;

import java.util.List;

public class Professor {

	private long id;
	private String nome;
	private List<Telefone> telefone;
	private List<Endereco> endereco;
	private TitulacaoMaxima titulacaoMaxima;
	private List<Curso> curso;
	private List<Turma> turma;
	
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
