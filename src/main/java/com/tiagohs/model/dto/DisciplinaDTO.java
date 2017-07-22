package com.tiagohs.model.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class DisciplinaDTO {
	
	private long id;
	
	@NotEmpty(message = "Campo Obrigatório")
	private String descricao;

	private String ementa;
	private String bibliografia;
	private String preRequisitos;
	private int teorica;
	private int pratica;
	private int estagio;
	
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
	
	
}
