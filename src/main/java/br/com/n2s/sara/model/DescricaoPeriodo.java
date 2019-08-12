package br.com.n2s.sara.model;

public enum DescricaoPeriodo {
	SUBMISSAO_MANUSCRITO("submiss�o de manuscrito"),
	AVALIACAO("avalia��o"),
	SUBMISSAO_FINAL("submiss�o final"),
	RESULTADO_FINAL("resultado final");
	
	private String descricao;
	
	DescricaoPeriodo(String descricao){
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
