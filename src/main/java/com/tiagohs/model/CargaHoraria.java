package com.tiagohs.model;

public class CargaHoraria {

	private long id;
	private int teorica;
	private int pratica;
	private int estagio;
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
