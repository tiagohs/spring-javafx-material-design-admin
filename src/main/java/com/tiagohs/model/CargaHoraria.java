package com.tiagohs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carga_horaria")
public class CargaHoraria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "carga_horaria_id")
	private long id;
	
	@Column(name = "teorica")
	private int teorica;

	@Column(name = "pratica")
	private int pratica;

	@Column(name = "estagio")
	private int estagio;
	
	@OneToOne
	private Disciplina disciplina;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getTeorica() {
		return teorica;
	}
	public void setTeorica(int teorica) {
		this.teorica = teorica;
	}
	public int getPratica() {
		return pratica;
	}
	public void setPratica(int pratica) {
		this.pratica = pratica;
	}
	public int getEstagio() {
		return estagio;
	}
	public void setEstagio(int estagio) {
		this.estagio = estagio;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	

}
