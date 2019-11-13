package br.com.n2s.sara.model;

public enum TipoEvento {	
	EU("Encontros Universit�rios"),ESTAGIO("Est�gio");
	
	private String descricao;	
	
	TipoEvento(String descricao){
		this.descricao=descricao;
	} 
	
	public String getDescricao() {
		return descricao;
	}
	
}
