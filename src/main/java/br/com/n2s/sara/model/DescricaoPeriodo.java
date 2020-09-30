package br.com.n2s.sara.model;

public enum DescricaoPeriodo {
	SUBMISSAO_MANUSCRITO("submissão de manuscrito"),
	AVAL("aval do orientador"),
	AVALIACAO("avaliação"),
	SUBMISSAO_FINAL("submissão final"),
	RESULTADO_FINAL("resultado final"),
	RECURSO("recurso"),
	AVALIACAO_RECURSO("Avaliação de recurso");
	
	private String descricao;
	
	DescricaoPeriodo(String descricao){
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
