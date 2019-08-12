package br.com.n2s.sara.model;

import java.util.List;

public class AvaliaTrabalho {
	
	private Usuario avaliador;
	private Trabalho trabalho;
	private String feedback;
	@Deprecated
	private StatusTrabalho status;
	
	private List<Item> itens;
	private float nota;
	
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public StatusTrabalho getStatus() {
		return status;
	}
	public void setStatus(StatusTrabalho status) {
		this.status = status;
	}
	public Usuario getAvaliador() {
		return avaliador;
	}
	public void setAvaliador(Usuario avaliador) {
		this.avaliador = avaliador;
	}
	public Trabalho getTrabalho() {
		return trabalho;
	}
	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	
 
}