package com.tiagohs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "disciplina")
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "disciplina_id")
	private long id;
	
	@Column(name = "descricao")
	private String descricao;

	@Column(name = "ementa")
	private String ementa;

	@Column(name = "bibliografia")
	private String bibliografia;

	@Column(name = "pre_requisitos")
	private String preRequisitos;
	
	@OneToOne(mappedBy = "disciplina", optional = true, fetch = FetchType.LAZY)
	private CargaHoraria cargaHoraria;
	
	@OneToOne
	private Curso curso;
	
	@ManyToOne
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
