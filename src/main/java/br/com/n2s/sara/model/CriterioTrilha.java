package br.com.n2s.sara.model;

import java.time.LocalDate;
import java.util.ArrayList;
@Deprecated
public class CriterioTrilha {

	private LocalDate dataCriacao;
	private String nome;
	private ArrayList<Criterio> criterios;
	private Trilha trilha;
	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<Criterio> getCriterios() {
		return criterios;
	}
	public void setCriterios(ArrayList<Criterio> criterios) {
		this.criterios = criterios;
	}
	public Trilha getTrilha() {
		return trilha;
	}
	public void setTrilha(Trilha trilha) {
		this.trilha = trilha;
	}
}