package br.com.n2s.sara.model;

public class CoordenacaoEvento {
	// Esta classe ser� descontinuada e excluida, favor n�o utilizar.

	@Deprecated
	private Usuario coordenador;
	private Evento evento;
	
	public Usuario getCoordenador() {
		return coordenador;
	}
	public void setCoordenador(Usuario coordenador) {
		this.coordenador = coordenador;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
