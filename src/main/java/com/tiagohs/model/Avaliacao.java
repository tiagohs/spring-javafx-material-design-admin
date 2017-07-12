package com.tiagohs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "avaliacao_id")
	private int id;
	
	@Column(name = "nota_av1")
	private int notaAv1;
	
	@Column(name = "nota_av2")
	private int notaAv2;
	
	@Column(name = "nota_final")
	private int notaFinal;
	
	@Column(name = "frequencia")
	private int frequencia;
	
	@OneToOne
	private Aluno aluno;
	
	@OneToOne
	private Turma turma;
	
	public int getNotaAv1() {
		return notaAv1;
	}
	public void setNotaAv1(int notaAv1) {
		this.notaAv1 = notaAv1;
	}
	public int getNotaAv2() {
		return notaAv2;
	}
	public void setNotaAv2(int notaAv2) {
		this.notaAv2 = notaAv2;
	}
	public int getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(int notaFinal) {
		this.notaFinal = notaFinal;
	}
	public int getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	

}
