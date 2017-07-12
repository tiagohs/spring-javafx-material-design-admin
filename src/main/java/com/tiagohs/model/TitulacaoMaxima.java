package com.tiagohs.model;

public enum TitulacaoMaxima {
	GRADUACAO("Graduação"), 
	ESPECIALIZACAO("Especialização"), 
	MESTRADO("Mestrado"), 
	DOUTORADO("Dourado");
	
	private String nome;

	private TitulacaoMaxima(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
