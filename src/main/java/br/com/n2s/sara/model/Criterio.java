package br.com.n2s.sara.model;

import java.util.ArrayList;

public class Criterio {
	
	private int idCriterio;
	private String descricao;
	private int peso;
	private CriterioTrilha criterioTrilha;
	private ArrayList<Item> itens;
	private String nome;
	private boolean padrao;
	
	public int getIdCriterio() {
		return idCriterio;
	}
	public void setIdCriterio(int idCriterio) {
		this.idCriterio = idCriterio;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public CriterioTrilha getCriterioTrilha() {
		return criterioTrilha;
	}
	public void setCriterioTrilha(CriterioTrilha criterioTrilha) {
		this.criterioTrilha = criterioTrilha;
	}
	public ArrayList<Item> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isPadrao() {
		return padrao;
	}
	public void setPadrao(boolean padrao) {
		this.padrao = padrao;
	}
	
}
