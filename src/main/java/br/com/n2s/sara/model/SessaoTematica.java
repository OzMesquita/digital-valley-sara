package br.com.n2s.sara.model;

/**
 * 
 * Classe representa uma Sessao Tematica
 * 
 * @author paulo.macedo
 *
 */
public class SessaoTematica {

	private int id;
	private Evento evento;
	private String nome;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Evento getEvento() {
		return evento;
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
