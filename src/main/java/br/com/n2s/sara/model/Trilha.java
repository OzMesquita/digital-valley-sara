package br.com.n2s.sara.model;

import java.util.ArrayList;

public class Trilha {
	
	private int idTrilha;
	private String nome;
	private String descricao;
	private ArrayList<Usuario> coordenadores;
	private Evento evento;
	private CriterioTrilha criterioTrilha;
	private ArrayList<Periodo> periodos;
	private ArrayList<Usuario> avaliadores;
	
	public int getIdTrilha() {
		return idTrilha;
	}
	public void setIdTrilha(int idTrilha) {
		this.idTrilha = idTrilha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ArrayList<Usuario> getCoordenadores() {
		return coordenadores;
	}
	public void setCoordenadores(ArrayList<Usuario> coordenadores) {
		this.coordenadores = coordenadores;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public CriterioTrilha getCriterioTrilha() {
		return criterioTrilha;
	}
	public void setCriterioTrilha(CriterioTrilha criterioTrilha) {
		this.criterioTrilha = criterioTrilha;
	}
	public ArrayList<Periodo> getPeriodos() {
		return periodos;
	}
	public void setPeriodos(ArrayList<Periodo> periodos) {
		this.periodos = periodos;
	}
	public ArrayList<Usuario> getAvaliadores() {
		return avaliadores;
	}
	public void setAvaliadores(ArrayList<Usuario> avaliadores) {
		this.avaliadores = avaliadores;
	}
	
}