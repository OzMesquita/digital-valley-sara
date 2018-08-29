package br.com.n2s.sara.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CriterioTrilha {

	private int idCriterioTrilha;
	private LocalDate dataCriacao;
	private String nome;
	private ArrayList<Criterio> criterios;
	
	public int getIdCriterioTrilha() {
		return idCriterioTrilha;
	}
	public void setIdCriterioTrilha(int idCriterioTrilha) {
		this.idCriterioTrilha = idCriterioTrilha;
	}
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
}