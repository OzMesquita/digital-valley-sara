package br.com.n2s.sara.model;

public enum TipoEvento {	
	EU("Encontros Universitários"),ESTAGIO("Estágio");
	
	private String descricao;	
	
	TipoEvento(String descricao){
		this.descricao=descricao;
	} 
	
	public String getDescricao() {
		return descricao;
	}
	
}
