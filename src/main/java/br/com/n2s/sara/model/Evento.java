package br.com.n2s.sara.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Evento {
	
	private int idEvento;
	private List<Usuario> coordenadores;
	private String nome;
	private String descricao;
	private String site;
	private String localizacao;
	private LocalDate dataInicial;
	private LocalDate dataFinal;
	private List<Trilha> trilhas;
	private boolean divulgada;
	private TipoEvento descriEvento;

	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public List<Usuario> getCoordenadores() {
		return coordenadores;
	}
	public void setCoordenadores(List<Usuario> coordenadores) {
		this.coordenadores = coordenadores;
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public List<Trilha> getTrilhas() {
		return trilhas;
	}
	public void setTrilhas(List<Trilha> trilhas) {
		this.trilhas = trilhas;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public LocalDate getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}
	public LocalDate getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}
	/**
	 * @return the divulgada
	 */
	public boolean getDivulgada() {
		return divulgada;
	}
	/**
	 * @param divulgada the divulgada to set
	 */
	public void setDivulgada(boolean divulgada) {
		this.divulgada = divulgada;
	}
	
	public boolean isCoordenador(Usuario u) {
		for (Usuario t:this.coordenadores) {
			if(t.getCpf().equals(u.getCpf()))
				return true;
		}
		return false;
	}
	public TipoEvento getDescriEvento() {
		return descriEvento;
	}
	public void setDescriEvento(TipoEvento descriEvento) {
		this.descriEvento = descriEvento;
	}
}