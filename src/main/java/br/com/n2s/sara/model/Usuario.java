package br.com.n2s.sara.model;

public class Usuario {

	private String cpf;
	private String nome;
	private String sobrenome;
	private String email;
	private NivelUsuario tipo;	
		
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) { 
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public NivelUsuario getTipo() {
		return tipo;
	}
	public void setTipo(NivelUsuario tipo) {
		this.tipo = tipo;
	}
}
