package com.tiagohs.model;

public class Disciplina {

	private long id;
	private String descricao;
	private String carga_horaria;
	private String ementa;
	private String bibliografia;
	private String preRequisitos;
	private CargaHoraria cargaHoraria;
	private Curso curso;
	private Turma turma;
	
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
	public String getCarga_horaria() {
		return carga_horaria;
	}
	public void setCarga_horaria(String carga_horaria) {
		this.carga_horaria = carga_horaria;
	}
	public String getEmenta() {
		return ementa;
	}
	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}
	public String getBibliografia() {
		return bibliografia;
	}
	public void setBibliografia(String bibliografia) {
		this.bibliografia = bibliografia;
	}
	public String getPreRequisitos() {
		return preRequisitos;
	}
	public void setPreRequisitos(String preRequisitos) {
		this.preRequisitos = preRequisitos;
	}
	public CargaHoraria getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(CargaHoraria cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	

}
