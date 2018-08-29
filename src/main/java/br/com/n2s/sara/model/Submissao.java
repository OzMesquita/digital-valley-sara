package br.com.n2s.sara.model;

public class Submissao {
	
	private Usuario autor;
	private Trabalho trabalho;
	
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	public Trabalho getTrabalho() {
		return trabalho;
	}
	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}
}