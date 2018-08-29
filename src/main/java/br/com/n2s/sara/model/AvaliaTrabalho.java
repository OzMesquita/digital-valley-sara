package br.com.n2s.sara.model;

public class AvaliaTrabalho {
	
	private Usuario avaliador;
	private Trabalho trabalho;
	
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

}