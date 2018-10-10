package br.com.n2s.sara.model;

public class AvaliaTrabalho {
	
	private Usuario avaliador;
	private Trabalho trabalho;
	private String feedback;
	private StatusTrabalho status;
	
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

}