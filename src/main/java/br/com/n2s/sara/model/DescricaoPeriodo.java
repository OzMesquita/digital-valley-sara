package br.com.n2s.sara.model;

public enum DescricaoPeriodo {
	SUBMISSAO_MANUSCRITO("submiss�o de manuscrito"),
	AVAL("aval do orientador"),
	AVALIACAO("avalia��o"),
	SUBMISSAO_FINAL("submiss�o final"),
	RESULTADO_FINAL("resultado final"),
	RECURSO("recurso"),
	AVALIACAO_RECURSO("Avalia��o de recurso");
	
	private String descricao;
	
	DescricaoPeriodo(String descricao){
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
