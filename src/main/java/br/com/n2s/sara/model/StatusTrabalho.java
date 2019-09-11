package br.com.n2s.sara.model;

public enum StatusTrabalho {
	ENVIADO("Trabalho enviado!"),
	EM_AVALIACAO("Em avaliação!"),
	REJEITADO("Trabalho não aceito!"),
	ACEITO("Trabalho aceito!"),
	SUBMISSAO_FINAL("Versão final submetida!"),
	ACEITO_FINAL("Versão final aprovada!"),
	ACEITO_ORIENTADOR("Aprovado pelo orientador!"),
	REJEITADO_ORIENTADOR("Não aprovado pelo orientador!");
	
	private String descricao;
	
	StatusTrabalho(String descricao){
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
