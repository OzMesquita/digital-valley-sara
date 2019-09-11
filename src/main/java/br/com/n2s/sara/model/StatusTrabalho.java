package br.com.n2s.sara.model;

public enum StatusTrabalho {
	ENVIADO("Trabalho enviado!"),
	EM_AVALIACAO("Em avalia��o!"),
	REJEITADO("Trabalho n�o aceito!"),
	ACEITO("Trabalho aceito!"),
	SUBMISSAO_FINAL("Vers�o final submetida!"),
	ACEITO_FINAL("Vers�o final aprovada!"),
	ACEITO_ORIENTADOR("Aprovado pelo orientador!"),
	REJEITADO_ORIENTADOR("N�o aprovado pelo orientador!");
	
	private String descricao;
	
	StatusTrabalho(String descricao){
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
