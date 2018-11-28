package br.com.n2s.sara.model;

public class Submissao {
	
	private Usuario autor;
	private Trabalho trabalho;
	private TipoAutor tipoAutor;
	
	public void setTipoAutor(TipoAutor tp) {
		this.tipoAutor = tp;
	}
	
	public TipoAutor getTipoAutor() {
		return tipoAutor;
	}
	
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