package br.com.n2s.sara.model;

public class Item {
	
	private int idItem;
	private String descricao;
	private int peso;
	private Criterio criterio;
	
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
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
	public Criterio getCriterio() {
		return criterio;
	}
	public void setCriterio(Criterio criterio) {
		this.criterio = criterio;
	}
	
}