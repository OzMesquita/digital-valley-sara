package br.com.n2s.sara.model;

import java.util.ArrayList;



public class Trabalho {

	private int idTrabalho;
	private String titulo;
	private String palavrasChaves;
	private String resumo;
	private StatusTrabalho status;
	private Trilha trilha; // Trilha a qual o trabalho está associado
	private ArrayList<Usuario> autores;
	private Usuario autor;
	private String enderecoInicial;
	private Usuario orientador;
	private String endereco;// Substitui versao final
	private TipoApresentacao tipoApresentacao;

	public Usuario getOrientador() {
		return orientador;
	}

	public void setOrientador(Usuario usuario) {
		this.orientador = usuario;
	}

	public String getEnderecoInicial() {
		return enderecoInicial;
	}

	public void setEnderecoInicial(String endereco) {
		this.enderecoInicial = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public int getIdTrabalho() {
		return idTrabalho;
	}

	public void setIdTrabalho(int idTrabalho) {
		this.idTrabalho = idTrabalho;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPalavrasChaves() {
		return palavrasChaves;
	}

	public void setPalavrasChaves(String palavrasChaves) {
		this.palavrasChaves = palavrasChaves;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public StatusTrabalho getStatus() {
		return status;
	}

	public void setStatus(StatusTrabalho status) {
		this.status = status;
	}

	public Trilha getTrilha() {
		return trilha;
	}

	public void setTrilha(Trilha trilha) {
		this.trilha = trilha;
	}

	public ArrayList<Usuario> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<Usuario> autores) {
		this.autores = autores;
	}

	public TipoApresentacao getTipoApresentacao() {
		return tipoApresentacao;
	}

	public void setTipoApresentacao(TipoApresentacao tipoApresentacao) {
		this.tipoApresentacao = tipoApresentacao;
	}
	
}